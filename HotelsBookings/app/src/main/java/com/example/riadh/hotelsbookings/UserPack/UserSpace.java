package com.example.riadh.hotelsbookings.UserPack;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.example.riadh.hotelsbookings.CommonPack.Account_Settings;
import com.example.riadh.hotelsbookings.DBConnection.AccountInfos;
import com.example.riadh.hotelsbookings.R;

/**
 * Created by riadh on 8/3/2016.
 */
public class UserSpace extends Activity {
    ImageView us_pp;
    Button HotelRoom,HotelSwmp,HotelRsta,Hotelclha,AccountSt;
    ImageButton view_reserv;
    AlertDialog.Builder builder;
    private static TextView Fullname;
    private static String str_fname,str_isadmin;
    @Override
    protected void onResume(){
        super.onResume();
        try{
            Fullname.setText(AccountInfos.getFullUsername());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            final Handler start = new Handler();

            start.postDelayed(new Runnable() {
                @Override
                public void run() {
        setContentView(R.layout.userspace);
        us_pp=(ImageView)findViewById(R.id.us_pp);
        HotelRoom=(Button) findViewById(R.id.hrobtn);
        HotelSwmp=(Button) findViewById(R.id.hspbtn);
        HotelRsta=(Button) findViewById(R.id.hrebtn);
        Hotelclha=(Button) findViewById(R.id.hchbtn);
        AccountSt=(Button) findViewById(R.id.asettings);
        view_reserv=(ImageButton)findViewById(R.id.us_view_r);

        Fullname=(TextView)findViewById(R.id.fusername);
                    try{
                        str_fname=AccountInfos.getFullUsername();
                        str_isadmin=AccountInfos.getIsadmin();
                        if(str_isadmin.equals("1")){
                            us_pp.setImageResource(R.drawable.admin_ico);
                            AccountSt.setVisibility(View.GONE);
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
        Fullname.setText(str_fname);

        HotelRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent srch_hotelroom = new Intent(v.getContext(),HotelRoom_S.class);
                startActivityForResult(srch_hotelroom,0);
            }
        });

        HotelSwmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent srch_swimp = new Intent(v.getContext(),SwimmingPool_S.class);
                startActivityForResult(srch_swimp,0);
            }
        });

        HotelRsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent srch_restau = new Intent(v.getContext(),Restaurent_S.class);
                startActivityForResult(srch_restau,0);
            }
        });

        Hotelclha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent srch_celebhall = new Intent(v.getContext(),CelebrationHall_S.class);
                startActivityForResult(srch_celebhall,0);
            }
        });

        AccountSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Account_Settings.class);
                startActivity(intent);
            }
        });

        view_reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), View_My_Bookings.class);
                startActivity(intent);
            }
        });
    }
}, 300);

        }catch(RuntimeException e){
        e.printStackTrace();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(AccountInfos.getIsadmin().equals("0")){
                builder = new AlertDialog.Builder(UserSpace.this);

                builder.setTitle("Confirm Loggin out");
                builder.setMessage("Want you to logout now ?");

                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        dialog.dismiss();
                        finish();
                    }
                });

                builder.setNegativeButton("Stay connected", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                builder.show();
            return true;
            }else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
