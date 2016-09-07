package com.example.riadh.hotelsbookings.AdminPack;

import android.app.*;
import android.content.*;
import android.os.*;

import android.support.v7.app.AlertDialog;

import android.view.*;
import android.widget.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;
import com.example.riadh.hotelsbookings.R;

import java.util.*;

/**
 * Created by riadh on 8/21/2016.
 */

public class AddHotel extends Activity {
    Spinner region,rating,restaurent,parking,swimmingpool,celebrationhall,wifi,gym,gamesroom,helicopter;
    EditText name, tel, website, nightp,restaup,swimpp,celbhp;
    TableRow restaup_r,swimpp_r,celbhp_r;
    Button save;
    String addh_region,addh_name,addh_rating,addh_tel,addh_resturent,addh_website,addh_parking,
           addh_swimmingpool,addh_wifi,addh_gamesroom,addh_gym,addh_celbrationhall,addh_helicopter,
           addh_night_price,addh_restaut_price,addh_swimp_price,addh_celebh_price;



    private void load_infos_and_verify(){
        String msg="";
//        addh_region="";
//        addh_name="";
//        addh_rating="";
//        addh_tel="";
//        addh_website="";
//        addh_parking="";
//        addh_swimmingpool="";
//        addh_gamesroom="";
//        addh_gym="";
//        addh_celbrationhall="";
//        addh_helicopter="";
//        addh_price="";

        addh_region=region.getSelectedItem().toString();
        addh_name=name.getText().toString().trim();
        addh_rating=rating.getSelectedItem().toString();
        addh_tel=tel.getText().toString();
        addh_resturent=restaurent.getSelectedItem().toString();
        addh_website=website.getText().toString().toLowerCase();
        addh_parking=parking.getSelectedItem().toString();
        addh_wifi=wifi.getSelectedItem().toString();
        addh_swimmingpool=swimmingpool.getSelectedItem().toString();
        addh_gamesroom=gamesroom.getSelectedItem().toString();
        addh_gym=gym.getSelectedItem().toString();

        addh_celbrationhall=celebrationhall.getSelectedItem().toString();//spinner
        addh_helicopter=helicopter.getSelectedItem().toString();

        addh_night_price=nightp.getText().toString();
        addh_celebh_price=celbhp.getText().toString();
        addh_restaut_price=restaup.getText().toString();
        addh_swimp_price=swimpp.getText().toString();




        float per_price=0;
             try{
                  per_price=Float.parseFloat(addh_night_price);
                 addh_night_price=String.valueOf(per_price);
                } catch (NumberFormatException ex) {
                    per_price=0;
                    addh_night_price="none";
                }
        if(per_price<=0)
        msg+="*Night price is not authorised\n";

        per_price=0;
        if(restaurent.getSelectedItem().toString().toLowerCase().equals("yes")){

            try{
                per_price=Float.parseFloat(addh_restaut_price);
                addh_restaut_price=String.valueOf(per_price);
            } catch (NumberFormatException ex) {
                per_price=0;
                addh_restaut_price="none";
            }
            if(per_price<=0)
                msg+="*Restaurent table price is not authorised\n";
        }else
             addh_restaut_price="none";

        per_price=0;
        if(swimmingpool.getSelectedItem().toString().toLowerCase().equals("yes")){

            try{
                per_price=Float.parseFloat(addh_swimp_price);
                addh_swimp_price=String.valueOf(per_price);
            } catch (NumberFormatException ex) {
                per_price=0;
                addh_swimp_price="none";
            }
            if(per_price<=0)
                msg+="*Swimming pool price is not authorised\n";
        }else
            addh_swimp_price="none";

        per_price=0;
        if(celebrationhall.getSelectedItem().toString().toLowerCase().equals("yes")){

            try{
                per_price=Float.parseFloat(addh_celebh_price);
                addh_celebh_price=String.valueOf(per_price);
            } catch (NumberFormatException ex) {
                per_price=0;
                addh_swimp_price="none";
            }
            if(per_price<=0)
                msg+="*Restaurent table price is not authorised\n";
        }else
            addh_celebh_price="none";

        msg+=MySingleton.checkHotelname(addh_name);
        msg+=MySingleton.checkHotelwebsite(addh_website);
        msg+=MySingleton.checkPhonenumber(addh_tel);

        if(msg.equals("")){

         String url= Server_Host_Constant.Host+"/hotelsbookingsapp/addhotel.php";
            StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getBaseContext(),response, Toast.LENGTH_LONG).show();
                    if(response.contains("created")){
                        finish();
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
                    params.put("addh_region",addh_region);
                    params.put("addh_name",addh_name);
                    params.put("addh_rating",addh_rating);
                    params.put("addh_tel",addh_tel);
                    params.put("addh_restaurent",addh_resturent);
                    params.put("addh_website",addh_website);
                    params.put("addh_wifi",addh_wifi);
                    params.put("addh_parking",addh_parking);
                    params.put("addh_swimmingpool",addh_swimmingpool);
                    params.put("addh_gamesroom",addh_gamesroom);
                    params.put("addh_gym",addh_gym);
                    params.put("addh_celbrationhall",addh_celbrationhall);
                    params.put("addh_helicopter",addh_helicopter);
                    params.put("addh_nightp",addh_night_price);
                    params.put("addh_restautp",addh_restaut_price);
                    params.put("addh_swimpp",addh_swimp_price);
                    params.put("addh_partyhp",addh_celebh_price);


                    return params;
                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);


        }else{
            new AlertDialog.Builder(AddHotel.this)
                    .setTitle("Sorry, there is some errors !")
                    .setIcon(R.drawable.caution)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
        }
//        Toast.makeText(getBaseContext(),addh_region,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.addhotel);
        region=(Spinner)findViewById(R.id.addhregsp);
        rating=(Spinner)findViewById(R.id.addhratingsp);
        restaurent=(Spinner)findViewById(R.id.addhrestausp);
        parking=(Spinner)findViewById(R.id.addhparksp);
        swimmingpool=(Spinner)findViewById(R.id.addhspsp);
        celebrationhall=(Spinner)findViewById(R.id.addhchsp);
        gym=(Spinner)findViewById(R.id.addhgymsp);
        gamesroom=(Spinner)findViewById(R.id.addhgrsp);
        helicopter=(Spinner)findViewById(R.id.addhhelsp);
        wifi=(Spinner)findViewById(R.id.addhwifisp);

        name=(EditText)findViewById(R.id.addhname);
        tel=(EditText)findViewById(R.id.addhtel);
        website=(EditText)findViewById(R.id.addhwebsite);

        swimpp_r=(TableRow)findViewById(R.id.addh_swimp_row);
        celbhp_r=(TableRow)findViewById(R.id.addh_celebh_row);
        restaup_r=(TableRow)findViewById(R.id.addh_restaut_row);

        nightp=(EditText)findViewById(R.id.addh_night_p);
        swimpp=(EditText)findViewById(R.id.addh_swimp_p);
        celbhp=(EditText)findViewById(R.id.addh_celebh_p);
        restaup=(EditText)findViewById(R.id.addh_restaut_p);

        save=(Button)findViewById(R.id.addhsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_infos_and_verify();
            }
        });

        swimmingpool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {   if(swimmingpool.getSelectedItem().toString().toLowerCase().equals("no"))
                    swimpp_r.setVisibility(View.GONE);
                else
                swimpp_r.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        celebrationhall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {   if(celebrationhall.getSelectedItem().toString().toLowerCase().equals("no"))
                celbhp_r.setVisibility(View.GONE);
            else
                celbhp_r.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        restaurent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {   if(restaurent.getSelectedItem().toString().toLowerCase().equals("no"))
                restaup_r.setVisibility(View.GONE);
            else
                restaup_r.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }
    }
}
