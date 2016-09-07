package com.example.riadh.hotelsbookings.UserPack;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.CommonPack.BookingsConstant;
import com.example.riadh.hotelsbookings.CommonPack.Bookings_ListAdapter;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;
import com.example.riadh.hotelsbookings.R;
import org.json.*;
import java.util.*;
/**
 * Created by riadh on 9/3/2016.
 */
public class View_My_Bookings extends Activity {
    ImageButton rooms,swimp,restau,celebh;
    int listsize;
    TextView nodatafound,firsttxt,secondtxt,thirdtxt,fouthtxt,fifithtxt,sixthtxt,seventhtxt,eighttxt;
    ListView bookings_lst;
    String serv;
    private ArrayList<HashMap> list;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.view_bookings_4_user);

        rooms=(ImageButton)findViewById(R.id.vmb_rooms);
        swimp=(ImageButton)findViewById(R.id.vmb_swimp);
        restau=(ImageButton)findViewById(R.id.vmb_restau);
        celebh=(ImageButton)findViewById(R.id.vmb_celebh);

        firsttxt=(TextView)findViewById(R.id.vbu_txt1);
        secondtxt=(TextView)findViewById(R.id.vbu_txt2);
        thirdtxt=(TextView)findViewById(R.id.vbu_txt3);
        fouthtxt=(TextView)findViewById(R.id.vbu_txt4);
        fifithtxt=(TextView)findViewById(R.id.vbu_txt5);
        sixthtxt=(TextView)findViewById(R.id.vbu_txt6);
        seventhtxt=(TextView)findViewById(R.id.vbu_txt7);
        eighttxt=(TextView)findViewById(R.id.vbu_txt8);
        nodatafound=(TextView)findViewById(R.id.vbu_nodata);

        bookings_lst=(ListView)findViewById(R.id.vbu_lst);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serv="rooms";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                build_list_header_4_rooms();
                Load_MyHotel_Bookings_List(serv);
            }
        });

        restau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serv="restau";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                build_list_header_4_restau();
                Load_MyHotel_Bookings_List(serv);
            }
        });

        swimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serv="swimp";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                build_list_header_4_swimp();
                Load_MyHotel_Bookings_List(serv);
            }
        });

        celebh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serv="celebh";
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                build_list_header_4_celebh();
                Load_MyHotel_Bookings_List(serv);
            }
        });

    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

    }

    void build_list_header_4_rooms(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);
        fifithtxt.setVisibility(View.VISIBLE);
        sixthtxt.setVisibility(View.VISIBLE);
        seventhtxt.setVisibility(View.VISIBLE);
        eighttxt.setVisibility(View.VISIBLE);

        firsttxt.setText("H_Name");
        secondtxt.setText("Room");
        thirdtxt.setText("Adults");
        fouthtxt.setText("Kids");
        fifithtxt.setText("Arr_day");
        sixthtxt.setText("Days");
        seventhtxt.setText("Days_left");
        eighttxt.setText("Cost");
        //8
    }

    void build_list_header_4_restau(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);
        firsttxt.setText("Hotel name");
        secondtxt.setText("Persons");
        thirdtxt.setText("Arrival time");
        fouthtxt.setText("Cost");
        fifithtxt.setVisibility(View.GONE);
        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //4
    }

    void build_list_header_4_swimp(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);
        fifithtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Hotel name");
        secondtxt.setText("Adults");
        thirdtxt.setText("Kids");
        fouthtxt.setText("Arrival day");
        fifithtxt.setText("Cost");

        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //5
    }

    void build_list_header_4_celebh(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Hotel name");
        secondtxt.setText("Persons");
        thirdtxt.setText("Arrival day");
        fouthtxt.setText("Cost");

        fifithtxt.setVisibility(View.GONE);
        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //4
    }


        private void Load_MyHotel_Bookings_List(final String srv){

            String url = Server_Host_Constant.Host+"/hotelsbookingsapp/Get_Hotel_Bookings_List_4_User.php";
            StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listsize=0;
                    //Toast.makeText(getBaseContext(),HotelsIDs[hotels_sp.getSelectedItemPosition()],Toast.LENGTH_LONG).show();
                    try{
                        JSONObject jsnobject = new JSONObject(response);
                        JSONArray jsonArray = jsnobject.getJSONArray("result");
                        listsize=jsonArray.length();
                        if(listsize>0)  {
                            nodatafound.setVisibility(View.GONE);
                            bookings_lst.setVisibility(View.VISIBLE);
                            list = new ArrayList<HashMap>();
                            list.clear();
                            if(srv.equals("rooms")){
                                for (int i = 0; i < listsize; i++) {
                                    JSONObject explrObject = jsonArray.getJSONObject(i);
                                    String hotelname=explrObject.getString("col1");
                                    String room=explrObject.getString("col2");
                                    String adults=explrObject.getString("col3");
                                    String kids=explrObject.getString("col4");
                                    String arrday=explrObject.getString("col5");
                                    String days=explrObject.getString("col6");
                                    String daysleft=explrObject.getString("col7");
                                    String cost=explrObject.getString("col8");
                                    add_list_row(hotelname,room,adults,kids,arrday,days,daysleft,cost);
                                }
                                buildMylist(8);
                            }else if(srv.equals("restau")){

                                for (int i = 0; i < listsize; i++) {
                                    JSONObject explrObject = jsonArray.getJSONObject(i);
                                    String hotelname=explrObject.getString("col1");
                                    String persons=explrObject.getString("col2");
                                    String arrtime=explrObject.getString("col3");
                                    String cost=explrObject.getString("col4");
                                    add_list_row(hotelname,persons,arrtime,cost,"","","","");
                                }
                                buildMylist(4);
                            }else if(srv.equals("swimp")){

                                for (int i = 0; i < listsize; i++) {
                                    JSONObject explrObject = jsonArray.getJSONObject(i);
                                    String hotelname=explrObject.getString("col1");
                                    String adults=explrObject.getString("col2");
                                    String kids=explrObject.getString("col3");
                                    String arrday=explrObject.getString("col4");
                                    String cost=explrObject.getString("col5");
                                    add_list_row(hotelname,adults,kids,arrday,cost,"","","");
                                }
                                buildMylist(5);
                            }else{

                                for (int i = 0; i < listsize; i++) {
                                    JSONObject explrObject = jsonArray.getJSONObject(i);
                                    String hotelname=explrObject.getString("col1");
                                    String persons=explrObject.getString("col2");
                                    String arrday=explrObject.getString("col3");
                                    String cost=explrObject.getString("col4");
                                    add_list_row(hotelname,persons,arrday,cost,"","","","");

                                }
                                buildMylist(4);
                            }
                        }else{
                            nodatafound.setVisibility(View.VISIBLE);
                            bookings_lst.setVisibility(View.GONE);
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                        bookings_lst.setVisibility(View.GONE);
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
                    params.put("iduser", AccountInfos.getUserid());
                    params.put("serv",serv);
                    return params;
                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
        }


    void add_list_row(String col1,String col2,String col3,String col4
            ,String col5,String col6,String col7,String col8){
        HashMap temp = new HashMap();
        temp.put(BookingsConstant.FIRST_COLUMN,col1);
        temp.put(BookingsConstant.SECOND_COLUMN, col2);
        temp.put(BookingsConstant.THIRD_COLUMN, col3);
        temp.put(BookingsConstant.FOURTH_COLUMN, col4);
        temp.put(BookingsConstant.FIFTH_COLUMN,col5);
        temp.put(BookingsConstant.SIXTH_COLUMN,col6);
        temp.put(BookingsConstant.SEVENTH_COLUMN,col7);
        temp.put(BookingsConstant.EIGTH_COLUMN,col8);

        list.add(temp);
    }
    private void buildMylist(int nb_cols){
        Bookings_ListAdapter adapter = new Bookings_ListAdapter(this, list,nb_cols);
        bookings_lst.setAdapter(adapter);
    }

}
