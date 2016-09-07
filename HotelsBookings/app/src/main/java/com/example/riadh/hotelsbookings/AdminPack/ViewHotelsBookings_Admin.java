package com.example.riadh.hotelsbookings.AdminPack;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.app.*;
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
 * Created by riadh on 8/23/2016.
 */
public class ViewHotelsBookings_Admin extends Activity {
    LinearLayout hotelperiod_l, services_l;
    ImageButton rooms,swimp,restau,celebh;
    Spinner hotels_sp,period_sp;
    int nbhotels,listsize;
    ArrayList spinnerArray;
    TextView nodatafound,firsttxt,secondtxt,thirdtxt,fouthtxt,fifithtxt,sixthtxt,seventhtxt,eighttxt;
    ListView bookings_lst;
    private static String[] HotelsIDs;
    String serv;
    private ArrayList<HashMap> list;
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    try{
        final Handler start = new Handler();

        start.postDelayed(new Runnable() {
            @Override
            public void run() {
    setContentView(R.layout.view_hotels_bookings_4_admin);
    hotelperiod_l=(LinearLayout)findViewById(R.id.vmhb_hp_l);
    services_l=(LinearLayout)findViewById(R.id.vmhb_serv_l);
    rooms=(ImageButton)findViewById(R.id.vmbh_rooms);
    swimp=(ImageButton)findViewById(R.id.vmbh_swimp);
    restau=(ImageButton)findViewById(R.id.vmbh_restau);
    celebh=(ImageButton)findViewById(R.id.vmbh_celebh);

    hotels_sp=(Spinner)findViewById(R.id.vmbh_hotels_sp);
    period_sp=(Spinner)findViewById(R.id.vmbh_period_sp);

    firsttxt=(TextView)findViewById(R.id.vba_txt1);
    secondtxt=(TextView)findViewById(R.id.vba_txt2);
    thirdtxt=(TextView)findViewById(R.id.vba_txt3);
    fouthtxt=(TextView)findViewById(R.id.vba_txt4);
    fifithtxt=(TextView)findViewById(R.id.vba_txt5);
    sixthtxt=(TextView)findViewById(R.id.vba_txt6);
    seventhtxt=(TextView)findViewById(R.id.vba_txt7);
    eighttxt=(TextView)findViewById(R.id.vba_txt8);
    nodatafound=(TextView)findViewById(R.id.vba_nodata);


    bookings_lst=(ListView)findViewById(R.id.vba_lst);

    nbhotels=0;
    listsize=0;
    spinnerArray =  new ArrayList<String>();
//    Toast.makeText(getBaseContext(),AccountInfos.getFullUsername(), Toast.LENGTH_SHORT ).show();
    period_sp.setSelection(0,true);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    Load_MyHotelsList();

/*    final Handler handler = new Handler();

    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            build_list_header_4_rooms();

        }
    }, 1000);*/
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
                        hotels_sp.setEnabled(true);
                        spinnerArray.clear();
                        HotelsIDs=new String[nbhotels];
                        for (int i = 0; i < nbhotels; i++) {
                            JSONObject explrObject = jsonArray.getJSONObject(i);
                            HotelsIDs[i]=explrObject.getString("id");
                            spinnerArray.add(i,explrObject.getString("name"));
                            build_sp();
                        }}else{
                        hotels_sp.setEnabled(false);
                        period_sp.setEnabled(false);
                        rooms.setEnabled(false);
                        restau.setEnabled(false);
                        swimp.setEnabled(false);
                        celebh.setEnabled(false);
                        hotelperiod_l.setVisibility(View.GONE);
                        services_l.setVisibility(View.GONE);

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
        hotels_sp.setAdapter(adapter);
        if(nbhotels>0)
            hotels_sp.setSelection(0,true);
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

    void build_list_header_4_rooms(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);
        fifithtxt.setVisibility(View.VISIBLE);
        sixthtxt.setVisibility(View.VISIBLE);
        seventhtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Room");
        secondtxt.setText("Adults");
        thirdtxt.setText("Kids");
        fouthtxt.setText("Arrival day");
        fifithtxt.setText("Days");
        sixthtxt.setText("Days left");
        seventhtxt.setText("Cost");
        eighttxt.setVisibility(View.GONE);
        //7
    }

    void build_list_header_4_restau(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Persons");
        secondtxt.setText("Arrival time");
        thirdtxt.setText("Cost");
        fouthtxt.setVisibility(View.GONE);
        fifithtxt.setVisibility(View.GONE);
        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //3
    }

    void build_list_header_4_swimp(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);
        fouthtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Adults");
        secondtxt.setText("Kids");
        thirdtxt.setText("Arrival day");
        fouthtxt.setText("Cost");
        fifithtxt.setVisibility(View.GONE);
        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //4
    }

    void build_list_header_4_celebh(){
        firsttxt.setVisibility(View.VISIBLE);
        secondtxt.setVisibility(View.VISIBLE);
        thirdtxt.setVisibility(View.VISIBLE);

        firsttxt.setText("Persons");
        secondtxt.setText("Arrival day");
        thirdtxt.setText("Cost");
        fouthtxt.setVisibility(View.GONE);
        fifithtxt.setVisibility(View.GONE);
        sixthtxt.setVisibility(View.GONE);
        seventhtxt.setVisibility(View.GONE);
        eighttxt.setVisibility(View.GONE);
        //3
    }


    private void Load_MyHotel_Bookings_List(final String srv){

        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/Get_Hotel_Bookings_List_4_Admin.php";
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
                            String room=explrObject.getString("col1");
                            String adults=explrObject.getString("col2");
                            String kids=explrObject.getString("col3");
                            String arrday=explrObject.getString("col4");
                            String days=explrObject.getString("col5");
                            String daysleft=explrObject.getString("col6");
                            String cost=explrObject.getString("col7");
                            add_list_row(room,adults,kids,arrday,days,daysleft,cost,"");
                            }
                            buildMylist(7);
                        }else if(srv.equals("restau")){

                            for (int i = 0; i < listsize; i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                String persons=explrObject.getString("col1");
                                String arrtime=explrObject.getString("col2");
                                String cost=explrObject.getString("col3");
                                add_list_row(persons,arrtime,cost,"","","","","");
                            }
                            buildMylist(3);
                        }else if(srv.equals("swimp")){

                            for (int i = 0; i < listsize; i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                String adults=explrObject.getString("col1");
                                String kids=explrObject.getString("col2");
                                String arrday=explrObject.getString("col3");
                                String cost=explrObject.getString("col4");
                                add_list_row(adults,kids,arrday,cost,"","","","");
                            }
                            buildMylist(4);
                        }else{

                            for (int i = 0; i < listsize; i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                String persons=explrObject.getString("col1");
                                String arrday=explrObject.getString("col2");
                                String cost=explrObject.getString("col3");
                                add_list_row(persons,arrday,cost,"","","","","");

                            }
                            buildMylist(3);
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
                params.put("idhotel", HotelsIDs[hotels_sp.getSelectedItemPosition()]);
                params.put("serv",serv);
                params.put("period",adjust_period());
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }


    String adjust_period(){
       String res;
        int pos=period_sp.getSelectedItemPosition();
        switch(pos){
            case 0:res="new";break;
            case 1:res="today";break;
            case 2:res="7";break;
            case 3:res="14";break;
            case 4:res="21";break;
            case 5:res="31";break;
            case 6:res="all";break;
            default:res="today";break;
        }
        return  res;
    }
}
