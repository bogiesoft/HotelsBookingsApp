package com.example.riadh.hotelsbookings.AdminPack;
import android.content.*;
import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.example.riadh.hotelsbookings.DBConnection.Server_Host_Constant;
import com.example.riadh.hotelsbookings.DBConnection.*;
import com.example.riadh.hotelsbookings.R;

import org.json.*;

import java.util.*;

/**
 * Created by riadh on 8/23/2016.
 */
public class EditHotel extends Activity {
    Spinner rating,restaurent,parking,swimmingpool,celebrationhall,wifi,gym,gamesroom,helicopter;
    EditText name, tel, website,nightp,restaup,swimpp,celbhp;
    TableRow restaup_r,swimpp_r,celbhp_r;
    String  edith_night_price,edith_restaut_price,edith_swimp_price,edith_celebh_price;
    Button save;
    LinearLayout form;
    private static Spinner MyhotelsSp;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.edithotel);
        MyhotelsSp=(Spinner)findViewById(R.id.edithhn_sp);
        form=(LinearLayout)findViewById(R.id.edithform);
        rating=(Spinner)findViewById(R.id.edithrate_sp);
        parking=(Spinner)findViewById(R.id.edithparking);
        restaurent=(Spinner)findViewById(R.id.edithrestausp);
        swimmingpool=(Spinner)findViewById(R.id.edithswimp);
        celebrationhall=(Spinner)findViewById(R.id.edithcelbh);
        gym=(Spinner)findViewById(R.id.edithgym);
        gamesroom=(Spinner)findViewById(R.id.edithgamesr);
        helicopter=(Spinner)findViewById(R.id.edithhelico);

        wifi=(Spinner)findViewById(R.id.edithwifisp);
        name=(EditText)findViewById(R.id.edithname);
        tel=(EditText)findViewById(R.id.edithtel);
        website=(EditText)findViewById(R.id.edithweb);


        swimpp_r=(TableRow)findViewById(R.id.edith_swimp_row);
        celbhp_r=(TableRow)findViewById(R.id.edith_celebh_row);
        restaup_r=(TableRow)findViewById(R.id.edith_restaut_row);

        nightp=(EditText)findViewById(R.id.edith_night_p);
        swimpp=(EditText)findViewById(R.id.edith_swimp_p);
        celbhp=(EditText)findViewById(R.id.edith_celebh_p);
        restaup=(EditText)findViewById(R.id.edith_restaut_p);

        save=(Button)findViewById(R.id.edithSave);
        progressDialog = ProgressDialog.show(EditHotel.this, "",
                "Retrieving data, please wait...", true);
        Load_MyHotelsList();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                if(size==0)
                    form.setVisibility(View.GONE);
                else
                    form.setVisibility(View.VISIBLE);

            }
        }, 1000);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg="";
                msg+=MySingleton.checkHotelname(name.getText().toString());
                msg+=MySingleton.checkHotelwebsite(website.getText().toString());
                msg+=MySingleton.checkPhonenumber(tel.getText().toString());
                edith_night_price=nightp.getText().toString();
                edith_celebh_price=celbhp.getText().toString();
                edith_restaut_price=restaup.getText().toString();
                edith_swimp_price=swimpp.getText().toString();




                float per_price=0;
                try{
                    per_price=Float.parseFloat(edith_night_price);
                    edith_night_price=String.valueOf(per_price);
                } catch (NumberFormatException ex) {
                    per_price=0;
                    edith_night_price="none";
                }
                if(per_price<=0)
                    msg+="*Night price is not authorised\n";

                per_price=0;
                if(restaurent.getSelectedItem().toString().toLowerCase().equals("yes")){

                    try{
                        per_price=Float.parseFloat(edith_restaut_price);
                        edith_restaut_price=String.valueOf(per_price);
                    } catch (NumberFormatException ex) {
                        per_price=0;
                        edith_restaut_price="none";
                    }
                    if(per_price<=0)
                        msg+="*Restaurent table price is not authorised\n";
                }else
                    edith_restaut_price="none";

                per_price=0;
                if(swimmingpool.getSelectedItem().toString().toLowerCase().equals("yes")){

                    try{
                        per_price=Float.parseFloat(edith_swimp_price);
                        edith_swimp_price=String.valueOf(per_price);
                    } catch (NumberFormatException ex) {
                        per_price=0;
                        edith_swimp_price="none";
                    }
                    if(per_price<=0)
                        msg+="*Swimming pool price is not authorised\n";
                }else
                    edith_swimp_price="none";

                per_price=0;
                if(celebrationhall.getSelectedItem().toString().toLowerCase().equals("yes")){

                    try{
                        per_price=Float.parseFloat(edith_celebh_price);
                        edith_celebh_price=String.valueOf(per_price);
                    } catch (NumberFormatException ex) {
                        per_price=0;
                        edith_swimp_price="none";
                    }
                    if(per_price<=0)
                        msg+="*Restaurent table price is not authorised\n";
                }else
                    edith_celebh_price="none";

                if(msg.equals(""))
                Update_DB_Hotel();
                else
                    new android.support.v7.app.AlertDialog.Builder(EditHotel.this)
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
        });

        MyhotelsSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {
                Get_DB_Hotel_Infos(HotelsIDs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

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
                params.put("adminid", AccountInfos.getUserid());
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    void Get_DB_Hotel_Infos(final String hotel_id){
        String url = Server_Host_Constant.Host+"/hotelsbookingsapp/getHotelInfos.php";
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try{
                    JSONObject jsnobject = new JSONObject(response);
                    UpdateForm(jsnobject.getString("name"),jsnobject.getString("rating"),
                    jsnobject.getString("tel"),jsnobject.getString("website"),
                    jsnobject.getString("restaurent"),jsnobject.getString("parking"),
                    jsnobject.getString("swimming_pool"),jsnobject.getString("celebration_hall"),
                    jsnobject.getString("wifi"),jsnobject.getString("gym"),
                    jsnobject.getString("games_room"),jsnobject.getString("helicopter_landing"),
                    jsnobject.getString("night_price"),jsnobject.getString("restau_table_price"),
                    jsnobject.getString("swimp_price"),jsnobject.getString("party_hall_price"));
                }catch(JSONException e){
                    e.printStackTrace();
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
                params.put("hotelid",hotel_id);
                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

        void UpdateForm(String name,String rating, String tel, String website,String restaurent,
        String park,String swimp, String celbh,String wifi,String gym, String gamesr, String helico,
        String night_price,String restau_table_price,String swimp_price,String party_hall_price
        ){
            this.name.setText(name);

            this.rating.setSelection(rating.length()-1,true);

            this.tel.setText(tel);

            this.website.setText(website);
            this.nightp.setText(night_price);
            if(restaurent.toLowerCase().equals("yes")){
                this.restaurent.setSelection(1,true);
                this.restaup_r.setVisibility(View.VISIBLE);
                this.restaup.setText(restau_table_price);
            }else{
                this.restaurent.setSelection(0,true);
                this.restaup_r.setVisibility(View.GONE);
                this.restaup.setText("");
            }
                if(park.toLowerCase().equals("yes"))//{
                this.parking.setSelection(1,true);
            else
                this.parking.setSelection(0,true);
//                this.parking.setEnabled(false);
//            }
            if(swimp.toLowerCase().equals("yes")){
                this.swimmingpool.setSelection(1,true);
                this.swimpp_r.setVisibility(View.VISIBLE);
                this.swimpp.setText(swimp_price);
            }else{
                this.swimmingpool.setSelection(0,true);
                this.swimpp_r.setVisibility(View.GONE);
                this.swimpp.setText("");
// this.swimmingpool.setEnabled(false);
            }

            if(celbh.toLowerCase().equals("yes")){
                this.celebrationhall.setSelection(1,true);
                this.celbhp_r.setVisibility(View.VISIBLE);
                this.celbhp.setText(party_hall_price);
            }else{
                this.celebrationhall.setSelection(0,true);
                this.celbhp_r.setVisibility(View.GONE);
                this.celbhp.setText("");
//                this.celebrationhall.setEnabled(false);
            }

            if(wifi.toLowerCase().equals("yes"))
                this.wifi.setSelection(1,true);
            else
                this.wifi.setSelection(0,true);

            if(gym.toLowerCase().equals("yes"))//{
                this.gym.setSelection(1,true);
            else
                this.gym.setSelection(0,true);
//                this.gym.setEnabled(false);
//            }
            if(gamesr.toLowerCase().equals("yes"))//{
                this.gamesroom.setSelection(1,true);
            else
                this.gamesroom.setSelection(0,true);
//                this.gamesroom.setEnabled(false);
//            }
            if(helico.toLowerCase().equals("yes"))//{
                this.helicopter.setSelection(1,true);
            else
                this.helicopter.setSelection(0,true);
//                this.helicopter.setEnabled(false);
//            }
        }

void Update_DB_Hotel(){

    String url = Server_Host_Constant.Host+"/hotelsbookingsapp/UpdateHotelInfos.php";
    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(getBaseContext(),response,Toast.LENGTH_SHORT).show();
            if(response.contains("success"))
                Load_MyHotelsList();
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
            params.put("hotelid",HotelsIDs[MyhotelsSp.getSelectedItemPosition()]);
            params.put("hname",name.getText().toString());
            params.put("hrate",rating.getSelectedItem().toString());
            params.put("htel",tel.getText().toString().toLowerCase());
            params.put("hwebs",website.getText().toString());
            params.put("hrestau",restaurent.getSelectedItem().toString());
//            if(parking.isEnabled())
            params.put("hpark",parking.getSelectedItem().toString());
//            else
//            params.put("hpark","Yes");

//            if(swimmingpool.isEnabled())
            params.put("hswip",swimmingpool.getSelectedItem().toString());
//            else
//            params.put("hswip","Yes");

//            if(helicopter.isEnabled())
            params.put("hclbh",helicopter.getSelectedItem().toString());
            params.put("wifi",wifi.getSelectedItem().toString());
//            else
//            params.put("hclbh","Yes");

//            if(gym.isEnabled())
            params.put("hgym",gym.getSelectedItem().toString());
//            else
//            params.put("hgym","Yes");

//            if(gamesroom.isEnabled())
            params.put("hgamr",gamesroom.getSelectedItem().toString());
//            else
//            params.put("hgamr","Yes");

//            if(helicopter.isEnabled())
            params.put("hhlco",helicopter.getSelectedItem().toString());
//            else
//            params.put("hhlco","Yes");
            params.put("hnighip",edith_night_price);
            params.put("hrestaup",edith_restaut_price);
            params.put("hswimpp",edith_swimp_price);
            params.put("hcelebhp",edith_celebh_price);

            return params;
        }
    };

    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
}
}



