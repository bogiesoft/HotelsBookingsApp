package com.example.riadh.hotelsbookings.AdminPack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.AccountInfos;
import com.example.riadh.hotelsbookings.DBConnection.MySingleton;
import com.example.riadh.hotelsbookings.R;

import org.json.*;

import java.util.*;

/**
 * Created by riadh on 8/21/2016.
 */
public class ManageHotelRooms extends Activity {
    private static Spinner MyhotelsSp;
    Button  editHRooms,addhr41per,addhr42per,addhr43per,addhr44per,delhotelrooms;
    private static int size;
    private static String[] HotelsIDs;
    ProgressDialog progressDialog;
    private static List<String> spinnerArray =  new ArrayList<String>();

    private void build_sp(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MyhotelsSp.setAdapter(adapter);
        if(size>0)
        MyhotelsSp.setSelection(0,true);
    }

    private void Load_MyHotelsList(){

        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getMyHotelsList.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                size=0;
                try{
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    size=jsonArray.length();
                    if(size>0)  {
                    spinnerArray.clear();
                    HotelsIDs=new String[size];
                    for (int i = 0; i < size; i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        HotelsIDs[i]=explrObject.getString("id");
                        spinnerArray.add(i,explrObject.getString("name"));
                        build_sp();
                    }
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
                params.put("adminid",AccountInfos.getUserid());
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.manage_hotel_rooms);
        MyhotelsSp =(Spinner)findViewById(R.id.addhrhsp);
        editHRooms=(Button)findViewById(R.id.edithrooms);
        addhr41per=(Button)findViewById(R.id.addhr41per);
        addhr42per=(Button)findViewById(R.id.addhr42per);
        addhr43per=(Button)findViewById(R.id.addhr43per);
        addhr44per=(Button)findViewById(R.id.addhr44per);
        delhotelrooms=(Button)findViewById(R.id.hmgr_delhr);
        progressDialog = ProgressDialog.show(ManageHotelRooms.this, "",
                    "Loading. Please wait...", true);
        
        Load_MyHotelsList();
        progressDialog.dismiss();
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(size==0){
                    editHRooms.setEnabled(false);
                    addhr41per.setEnabled(false);
                    addhr42per.setEnabled(false);
                    addhr43per.setEnabled(false);
                    addhr44per.setEnabled(false);
                    delhotelrooms.setEnabled(false);
                }

                }
             }, 1000);
                editHRooms.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent=new Intent(getBaseContext(),EditMyHotelRooms.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        intent.putExtra("HOTEL_NAME",MyhotelsSp.getSelectedItem().toString());
                        startActivity(intent);
                    }
                });


                addhr41per.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(),Addhr41per.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        startActivity(intent);
                    }
                });

                addhr42per.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(),Addhr42per.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        startActivity(intent);
                    }
                });

                addhr43per.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(),Addhr43per.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        startActivity(intent);
                    }
                });

                addhr44per.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(),Addhr44per.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        startActivity(intent);
                    }
                });

                delhotelrooms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(),DeleteHotelRooms.class);
                        intent.putExtra("HOTEL_ID",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
                        intent.putExtra("HOTEL_NAME",MyhotelsSp.getSelectedItem().toString());
                        startActivity(intent);
                    }
                });

    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

    }

    }


