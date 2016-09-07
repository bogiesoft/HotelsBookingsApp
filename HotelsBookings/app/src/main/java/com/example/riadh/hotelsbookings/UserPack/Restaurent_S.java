package com.example.riadh.hotelsbookings.UserPack;

import android.app.*;
import android.content.*;
import android.os.*;
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
public class Restaurent_S extends Activity{
    Spinner dest;
    Calendar cal;
    DateFormat format1, format2;
    Button chkindatere,chkintimere,search;
    TextView chkindatetxt,chkintimetxt;
    EditText nbpersons;
    String arrtime,nbpers,time,day;
    private void setxTime(int hour, int minute){
        String strhrs, strmins;
        if(hour<10)
            strhrs="0"+Integer.toString(hour);
        else
            strhrs=Integer.toString(hour);

        if(minute<10)
            strmins="0"+Integer.toString(minute);
        else
            strmins=Integer.toString(minute);
        time=strhrs+":"+strmins+":00";
        chkintimetxt.setText(strhrs+":"+strmins);
    }

    private boolean AllowedPastTime(){
        String txtd= chkindatetxt.getText().toString();
        Date b =Calendar.getInstance().getTime();
        String curd = format1.format(b);
        format2 = new SimpleDateFormat("yyyy-MM-dd");
        boolean res=true;

        if(txtd.equals(curd)) {
            res=false;
            /*for search button*/
            /*int cm,ch,th,tm;
            th=Integer.parseInt(atch.getText().toString().substring(0,2));
            tm=Integer.parseInt(atch.getText().toString().substring(3,5));
            ch=cal.get(Calendar.HOUR_OF_DAY);cm=cal.get(Calendar.MINUTE);
            if (th>ch)
                res=true;
            else if(th==ch){
                if(tm>cm)
                    res=true;
            }
            */
            }
        return res;
    }


    private Dialog createTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hour, int minute) {
                setxTime(hour,minute);
            }
        };
        RangeTimePickerDialog tpd= new RangeTimePickerDialog(this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true) {
        };

        if (!AllowedPastTime())
            tpd.setMin(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));

        return tpd;
    }

void onload(){
    setContentView(R.layout.search_restau);

    cal = Calendar.getInstance();
    //SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    format1 = DateFormat.getDateInstance();
    format2 = new SimpleDateFormat("yyyy-MM-dd");
    dest=(Spinner)findViewById(R.id.respdest);
    chkindatere =(Button) findViewById(R.id.chkinbtnre);
    chkintimere=(Button)findViewById(R.id.chktrebtn);
    chkindatetxt = (TextView) findViewById(R.id.chckinre);
    chkintimetxt=(TextView)findViewById(R.id.atres);
    nbpersons=(EditText)findViewById(R.id.resppers);
    chkindatetxt.setText(format1.format(cal.getTime()));
    search=(Button)findViewById(R.id.searchrestau);
    setxTime(cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));
    day=format2.format(cal.getTime());
    final DatePickerDialog.OnDateSetListener dp=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);


            chkindatetxt.setText(format1.format(cal.getTime()));
            day=format2.format(cal.getTime());
        }
    };

    final DatePickerDialog Dpdialog=new DatePickerDialog(this, dp,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    Dpdialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    Dpdialog.getDatePicker().setMaxDate(System.currentTimeMillis()+86400000*21);

    chkindatere.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dpdialog.show();
        }
    });

    chkintimere.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {createTimePicker().show();}});

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            arrtime=day+" "+time;

            try{
                nbpers=String.valueOf(Integer.parseInt(nbpersons.getText().toString()));

            }catch(NumberFormatException ex){
                nbpers="none";
            }

            if(!nbpers.equals("none")){
                Intent intent = new Intent(getBaseContext(),Show_result.class);
                intent.putExtra("serv","restau");
                intent.putExtra("reg",dest.getSelectedItem().toString());
                intent.putExtra("arrtime",arrtime);
                intent.putExtra("nbpers",nbpers);
                startActivity(intent);
            }else{
                new AlertDialog.Builder(Restaurent_S.this)
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


