package com.example.riadh.hotelsbookings.AdminPack;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.provider.*;
import android.util.*;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;
import com.example.riadh.hotelsbookings.R;

import org.json.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by riadh on 8/26/2016.
 */
public class Manage_Hotels_Images extends Activity {

    Button upload,prev,next,delete,setprev;
    private static Spinner MyhotelsSp;
    private static String[] HotelsIDs;
    private static List<String> spinnerArray ;
    private static ArrayList<String> ImagesNames;
    LinearLayout menu;
    static ScrollView img_display;
    static int nbhotels,nbimages,ptr;
    NetworkImageView imageView;
    private ImageLoader imageLoader;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL =Server_Host_Constant.Host+"/hotelsbookingsapp/UploadImg.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_HOTEL="idhotel";

    @Override
    protected void onCreate(Bundle savedInstanceStace){
        super.onCreate(savedInstanceStace);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.manage_hotel_images);
        upload=(Button)findViewById(R.id.img_mgr_upload);
        prev = (Button) findViewById(R.id.img_mgr_prev);
        next = (Button) findViewById(R.id.img_mgr_next);
        delete=(Button)findViewById(R.id.img_mgr_del);
        setprev=(Button)findViewById(R.id.img_mgr_set_asCover);
        menu=(LinearLayout)findViewById(R.id.img_mgr_menu);
        img_display=(ScrollView)findViewById(R.id.img_mgr_photos);
        MyhotelsSp=(Spinner)findViewById(R.id.img_mgr_h_sp);
        imageView=(NetworkImageView)findViewById(R.id.intimgv);
        spinnerArray =  new ArrayList<String>();
        ImagesNames=new ArrayList<String>();
        ptr=0;nbimages=0;nbhotels=0;

        MyhotelsSp.setEnabled(false);
        menu.setVisibility(View.GONE);
        img_display.setVisibility(View.GONE);
        empty_slide_case();
        Load_MyHotelsList();





        MyhotelsSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {
                ptr=0;
                getImagesList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                showFileChooser();

            }}

        );

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            ptr++;
            ptr=(ptr+nbimages)%nbimages;
            loadImage(ImagesNames.get(ptr));
            }}

        );

        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ptr--;
                ptr=(nbimages+ptr)%nbimages;
                loadImage(ImagesNames.get(ptr));

            }}

        );

        setprev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String url = Server_Host_Constant.Host+"/hotelsbookingsapp/set_main_img.php";
                StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    Toast.makeText(getBaseContext(),response,Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("idhotel", HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        params.put("imgname",ImagesNames.get(ptr));
                        return params;
                    }
                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

            }}

        );
    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String url = Server_Host_Constant.Host+"/hotelsbookingsapp/delete_img.php";
            StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                                ptr=0;
                                getImagesList();
                    Toast.makeText(getBaseContext(),response,Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("idhotel", HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                    params.put("imgname",ImagesNames.get(ptr));
                    return params;
                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

        }
    }
    );

    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String rep) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        img_display.setVisibility(View.VISIBLE);
                        getImagesList();
                        not_empty_slide_case();
                        Toast.makeText(Manage_Hotels_Images.this, rep , Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Manage_Hotels_Images.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String

                String image = getStringImage(bitmap);

                //Getting Image Name

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                String currentDateandTime = sdf.format(new Date());

                String image_name= currentDateandTime;
                params.put(KEY_NAME, image_name);
                params.put(KEY_HOTEL,HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);

                return params;

            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);


        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri filePath = data.getData();


            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if(bitmap.getHeight()>1600||bitmap.getWidth()>1600){
                    new AlertDialog.Builder(Manage_Hotels_Images.this)
                            .setTitle("Oops !")
                            .setIcon(R.drawable.caution)
                            .setMessage("Sorry, this picture is not authorised !")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create().show();
                }else{


                try{

                    imageView.setImageBitmap(bitmap);
                    uploadImage();
                }catch(RuntimeException e){
                    e.printStackTrace();
                }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadImage(final String imgname){

        String url =Server_Host_Constant.Host+"/hotelsbookingsapp/IMG_Hotels/"+imgname;
        if(url.equals(""))
            return;


        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();

        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.clock, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
    }

    private void Load_MyHotelsList(){

        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getMyHotelsList.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nbhotels=0;
                try{
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    nbhotels=jsonArray.length();
                    if(nbhotels>0)  {
                        MyhotelsSp.setEnabled(true);
                        menu.setVisibility(View.VISIBLE);
                        img_display.setVisibility(View.VISIBLE);
                        spinnerArray.clear();
                        HotelsIDs=new String[nbhotels];
                        for (int i = 0; i < nbhotels; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            HotelsIDs[i]=explrObject.getString("id");
                            spinnerArray.add(i,explrObject.getString("name"));
                            build_sp();
                            getImagesList();
                        }}else{
                            MyhotelsSp.setEnabled(false);
                            menu.setVisibility(View.GONE);
                            img_display.setVisibility(View.GONE);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
                    }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("adminid", AccountInfos.getUserid());
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    private void build_sp(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MyhotelsSp.setAdapter(adapter);
        if(nbhotels>0)
            MyhotelsSp.setSelection(0,true);
    }

    private void getImagesList(){
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getMyHotelImages.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nbimages=0;
                try{
                    ImagesNames.clear();
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    nbimages=jsonArray.length();
                    if(nbimages>0)  {
                        for (int i = 0; i < nbimages; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            ImagesNames.add(explrObject.getString("imgname"));
                        }
                        not_empty_slide_case();
                        loadImage(ImagesNames.get(ptr));
                    }else{
                        empty_slide_case();
                        }
                        }catch(JSONException e){
                e.printStackTrace();
                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("idhotel", HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    private void empty_slide_case(){
        img_display.setVisibility(View.GONE);
        delete.setEnabled(false);
        setprev.setEnabled(false);
    }

    private void not_empty_slide_case(){
        img_display.setVisibility(View.VISIBLE);
        delete.setEnabled(true);
        setprev.setEnabled(true);
    }
}

