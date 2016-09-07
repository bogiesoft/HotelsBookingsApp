package com.example.riadh.hotelsbookings.UserPack;

import android.app.*;
import android.content.*;
import android.os.*;
import android.*;
import android.support.v7.app.*;
import android.support.v7.app.AlertDialog;
import android.view.*;
import android.widget.*;


import com.example.riadh.hotelsbookings.CustomisedTimePicker.RangeTimePickerDialog;
import com.example.riadh.hotelsbookings.R;

import java.text.*;
import java.util.*;

/**
 * Created by riadh on 8/3/2016.
 */
public class CelebrationHall_S extends Activity {

    Spinner dest;
    EditText nbpersons;
    Calendar cal ;
    DateFormat format1,format2;
    Button chkinch,search;
    TextView chkinchtxt;
    String nbpers,arrday;

    private boolean AllowedPastTime(){
        String txtd= chkinchtxt.getText().toString();
        Date b =Calendar.getInstance().getTime();
        String curd = format1.format(b);
        boolean res=true;
        if(txtd.equals(curd)) {
            res=false;
        }
    return res;
    }

void onload(){
    setContentView(R.layout.search_partyhall);
    dest=(Spinner)findViewById(R.id.phspdest);
    chkinch=(Button) findViewById(R.id.chkinbtnph);
    search=(Button) findViewById(R.id.srch_ch);
    format2 = new SimpleDateFormat("yyyy-MM-dd");
    nbpersons=(EditText)findViewById(R.id.sch_nbpers);
    chkinchtxt = (TextView) findViewById(R.id.chkinph);
    cal = Calendar.getInstance();
    format1 = DateFormat.getDateInstance();

    chkinchtxt.setText(format1.format(cal.getTime()));
    arrday =format2.format(cal.getTime());




    DatePickerDialog.OnDateSetListener dp=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);


            chkinchtxt.setText(format1.format(cal.getTime()));
            arrday =format2.format(cal.getTime());
        }
    };

    final DatePickerDialog Dpdialog=new DatePickerDialog(this, dp,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    Dpdialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    Dpdialog.getDatePicker().setMaxDate(System.currentTimeMillis()+86400000*21);
    chkinch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {Dpdialog.show();
                    /*
                    if (!AllowedPastTime()&&isPastTime()){
                        new AlertDialog.Builder(Dpdialog.getContext())
                                .setTitle("No past times")
                                .setIcon(R.drawable.caution)
                                .setMessage("Past Time is not allowed !")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                    }
                                }).create().show();
                    }
                    */
        }});

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                nbpers=String.valueOf(Integer.parseInt(nbpersons.getText().toString()));

            }catch(NumberFormatException ex){
                nbpers="none";
            }

            if(!nbpers.equals("none")){
                Intent intent = new Intent(getBaseContext(),Show_result.class);
                intent.putExtra("serv","celebh");
                intent.putExtra("reg",dest.getSelectedItem().toString());
                intent.putExtra("arrday",arrday);
                intent.putExtra("nbpers",nbpers);
                startActivity(intent);
            }else{
                new AlertDialog.Builder(CelebrationHall_S.this)
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

}



