package com.example.riadh.hotelsbookings.UserPack;

import android.app.Activity;
import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;

import com.example.riadh.hotelsbookings.AdminPack.Addhr44per;
import com.example.riadh.hotelsbookings.AdminPack.EditHotel;
import com.example.riadh.hotelsbookings.CustomisedTimePicker.RangeTimePickerDialog;
import com.example.riadh.hotelsbookings.R;

import java.text.*;
import java.util.*;

/**
 * Created by riadh on 8/3/2016.
 */
public class SwimmingPool_S extends Activity{
    Calendar cal;
    DateFormat format1,format2;

    Button chkinsp,search;
    TextView chkinsptxt;
    EditText nbadult,nbkids;
    Spinner dest;
    String serv,reg,arrday,nbk1,nba1;
    DatePickerDialog Dpdialog;
    DatePickerDialog.OnDateSetListener dp;
    //    private boolean AllowedPastTime(){
//        String txtd= chkinsptxt.getText().toString();
//        Date b =Calendar.getInstance().getTime();
//        String curd = format1.format(b);
//        boolean res=true;
//
//        if(txtd.equals(curd)) {
//            res=false;
//        }
//        return res;
//    }


void onload(){
    setContentView(R.layout.search_swimp);
    cal = Calendar.getInstance();

    format1 = DateFormat.getDateInstance();
    format2 = new SimpleDateFormat("yyyy-MM-dd");

    chkinsp =(Button) findViewById(R.id.chkinbtnsp);
    search=(Button)  findViewById(R.id.searchpi);
    nbadult=(EditText)findViewById(R.id.spspadults);
    nbkids=(EditText)findViewById(R.id.spspkids);
    chkinsptxt = (TextView) findViewById(R.id.chckinsp);
    dest=(Spinner)findViewById(R.id.spspdest);
    serv="swimp";


    chkinsptxt.setText(format1.format(cal.getTime()));
    arrday=format2.format(cal.getTime());

    dp=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);


            chkinsptxt.setText(format1.format(cal.getTime()));
            arrday=format2.format(cal.getTime());
        }
    };

    Dpdialog=new DatePickerDialog(this, dp,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    Dpdialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    Dpdialog.getDatePicker().setMaxDate(System.currentTimeMillis()+86400000*21);

    chkinsp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dpdialog.show();
        }
    });


    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(testnumbers(nbadult,nbkids)>0){
                try{
                    nba1=String.valueOf(Integer.parseInt(nbadult.getText().toString()));

                }catch(NumberFormatException ex){
                    nba1="none";
                }

                try{
                    nbk1=String.valueOf(Integer.parseInt(nbkids.getText().toString()));

                }catch(NumberFormatException ex){
                    nbk1="none";
                }

                reg=dest.getSelectedItem().toString();

                Intent intent = new Intent(getBaseContext(),Show_result.class);
                intent.putExtra("serv",serv);
                intent.putExtra("reg",reg);
                intent.putExtra("arrday",String.valueOf(arrday));
                intent.putExtra("nba1",nba1);
                intent.putExtra("nbk1",nbk1);
                //Toast.makeText(getBaseContext(),String.valueOf(arrday),Toast.LENGTH_LONG).show();
                startActivity(intent);

            }else{
                new AlertDialog.Builder(SwimmingPool_S.this)
                        .setTitle("Sorry, you've gave wrong numbers !")
                        .setIcon(R.drawable.caution)
                        .setMessage("The number must be at least 1 persons")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create().show();
            }
        }
    });
}

    @Override
        protected void onCreate(Bundle savedInstanceState) {
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
    private int testnumbers(EditText e1, EditText e2){
        int a,b,s;
        try{
            a=Integer.parseInt(e1.getText().toString());

        }catch(NumberFormatException ex){
            a=0;
        }

        try{
            b=Integer.parseInt(e2.getText().toString());

        }catch(NumberFormatException ex){
            b=0;
        }
        s=a+b;
        if(s>0)
            return s;
        else
            return -1;

    }
}


