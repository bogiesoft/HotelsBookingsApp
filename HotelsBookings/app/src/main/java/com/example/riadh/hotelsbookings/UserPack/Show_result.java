package com.example.riadh.hotelsbookings.UserPack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.*;
import android.graphics.Canvas;
import android.graphics.drawable.*;
import android.os.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.*;
import android.widget.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;

import com.example.riadh.hotelsbookings.*;

import org.json.*;

import java.util.*;

/**
 * Created by riadh on 8/27/2016.
 */
public class Show_result extends Activity{



    static ListView list;
    static Result_list_adapter adapter;
    static ArrayList<String> idhotel;
    static ArrayList<String> rating;
    static ArrayList<String> name;
    static ArrayList<String> price;
    static ArrayList<String> imgname;

    static ArrayList<Bitmap> image;
    LinearLayout main_l;
    ImageView lst_imv;
    TextView emptymsg;
    String serv,reg,arrday,nbdays,nbk1,nba1,nbk2,nba2,nbk3,nba3,nbk4,nba4,nbk5,nba5,
           arrtime,nbper;
    String sum1,sum2,sum3,sum4,sum5;
    int nbhotels;
    ProgressDialog progressDialog ;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            try{
                final Handler start = new Handler();

                start.postDelayed(new Runnable() {
                    @Override
                    public void run() {
            image=new ArrayList<Bitmap>();
            name=new ArrayList<String>();
            rating=new ArrayList<String>();
            price=new ArrayList<String>();
            idhotel=new ArrayList<String>();
            imgname=new ArrayList<String>();

            setContentView(R.layout.result_list);

            main_l=(LinearLayout)findViewById(R.id.r_l_main_l);
            lst_imv=(ImageView)findViewById(R.id.lst_imv);
            list = (ListView) findViewById(R.id.resultlist);
            emptymsg=(TextView)findViewById(R.id.reslist_empty);



            reg="none";
            arrday="none";
            nbdays="none";
            arrtime="none";
            nbper="none";
            nbk1="none";
            nba1="none";
            nbk2="none";
            nba2="none";
            nbk3="none";
            nba3="none";
            nbk4="none";
            nba4="none";
            nbk5="none";
            nba5="none";

            sum1="none";
            sum2="none";
            sum3="none";
            sum4="none";
            sum5="none";

                    Bundle extras = getIntent().getExtras();
                    if (extras != null) {
                        serv = extras.getString("serv");
                        if(serv.equals("rooms")){
                            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.hroom_back));
                            lst_imv.setImageResource(R.drawable.rooms_ico);
                            reg=extras.getString("reg");
                            nbdays=extras.getString("nbdays");
                            arrday=extras.getString("arrday");
                            nbk1=extras.getString("nbk1");
                            nba1=extras.getString("nba1");
                            nbk2=extras.getString("nbk2");
                            nba2=extras.getString("nba2");
                            nbk3=extras.getString("nbk3");
                            nba3=extras.getString("nba3");
                            nbk4=extras.getString("nbk4");
                            nba4=extras.getString("nba4");
                            nbk5=extras.getString("nbk5");
                            nba5=extras.getString("nba5");
                            arrtime="none";
                            nbper="none";
                            if(!nbk1.equals("none")&&!nba1.equals("none"))
                                sum1=String.valueOf(Integer.parseInt(nbk1)+Integer.parseInt(nba1));
                            else
                                sum1="none";

                            if(!nbk2.equals("none")&&!nba2.equals("none"))
                                sum2=String.valueOf(Integer.parseInt(nbk2)+Integer.parseInt(nba2));
                            else
                                sum2="none";

                            if(!nbk3.equals("none")&&!nba3.equals("none"))
                                sum3=String.valueOf(Integer.parseInt(nbk3)+Integer.parseInt(nba3));
                            else
                                sum3="none";

                            if(!nbk4.equals("none")&&!nba4.equals("none"))
                                sum4=String.valueOf(Integer.parseInt(nbk4)+Integer.parseInt(nba4));
                            else
                                sum4="none";

                            if(!nbk5.equals("none")&&!nba5.equals("none"))
                                sum5=String.valueOf(Integer.parseInt(nbk5)+Integer.parseInt(nba5));
                            else
                                sum5="none";

                        }else if(serv.equals("swimp")){
                            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.swimp_back));
                            lst_imv.setImageResource(R.drawable.swimp_ico);
                            //Toast.makeText(getBaseContext(),"swimp",Toast.LENGTH_LONG).show();
                            reg=extras.getString("reg");
                            nbk1=extras.getString("nbk1");
                            nba1=extras.getString("nba1");
                            arrday=extras.getString("arrday");
                            nbdays="none";
                            arrtime="none";
                            nbper="none";

                        }else if(serv.equals("restau")){
                            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.restau_back));
                            lst_imv.setImageResource(R.drawable.restau_ico);
                            reg=extras.getString("reg");
                            arrtime=extras.getString("arrtime");
                            nbper=extras.getString("nbpers");
                            nbdays="none";
                            arrday="none";
                        }else{
                            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.celebh_bak));
                            lst_imv.setImageResource(R.drawable.celebhall_ico);
                            reg=extras.getString("reg");
                            arrday=extras.getString("arrday");
                            nbper=extras.getString("nbpers");
                            arrtime="none";
                            nbdays="none";
                        }


                        Load_Hotels_List();

                    }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

            Intent show=new Intent(getBaseContext(),Show_reserv_offer_detail.class);
            show.putExtra("serv2",serv);
            show.putExtra("reg",reg);
            show.putExtra("arrday",arrday);
            show.putExtra("nbdays",nbdays);
            show.putExtra("nba1",nba1);
            show.putExtra("nbk1",nbk1);


            show.putExtra("nba2",nba2);
            show.putExtra("nbk2",nbk2);
            show.putExtra("nba3",nba3);
            show.putExtra("nbk3",nbk3);
            show.putExtra("nba4",nba4);
            show.putExtra("nbk4",nbk4);
            show.putExtra("nba5",nba5);
            show.putExtra("nbk5",nbk5);

            show.putExtra("sum1",nba2);
            show.putExtra("sum2",nbk2);
            show.putExtra("sum3",nba3);
            show.putExtra("sum4",nbk3);
            show.putExtra("sum5",nba4);

            show.putExtra("arrtime",arrtime);
            show.putExtra("nbpers",nbper);
            show.putExtra("price",price.get(i));
            show.putExtra("idhotel",idhotel.get(i));
            show.putExtra("rating",rating.get(i));
            show.putExtra("hotelname",name.get(i));

            startActivity(show);
        }});
        }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

        }





    private void Load_Hotels_List(){
        progressDialog = ProgressDialog.show(Show_result.this, "",
                "Searching, Please wait...", true);
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/Get_HotelsList_Infos.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nbhotels=0;
                try{
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    nbhotels=jsonArray.length();

                    if(nbhotels>0)  {
                        Drawable d= ResourcesCompat.getDrawable(getResources(), R.drawable.hotelnoimg, null);
                        for(int i = 0;i<nbhotels;i++)
                            image.add(drawableToBitmap(d));
                        list.setVisibility(View.VISIBLE);
                        emptymsg.setVisibility(View.GONE);
                        String imname;
                        for (int i = 0; i < nbhotels; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            idhotel.add(explrObject.getString("idhotel"));
                            name.add(explrObject.getString("hotelname"));
                            rating.add(explrObject.getString("rating"));
                            price.add(explrObject.getString("price"));
                            imname=explrObject.getString("imgname");
                            imgname.add(imname);
                            //Toast.makeText(getBaseContext(),explrObject.getString("imgname"),Toast.LENGTH_SHORT).show();
                            if(!imname.equals("none"))
                               loadImage(i,imname);
                            }

                        try{
                            final Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    build_list();
                                    progressDialog.dismiss();
                                }
                            }, 5000);

                        }catch(RuntimeException e){
                            e.printStackTrace();
                        }


                    }else{
                        progressDialog.dismiss();
                        list.setVisibility(View.GONE);
                        emptymsg.setVisibility(View.VISIBLE);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                    progressDialog.dismiss();
                    list.setVisibility(View.GONE);
                    emptymsg.setVisibility(View.VISIBLE);
                }
            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("serv",serv);
                params.put("reg",reg);
                params.put("arrday",arrday);
                params.put("nbdays",nbdays);
                params.put("sum1",sum1);
                params.put("sum2",sum2);
                params.put("sum3",sum3);
                params.put("sum4",sum4);
                params.put("sum5",sum5);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }


    private void loadImage(final int i,final String imgname){

        String url =Server_Host_Constant.Host+"/hotelsbookingsapp/IMG_Hotels/"+imgname;
        ImageRequest imgreq=new ImageRequest(
                url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                image.set(i,response);
            }
        }, 0,0, null, Bitmap.Config.RGB_565, null);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(imgreq);
    }
    private void build_list(){
        try{
        adapter = new Result_list_adapter(this, rating, name, price,image);
        list.setAdapter(adapter);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }




    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    }


