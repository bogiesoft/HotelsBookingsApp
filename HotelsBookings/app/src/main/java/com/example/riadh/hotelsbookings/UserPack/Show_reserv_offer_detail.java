package com.example.riadh.hotelsbookings.UserPack;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.support.v4.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;

import com.example.riadh.hotelsbookings.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by riadh on 8/30/2016.
 */
public class Show_reserv_offer_detail extends Activity {
    RatingBar rating ;
    TextView unitprice,reservcost,phonenumber,hotlname,nbtables_txt;
    ImageButton call,next,prev;
    Button book;
    ImageView srod_im,curr_img,wifi_check,park_check,games_r_check,gym_check,
            helico_check,swimp_chk,restau_chk;
    WebView findus_map;
    LinearLayout main_l,GoogleMaps_l, wifi_l,park_l,swimp_l,games_r_l,gym_l,helico_l,restau_l;
    ArrayList<String> img_names;
    int imgptr,nbimages,nbtables;
    TableRow nbtables_r;
    ProgressDialog progressDialog;

    String serv,reg,arrday,nbdays,nbk1,nba1,nbk2,nba2,nbk3,nba3,nbk4,nba4,nbk5,nba5,
            arrtime,nbper,sum1,sum2,sum3,sum4,sum5,uprice,idhotel,ratestatus,hname,cost;
@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    try{
        final Handler start = new Handler();

        start.postDelayed(new Runnable() {
            @Override
            public void run() {
    try{
        final Handler start = new Handler();

        start.postDelayed(new Runnable() {
            @Override
            public void run() {
    setContentView(R.layout.show_reserv_offer_details);
    main_l=(LinearLayout)findViewById(R.id.srod_main_l);
    srod_im=(ImageView)findViewById(R.id.srod_imv);
    rating =(RatingBar)findViewById(R.id.srod_rating);


    unitprice=(TextView)findViewById(R.id.srod_unitp);
    reservcost=(TextView)findViewById(R.id.srod_rcost);
    phonenumber=(TextView)findViewById(R.id.srod_tel);
    hotlname=(TextView)findViewById(R.id.srod_hname);
    call=(ImageButton)findViewById(R.id.srod_call);
    next=(ImageButton)findViewById(R.id.srod_next);
    prev=(ImageButton) findViewById(R.id.srod_prev);
    book=(Button)findViewById(R.id.srod_book);
    curr_img=(ImageView)findViewById(R.id.srod_imgs);
    findus_map=(WebView)findViewById(R.id.srod_hpos);
    nbtables_r=(TableRow)findViewById(R.id.srod_nb_tables_tr);
    nbtables_txt=(TextView)findViewById(R.id.srod_txtnbtables);
    GoogleMaps_l=(LinearLayout)findViewById(R.id.srod_google_maps_l);

    wifi_l=(LinearLayout)findViewById(R.id.srod_wifi_l);
    park_l=(LinearLayout)findViewById(R.id.srod_park_l);
    games_r_l=(LinearLayout)findViewById(R.id.srod_gamesr_l);
    gym_l=(LinearLayout)findViewById(R.id.srod_gym_l);
    helico_l=(LinearLayout)findViewById(R.id.srod_helico_l);
    swimp_l=(LinearLayout)findViewById(R.id.srod_swimp_l);
    restau_l=(LinearLayout)findViewById(R.id.srod_restau_l);

    restau_chk=(ImageView)findViewById(R.id.srod_restau_chk);
    swimp_chk=(ImageView)findViewById(R.id.srod_swimp_chk);
    gym_check=(ImageView)findViewById(R.id.srod_gym_chk);
    games_r_check=(ImageView)findViewById(R.id.srod_gamesr_chk);
    helico_check=(ImageView)findViewById(R.id.srod_helico_chk);
    wifi_check=(ImageView)findViewById(R.id.srod_wifi_chk);
    park_check=(ImageView)findViewById(R.id.srod_park_chk);

    imgptr=0;
    nbimages=0;
    nbtables=0;
    img_names=new ArrayList<String>();
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        serv = extras.getString("serv2");
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
        sum1=extras.getString("sum1");
        sum2=extras.getString("sum2");
        sum3=extras.getString("sum3");
        sum4=extras.getString("sum4");
        sum5=extras.getString("sum5");
        arrtime=extras.getString("arrtime");
        nbper=extras.getString("nbpers");
        uprice=extras.getString("price");
        idhotel=extras.getString("idhotel");
        ratestatus=extras.getString("rating");
        hname=extras.getString("hotelname");

        if(serv.equals("rooms")){
            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.hroom_back));
            srod_im.setImageResource(R.drawable.rooms_ico);

        }else if(serv.equals("swimp")){
            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.swimp_back));
            srod_im.setImageResource(R.drawable.swimp_ico);

        }else if(serv.equals("restau")){
            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.restau_back));
            srod_im.setImageResource(R.drawable.restau_ico);
        }else{
            main_l.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.celebh_bak));
            srod_im.setImageResource(R.drawable.celebhall_ico);
        }

        progressDialog = ProgressDialog.show(Show_reserv_offer_detail.this, "",
                "Loading data, Please wait...", true);
        hotlname.setText(hname);
        unitprice.setText(uprice);



        get_hotel_serv_infos();
        final Handler handler = new Handler();
        try{

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {



                    rating.setRating(ratestatus.length());
                    if(serv.equals("rooms")){
                        cost=calc_hotelrooms_reserv_cost(nbdays,uprice,nba1,nbk1,
                                nba2,nbk2,nba3,nbk3,nba4,nbk4,nba5,nbk5
                        );


                        reservcost.setText(cost);




                    }else if(serv.equals("swimp")){
                        cost=clac_swimp_reserv_cost(uprice,nba1,nbk1);

                        reservcost.setText(cost);


                    }else if(serv.equals("restau")){
                        nbtables_r.setVisibility(View.VISIBLE);

                        cost=calc_restau_reserv_cost(uprice,nbper);

                        reservcost.setText(cost);
                        nbtables_txt.setText(String.valueOf(nbtables));

                    } else{
                        reservcost.setText(uprice);
                    }
                    getImagesList();

                    String place=reg+"+"+hname;
                    String url="https://www.google.com/maps/place/"+place;
                    findus_map.setWebViewClient(new WebViewClient());
                    WebSettings webSettings = findus_map.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    findus_map.loadUrl(url);
                    final Handler getter = new Handler();

                    getter.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String link =findus_map.getUrl();
                    if(link.contains("/place/"))
                        GoogleMaps_l.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                progressDialog.dismiss();
                }
            }, 500);

        }catch(RuntimeException e){
            e.printStackTrace();
        }

    }




    call.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phonenumber.getText().toString()));
                startActivity(callIntent);
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
            }
        }
    });

    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ++imgptr;
            imgptr=(imgptr+nbimages)%nbimages;
            getImagesList();

        }
    });

    prev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            --imgptr;
            imgptr=(imgptr+nbimages)%nbimages;
            getImagesList();
        }
    });

    book.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Show_reserv_offer_detail.this);

            builder.setTitle("Confirmation of the booking operation");
            builder.setMessage("Are you sure to perform the transaction operation ?");

            builder.setPositiveButton("Yes book now", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
  /*                    String msg ="Reg= "+reg+"\n Serv= "+serv+"\n nbdays= "+ nbdays+"\n"+
                              "\nnbk1= "+nbk1+"\nnba1="+nba1+"\nnbk2= "+nbk2+"\nnba2= "+nba2+"\nnka3= "+
                        nbk3+"\nnba3= "+nba3+"\nnbk4= "+nbk4+"\nnba4= "+nba4+"\nnbk5= "+nbk5+"\nnba5= "+nba5+
                              "\narrtime= "+arrtime+"\nnbpers= "+nbper;

                    new AlertDialog.Builder(Show_reserv_offer_detail.this)
                            .setTitle("Booking operation failed !")
                            .setIcon(R.drawable.failure_ico)
                            .setMessage(msg)
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).create().show();
*/

                    BookNow();
                }
            });

            builder.setNegativeButton("No thanks", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });

            builder.show();
        }
    });

}
}, 100);

        }catch(RuntimeException e){
        e.printStackTrace();
        }
}
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

}

    private void loadImage(final String imgname){

            String url = Server_Host_Constant.Host+"/hotelsbookingsapp/IMG_Hotels/"+imgname;
            ImageRequest imgreq=new ImageRequest(
                    url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {

                    curr_img.setImageBitmap(response);
                }
            }, 0,0, null, Bitmap.Config.RGB_565, null);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(imgreq);
        }

    String calc_hotelrooms_reserv_cost(String nbdays, String uprice,String na1,String nk1,String na2,String nk2,String na3,
                                       String nk3,String na4,String nk4,String na5,String nk5){

        float cost=0,up=Float.parseFloat(uprice);
        int nbd=Integer.parseInt(nbdays);
        if(!na1.equals("none"))
        cost+=(Float.parseFloat(na1))*nbd*up;
            if(!na2.equals("none"))
                cost+=(Float.parseFloat(na2))*nbd*up;
                if(!na3.equals("none"))
                    cost+=(Float.parseFloat(na3))*nbd*up;
                    if(!na4.equals("none"))
                        cost+=(Float.parseFloat(na4))*nbd*up;
                        if(!na5.equals("none"))
                            cost+=(Float.parseFloat(na5))*nbd*up;

        if(!nk1.equals("none"))
            cost+=(Float.parseFloat(nk1))*nbd*(up/2);
        if(!nk2.equals("none"))
            cost+=(Float.parseFloat(nk2))*nbd*(up/2);
        if(!nk3.equals("none"))
            cost+=(Float.parseFloat(nk3))*nbd*(up/2);
        if(!nk4.equals("none"))
            cost+=(Float.parseFloat(nk4))*nbd*(up/2);
        if(!nk5.equals("none"))
            cost+=(Float.parseFloat(nk5))*nbd*(up/2);

        return  String.valueOf(cost) ;

    }

    String clac_swimp_reserv_cost(String uprice,String na, String nk) {
        float up=Float.parseFloat(uprice);
            int nba;
    try{
        nba=Integer.parseInt(na);
    }catch (NumberFormatException e){
        nba=0;
    }
        int nbk;

        try{
            nbk=Integer.parseInt(nk);
        }catch (NumberFormatException e){
            nbk=0;
        }

        float cost=up*(nba)+(up/2)*nbk;
        return String.valueOf(cost);
    }

    String calc_restau_reserv_cost(String uprice, String nbpers){
        float up=Float.parseFloat(uprice);
        int nbp=Integer.parseInt(nbpers);
        float cost;
        nbtables=((int)(nbp/4));
        if(nbp%4!=0)
            nbtables++;
        cost = nbtables*up;
        return String.valueOf(cost);
    }

    private void getImagesList(){
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getMyHotelImages.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               nbimages=0;
                try{
                    img_names.clear();
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    nbimages=jsonArray.length();
                    if(nbimages>0)  {
                        for (int i = 0; i < nbimages; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            img_names.add(explrObject.getString("imgname"));
                        }
                        loadImage(img_names.get(imgptr));
                    }else{
                        next.setVisibility(View.GONE);
                        prev.setVisibility(View.GONE);
                        curr_img.setVisibility(View.GONE);
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
                params.put("idhotel", idhotel);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    private void BookNow(){
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/Booknow.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.toLowerCase().contains("success"))
                    ShowSuccessDialog(response);
                else
                    ShowFailureDialog(response);

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
                params.put("idhotel", idhotel);
                params.put("serv", serv);
                params.put("iduser", AccountInfos.getUserid());
                params.put("arrday", arrday);
                params.put("nbdays", nbdays);
                params.put("arrtime",arrtime );
                params.put("nbpers", nbper);
                params.put("nba1", nba1);
                params.put("nba2", nba2 );
                params.put("nba3", nba3);
                params.put("nba4", nba4);
                params.put("nba5", nba5);
                params.put("nbk1", nbk1);
                params.put("nbk2", nbk2);
                params.put("nbk3", nbk3);
                params.put("nbk4", nbk4);
                params.put("nbk5", nbk5);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    private void get_hotel_serv_infos(){
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/get_hotel_serv_infos.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsnobject = new JSONObject(response);
                    update_form(jsnobject.getString("tel"),jsnobject.getString("wifi"),
                            jsnobject.getString("parking"),jsnobject.getString("swimming_pool"),
                            jsnobject.getString("restaurent"),jsnobject.getString("gym"), jsnobject.getString("games_room"),
                            jsnobject.getString("helicopter_landing"));

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
                params.put("idhotel", idhotel);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    private void ShowSuccessDialog(String msg){
        new AlertDialog.Builder(Show_reserv_offer_detail.this)
                .setTitle("Booking operation completed successfully")
                .setIcon(R.drawable.tick_green)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }

    private void ShowFailureDialog(String msg){
        new AlertDialog.Builder(Show_reserv_offer_detail.this)
                .setTitle("Booking operation failed !")
                .setIcon(R.drawable.failure_ico)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }

    void update_form(String tel, String wifi,String parking,String swimp,
                     String restau,String gym, String games_r, String helico){

        phonenumber.setText(tel);

        if(serv.equals("rooms")){
            if(wifi.toLowerCase().equals("yes"))
                this.wifi_check.setImageResource(R.drawable.checked_ico);

            if(parking.toLowerCase().equals("yes"))
                this.park_check.setImageResource(R.drawable.checked_ico);

            if(swimp.toLowerCase().equals("yes"))
                this.swimp_chk.setImageResource(R.drawable.checked_ico);

            if(restau.toLowerCase().equals("yes"))
                this.restau_chk.setImageResource(R.drawable.checked_ico);

            if(gym.toLowerCase().equals("yes"))
                this.gym_check.setImageResource(R.drawable.checked_ico);

            if(games_r.toLowerCase().equals("yes"))
                this.games_r_check.setImageResource(R.drawable.checked_ico);

            if(helico.toLowerCase().equals("yes"))
                this.helico_check.setImageResource(R.drawable.checked_ico);

        }else {
            if(wifi.toLowerCase().equals("yes"))
                this.wifi_check.setImageResource(R.drawable.checked_ico);
            if(parking.toLowerCase().equals("yes"))
                this.park_check.setImageResource(R.drawable.checked_ico);

                restau_l.setVisibility(View.GONE);
                swimp_l.setVisibility(View.GONE);
                gym_l.setVisibility(View.GONE);
                games_r_l.setVisibility(View.GONE);
                helico_l.setVisibility(View.GONE);
        }
    }

}

