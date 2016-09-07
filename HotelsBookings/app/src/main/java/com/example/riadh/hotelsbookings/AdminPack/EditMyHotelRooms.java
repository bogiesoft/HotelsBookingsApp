package com.example.riadh.hotelsbookings.AdminPack;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.android.volley.*;

import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.MySingleton;
import com.example.riadh.hotelsbookings.R;

import org.json.*;

import java.util.*;

/**
 * Created by riadh on 8/22/2016.
 */
public class EditMyHotelRooms  extends Activity{
    private ArrayList<HashMap> list;
    private ArrayList<String> numroom_t;
    private ArrayList<String> capacity_l;
    private ArrayList<String> occypation_l;
    //private ArrayList<String> night_price_l;
    TextView title,listState;
    ListView lview;
    String MyHotelName;
    String MyHotelID;
//    ProgressDialog progressDialog;
    int size;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.getmyhotelsrooms);
        title=(TextView)findViewById(R.id.edihrtitle);
        Bundle extras = getIntent().getExtras();
        size=0;
        if (extras != null) {
            MyHotelID=extras.getString("HOTEL_ID");
            MyHotelName = extras.getString("HOTEL_NAME");
            title.setText("Edit rooms for "+MyHotelName);
        }

        listState=(TextView)findViewById(R.id.lhr_state);
        lview= (ListView) findViewById(R.id.listview);
        numroom_t=new ArrayList<String>();
        capacity_l=new ArrayList<String>();
        occypation_l=new ArrayList<String>();
        //night_price_l=new ArrayList<String>();

        Load_MyHotelRoomsList();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(size>0)
                buildMylist();

            }

        }, 1000);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                                             final Dialog dialog = new Dialog(EditMyHotelRooms.this);
                                             dialog.setContentView(R.layout.alter_room_dialog);
                                             dialog.setTitle("Modify room number "+ numroom_t.get(i)+" infos.");
                                             TextView rnum=(TextView) dialog.findViewById(R.id.alt_diag_rnum);
                                             rnum.setText(numroom_t.get(i));
                                             dialog.show();
                                             Button cancel=(Button)dialog.findViewById(R.id.alt_diag_cancel);
                                             Button submit=(Button)dialog.findViewById(R.id.alt_diag_submit);

                                             final Spinner cap_sp=(Spinner)dialog.findViewById(R.id.alt_diag_cap_sp);
                                             final Spinner ocp_sp=(Spinner)dialog.findViewById(R.id.alt_diag_roccup_sp);
                                             //final EditText price=(EditText) dialog.findViewById(R.id.alt_diag_rprice);

                                             cap_sp.setSelection(Integer.valueOf(capacity_l.get(i))-1,true);

                                             if(occypation_l.get(i).toLowerCase().equals("yes"))
                                             ocp_sp.setSelection(1,true);
                                             else
                                             ocp_sp.setSelection(0,true);

                                             //price.setText(night_price_l.get(i));

                                             cancel.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     dialog.dismiss();
                                                 }
                                             });

                                             submit.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View view) {
                                                     capacity_l.set(i,cap_sp.getSelectedItem().toString());
                                                     occypation_l.set(i,ocp_sp.getSelectedItem().toString());
//                                                     float roomp;
//                                                        try{
//                                                            roomp=Float.parseFloat(price.getText().toString());
//
//                                                        } catch (NumberFormatException ex) {
//                                                                roomp=0;
//                                                        }

//                                                     if(roomp>0){
                                                         //night_price_l.set(i,price.getText().toString());

                                                         String url=Server_Host_Constant.Host+"/hotelsbookingsapp/UpdateRoomInfos.php";
                                                         StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                                             @Override
                                                             public void onResponse(String response) {
                                                                 if(response.contains("success")){
                                                                     UpdateRoomsList();
                                                                     Toast.makeText(getBaseContext(),response,Toast.LENGTH_SHORT).show();
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
                                                                 params.put("roomnum",numroom_t.get(i));
                                                                 params.put("hotelid",MyHotelID);
                                                                 params.put("capacity",capacity_l.get(i));
                                                                 params.put("isoccup",occypation_l.get(i));
                                                                 //params.put("night_price",night_price_l.get(i));
                                                                 return params;
                                                             }
                                                         };

                                                         MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

                                                         dialog.dismiss();
//                                                     }
//                                                     else
//                                                         new AlertDialog.Builder(EditMyHotelRooms.this)
//                                                                 .setTitle("Oops !")
//                                                                 .setIcon(R.drawable.caution)
//                                                                 .setMessage("Sorry, this price is not authorised !")
//                                                                 .setCancelable(false)
//                                                                 .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                                                                     @Override
//                                                                     public void onClick(DialogInterface dialog, int which) {
//                                                                     }
//                                                                 }).create().show();

                                                 }
                                             });

                                         }
                                     }

        );

    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }
    }

    private void buildMylist(){
        ListviewAdapter adapter = new ListviewAdapter(this, list);
        lview.setAdapter(adapter);
//        progressDialog.dismiss();
    }

    private void UpdateRoomsList(){
        list.clear();
        list = new ArrayList<HashMap>();
        for(int i=0;i<numroom_t.size();i++){
        HashMap temp = new HashMap();
        temp.put(Constant.FIRST_COLUMN,numroom_t.get(i));
        temp.put(Constant.SECOND_COLUMN, capacity_l.get(i));
        temp.put(Constant.THIRD_COLUMN, occypation_l.get(i));
        //temp.put(Constant.FOURTH_COLUMN, night_price_l.get(i));
        list.add(temp);
    }
        buildMylist();
    }

    private void Load_MyHotelRoomsList(){

        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getMyHotelrooms.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsnobject = new JSONObject(response);
                    JSONArray jsonArray = jsnobject.getJSONArray("result");
                    size=jsonArray.length();
                    if(size>0)  {
                        list = new ArrayList<HashMap>();
//                        progressDialog = ProgressDialog.show(EditMyHotelRooms.this, "",
//                                "Loading. Please wait...", true);
                        listState.setVisibility(View.GONE);
                        for (int i = 0; i < size; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            String numroom=explrObject.getString("numroom");
                            String capacity=explrObject.getString("capacity");
                            String isoccupied=explrObject.getString("isoccupied");
                            //String night_price=explrObject.getString("price");
                            numroom_t.add(numroom);
                            capacity_l.add(capacity);
                            occypation_l.add(isoccupied);
                            //night_price_l.add(night_price);
                            HashMap temp = new HashMap();
                            temp.put(Constant.FIRST_COLUMN,numroom);
                            temp.put(Constant.SECOND_COLUMN, capacity);
                            temp.put(Constant.THIRD_COLUMN, isoccupied);
                            //temp.put(Constant.FOURTH_COLUMN, night_price);
                            list.add(temp);
                        }
                    }else
                        listState.setVisibility(View.VISIBLE);
                }catch(JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),"Error while establishing connection with server",Toast.LENGTH_LONG).show();
//                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("idhotel", MyHotelID);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

}
