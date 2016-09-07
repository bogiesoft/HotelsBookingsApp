package com.example.riadh.hotelsbookings.AdminPack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ViewDragHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riadh.hotelsbookings.CommonPack.Account_Settings;
import com.example.riadh.hotelsbookings.DBConnection.AccountInfos;
import com.example.riadh.hotelsbookings.R;
import com.example.riadh.hotelsbookings.UserPack.UserSpace;

/**
 * Created by riadh on 8/20/2016.
 */
public class AdminSpace extends Activity {
    Button addhotel,managehrooms, edithotels,ViewHR,gousersp,hotels_img_mgr,AccountSt;
    TextView adminfullname;
    AlertDialog.Builder builder;
    static String str_fname;
    void onload(){
        setContentView(R.layout.adminspace);
        addhotel=(Button)findViewById(R.id.addhotel);

        managehrooms=(Button)findViewById(R.id.manhrooms);
        ViewHR=(Button)findViewById(R.id.viewHR);
        gousersp=(Button)findViewById(R.id.gousersp);
        edithotels=(Button)findViewById(R.id.edithotels);
        hotels_img_mgr=(Button)findViewById(R.id.go_img_mgr);
        AccountSt=(Button)findViewById(R.id.as_acst);
        adminfullname=(TextView)findViewById(R.id.adminName);


        try{
            adminfullname.setText(AccountInfos.getFullUsername());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        addhotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getBaseContext(),AddHotel.class);
                startActivity(intent);
            }
        });



        managehrooms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getBaseContext(),ManageHotelRooms.class);
                startActivity(intent);
            }
        });


        gousersp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gouser=new Intent(getBaseContext(),UserSpace.class);
                startActivity(gouser);
            }
        });

        edithotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getBaseContext(),EditHotel.class);
                startActivity(intent);
            }
        });
        ViewHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(), ViewHotelsBookings_Admin.class);
                startActivity(intent);
            }
        });

        hotels_img_mgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(), Manage_Hotels_Images.class);
                startActivity(intent);
            }
        });

        AccountSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Account_Settings.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        try{
            adminfullname.setText(AccountInfos.getFullUsername());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            builder = new AlertDialog.Builder(AdminSpace.this);

            try{
                str_fname=AccountInfos.getFullUsername();
                adminfullname.setText(str_fname);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
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
        }
        return super.onKeyDown(keyCode, event);
    }




}
