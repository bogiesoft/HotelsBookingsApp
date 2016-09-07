package com.example.riadh.hotelsbookings.AdminPack;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.MySingleton;
import com.example.riadh.hotelsbookings.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by riadh on 8/22/516.
 */



public class Addhr42per extends Activity {

    ListView MyListV;
    TextView isempty;
    Button addbtn,delbtn,savebtn;
    String MyhotelID;
    ArrayList<String> list = new ArrayList<String>();
    ProgressDialog progressDialog;
//    float roomprice;
    ArrayAdapter<String> adapter;

void onload(){
    setContentView(R.layout.addhr42per);

    MyListV=(ListView)findViewById(R.id.addr42perlv);
    isempty=(TextView)findViewById(R.id.addr42p_empty);

    addbtn= (Button) findViewById(R.id.addr42p_add);
    delbtn=(Button)findViewById(R.id.addr42p_del);
    savebtn=(Button)findViewById(R.id.addr42p_save);

    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        MyhotelID = extras.getString("HOTEL_ID");
    }

//        final Dialog dialog = new Dialog(Addhr42per.this);
//        dialog.setContentView(R.layout.getroom_price);
//        dialog.setTitle("Single room price for one night.");
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);

//        final EditText pricetxt=(EditText)dialog.findViewById(R.id.addhrprice);
//        Button submit =(Button)dialog.findViewById(R.id.getpsubmit);
//        Button quit=(Button)dialog.findViewById(R.id.quitadding);
//        roomprice=-1;
//
//        quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                finish();
//            }
//        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    roomprice=Float.parseFloat(pricetxt.getText().toString());
//                } catch (NumberFormatException ex) {
//                    roomprice=0;
//                }
//                if(roomprice<=0)
//                    new AlertDialog.Builder(Addhr42per.this)
//                            .setTitle("Oops !")
//                            .setIcon(R.drawable.caution)
//                            .setMessage("Sorry, this price is not authorised !")
//                            .setCancelable(false)
//                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                }
//                            }).create().show();
//                else
//                    dialog.dismiss();
//
//            }
//        });
//
//        dialog.show();
//

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText edit = (EditText) findViewById(R.id.txtItem);
            list.add(edit.getText().toString());
            edit.setText("");
            isempty.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            MyListV.setAdapter(adapter);
        }
    };


    addbtn.setOnClickListener(listener);

    delbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(list.size()>0){
                int sz=list.size();
                list.remove(sz-1);
                if(sz==1)
                    isempty.setVisibility(View.VISIBLE);

                adapter.notifyDataSetChanged();
                MyListV.setAdapter(adapter);
            }
        }
    });

    savebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressDialog = ProgressDialog.show(Addhr42per.this, "",
                    "Loading. Please wait...", true);

            for(int i=0;i<list.size();i++)
                add1room42per(list.get(i));
            progressDialog.dismiss();
            finish();
        }
    });

}
    private void add1room42per(final String num){
        String url= Server_Host_Constant.Host+"/hotelsbookingsapp/addhotelsrooms.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("Error")){
                    Toast.makeText(getBaseContext(),response, Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getBaseContext(),"Operation completed successfully", Toast.LENGTH_LONG).show();

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
                params.put("numroom",num);
                params.put("idhotel", MyhotelID);
                params.put("capacity","2");
                //params.put("price",String.valueOf(roomprice));
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onload();
                        }
                    }, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

    }

}
