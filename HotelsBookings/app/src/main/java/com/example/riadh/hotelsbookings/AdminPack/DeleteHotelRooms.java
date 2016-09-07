package com.example.riadh.hotelsbookings.AdminPack;
import android.app.Activity;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;


import org.json.*;

import java.util.*;

/**
 * Created by riadh on 8/24/2016.
 */
public class DeleteHotelRooms  extends Activity{
    TextView title,emptylist,allselect;
    Button delete,back;
    LinearLayout roomslist_l;
    CheckBox selectall;
    ListView roomslist;
    String MyHotelID,MyHotelName;
    ArrayAdapter<String> adapter;
    ArrayList<String> NumRooms;
    ArrayList<String> selectedrooms;
    //ProgressDialog progressDialog;
    Boolean fail;
    int size;
    void onload(){
        size=0;
        setContentView(R.layout.delete_hotel_rooms);
        title=(TextView)findViewById(R.id.delr_title);
        delete=(Button)findViewById(R.id.delr_del);
        back=(Button)findViewById(R.id.delr_back);
        emptylist=(TextView)findViewById(R.id.delr_emptyl);
        roomslist_l=(LinearLayout)findViewById(R.id.delr_list_l);
        allselect=(TextView)findViewById(R.id.delr_sel_all);
        selectall=(CheckBox)findViewById(R.id.delr_sall);
        roomslist=(ListView)findViewById(R.id.deletable_rooms_list);
        roomslist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        fail=false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            MyHotelID=extras.getString("HOTEL_ID");
            MyHotelName = extras.getString("HOTEL_NAME");
            title.setText("Deletetable rooms for "+MyHotelName);
        }
        NumRooms=new ArrayList<String>();
        selectedrooms=new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,NumRooms);
        get_Deletable_Rooms();


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = roomslist.getCheckedItemPositions();
                int len = roomslist.getCount();
                Handler handler;
                for (int i = len-1; i >=0 ; i--)
                    if (checked.get(i))
                        delete_unused_room(adapter.getItem(i));

                if(!fail)
                    Toast.makeText(getBaseContext(),"Operation completed successfully", Toast.LENGTH_SHORT).show();
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        get_Deletable_Rooms();
                    }

                }, 200);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(selectall.isChecked()){
                    allselect.setText("Unselect all");
                    for(int i=0;i<NumRooms.size();i++)
                        roomslist.setItemChecked(i,true);
                }
                else{
                    allselect.setText("Select all");
                    for(int i=0;i<NumRooms.size();i++)
                        roomslist.setItemChecked(i,false);
                }
            }
        });

        roomslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(roomslist.getCheckedItemCount()==adapter.getCount()){
                    selectall.setChecked(true);
                    allselect.setText("Unselect all");
                } else{
                    selectall.setChecked(false);
                    allselect.setText("Select all");
                }


            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
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

void delete_unused_room(final String numroom){
    String url= Server_Host_Constant.Host+"/hotelsbookingsapp/deleteroom.php";
    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if(response.contains("fail")){
                fail=true;
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
            params.put("idhotel",MyHotelID);
            params.put("idroom",numroom);
            return params;
        }
    };

    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
}

void get_Deletable_Rooms(){


    String url=Server_Host_Constant.Host+"/hotelsbookingsapp/getDeletableRooms.php";
    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try{
                NumRooms.clear();
                roomslist.clearChoices();
                JSONObject jsnobject = new JSONObject(response);
                JSONArray jsonArray = jsnobject.getJSONArray("result");
                size=jsonArray.length();
                if(size>0)  {

                    for (int i = 0; i < size; i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        NumRooms.add(explrObject.getString("numroom"));
                    }
                    roomslist.setAdapter(adapter);
                }   else
                    empty_list_case();


            }
            catch(JSONException e){
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
            params.put("idhotel",MyHotelID);
            return params;
        }
    };

    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);




        }
void empty_list_case(){
    emptylist.setVisibility(View.VISIBLE);
    selectall.setVisibility(View.GONE);
    allselect.setVisibility(View.GONE);
    delete.setEnabled(false);
}
}
