package com.example.riadh.hotelsbookings.UserPack;

import android.app.Activity;
import android.app.DatePickerDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.*;
import android.view.*;
import android.widget.*;

import com.example.riadh.hotelsbookings.CommonPack.ForgotPassword;
import com.example.riadh.hotelsbookings.R;

import java.sql.Date;
import java.text.*;
import java.util.*;


/**
 * Created by riadh on 8/3/2016.
 */
public class HotelRoom_S extends Activity{
    Calendar cal;
    DateFormat format1,format2;

    Button chkinro,search;
    TextView chkinrotxt;
    Spinner rodest,ronbroom,ronbdays;
    Spinner ro1adults,ro1kids,ro2adults,ro2kids,ro3adults,ro3kids,ro4adults,ro4kids,ro5adults,ro5kids;
    LinearLayout ro2l,ro3l,ro4l,ro5l;
    String serv,reg,arrday,nbdays,nbk1,nba1,nbk2,nba2,nbk3,nba3,nbk4,nba4,nbk5,nba5;
void onload(){
    setContentView(R.layout.search_room);
    serv="rooms";
    cal = Calendar.getInstance();
    format2 = new SimpleDateFormat("yyyy-MM-dd");
    format1 = DateFormat.getDateInstance();
    chkinro =(Button) findViewById(R.id.chkinbtnro);
    chkinrotxt = (TextView) findViewById(R.id.chkinro);
    rodest=(Spinner)findViewById(R.id.rospdest);
    ronbroom=(Spinner)findViewById(R.id.rosp_rooms);
    ronbdays=(Spinner)findViewById(R.id.rospDays);

    ro1kids=(Spinner)findViewById(R.id.ro1spkids);
    ro1adults=(Spinner)findViewById(R.id.ro1spadults);

    ro1adults.setSelection(1);

    ro2kids=(Spinner)findViewById(R.id.ro2spkids);
    ro2adults=(Spinner)findViewById(R.id.ro2spadults);

    ro2adults.setSelection(1);

    ro3kids=(Spinner)findViewById(R.id.ro3spkids);
    ro3adults=(Spinner)findViewById(R.id.ro3spadults);

    ro3adults.setSelection(1);

    ro4kids=(Spinner)findViewById(R.id.ro4spkids);

    ro4adults=(Spinner)findViewById(R.id.ro4spadults);

    ro4adults.setSelection(1);

    ro5kids=(Spinner)findViewById(R.id.ro5spkids);
    ro5adults=(Spinner)findViewById(R.id.ro5spadults);

    ro5adults.setSelection(1);

    ro2l=(LinearLayout)findViewById(R.id.ro2l);
    ro3l=(LinearLayout)findViewById(R.id.ro3l);
    ro4l=(LinearLayout)findViewById(R.id.ro4l);
    ro5l=(LinearLayout)findViewById(R.id.ro5l);
    search=(Button)findViewById(R.id.searchro);

    ronbroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            //Toast.makeText(HotelRoom_S.this,ronbroom.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            switch(ronbroom.getSelectedItem().toString()) {

                case "1":ro2l.setVisibility(View.GONE);
                    ro3l.setVisibility(View.GONE);
                    ro4l.setVisibility(View.GONE);
                    ro5l.setVisibility(View.GONE);break;

                case "2":ro2l.setVisibility(View.VISIBLE);
                    ro3l.setVisibility(View.GONE);
                    ro4l.setVisibility(View.GONE);
                    ro5l.setVisibility(View.GONE);break;

                case "3":ro2l.setVisibility(View.VISIBLE);
                    ro3l.setVisibility(View.VISIBLE);
                    ro4l.setVisibility(View.GONE);
                    ro5l.setVisibility(View.GONE);break;

                case "4":ro2l.setVisibility(View.VISIBLE);
                    ro3l.setVisibility(View.VISIBLE);
                    ro4l.setVisibility(View.VISIBLE);
                    ro5l.setVisibility(View.GONE);break;

                case "5":ro2l.setVisibility(View.VISIBLE);
                    ro3l.setVisibility(View.VISIBLE);
                    ro4l.setVisibility(View.VISIBLE);
                    ro5l.setVisibility(View.VISIBLE);break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            //Toast.makeText(HotelRoom_S.this,"Nothing",Toast.LENGTH_SHORT).show();
        }

    });

    chkinrotxt.setText(format1.format(cal.getTime()));
    arrday=format2.format(cal.getTime());

    final DatePickerDialog.OnDateSetListener dp=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);


            chkinrotxt.setText(format1.format(cal.getTime()));
            arrday=format2.format(cal.getTime());

        }
    };

    final DatePickerDialog Dpdialog=new DatePickerDialog(this, dp,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    Dpdialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    Dpdialog.getDatePicker().setMaxDate(System.currentTimeMillis()+86400000*21);
    chkinro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {Dpdialog.show();}});

    search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reg=rodest.getSelectedItem().toString();
            nbdays=ronbdays.getSelectedItem().toString();
            nbk1=ro1kids.getSelectedItem().toString();
            nba1=ro1adults.getSelectedItem().toString();
            nbk2=ro2kids.getSelectedItem().toString();
            nba2=ro2adults.getSelectedItem().toString();
            nbk3=ro3kids.getSelectedItem().toString();
            nba3=ro3adults.getSelectedItem().toString();
            nbk4=ro4kids.getSelectedItem().toString();
            nba4=ro4adults.getSelectedItem().toString();
            nbk5=ro5kids.getSelectedItem().toString();
            nba5=ro5adults.getSelectedItem().toString();
            String tmp=chkinrotxt.getText().toString();
//                Toast.makeText(getBaseContext(),String.valueOf(arrday),Toast.LENGTH_LONG).show();

            String msg="";

            if(spinner_handler(ro1adults,ro1kids)>0)
                msg+="Room 1\n";

            if(ro2l.getVisibility()==View.GONE){
                nbk2="none";
                nba2="none";

                nbk3="none";
                nba3="none";

                nbk4="none";
                nba4="none";

                nbk5="none";
                nba5="none";

            }else{
                if(spinner_handler(ro2adults,ro2kids)>0)
                    msg+="Room 2\n";
                if(ro3l.getVisibility()==View.GONE){
                    nbk3="none";
                    nba3="none";

                    nbk4="none";
                    nba4="none";

                    nbk5="none";
                    nba5="none";
                }else{
                    if(spinner_handler(ro3adults,ro3kids)>0)
                        msg+="Room 3\n";
                    if(ro4l.getVisibility()==View.GONE){
                        nbk4="none";
                        nba4="none";

                        nbk5="none";
                        nba5="none";
                    }else{
                        if(spinner_handler(ro4adults,ro4kids)>0)
                            msg+="Room 4\n";

                        if(ro5l.getVisibility()==View.GONE){
                            nbk5="none";
                            nba5="none";
                        }else{
                            if(spinner_handler(ro5adults,ro5kids)>0)
                                msg+="Room 5\n";
                        }

                    }
                }

            }







//                Toast.makeText(getBaseContext(),reg,Toast.LENGTH_LONG).show();
            if(msg.equals("")){
            Intent show = new Intent(view.getContext(),Show_result.class);
            show.putExtra("serv",serv);
            show.putExtra("reg",reg);
            show.putExtra("arrday",arrday);
            show.putExtra("nbdays",nbdays);
            show.putExtra("nba1",nba1);
            show.putExtra("nbk1",nbk1);
            show.putExtra("nba2",nba2);
            show.putExtra("nbk2",nbk2);
            show.putExtra("nba3",nba3);
            show.putExtra("nbk3",nbk3);
            show.putExtra("nba4",nba4);
            show.putExtra("nbk4",nbk4);
            show.putExtra("nba5",nba5);
            show.putExtra("nbk5",nbk5);
            startActivity(show);
            }else{
                AlertDialog alertDialog=new AlertDialog.Builder(HotelRoom_S.this)
                        .setTitle("You've didn't respect the room persons boundaries !")
                        .setIcon(R.drawable.caution)
                        .setMessage("Room contains at least 1 and max 4 persons,\nPlease check : \n"+msg)
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                alertDialog.show();
            }
        }
    });

/*    ro1adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro1adults,ro1kids)==1){
                ro1adults.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro1adults,ro1kids)==2){
                ro1adults.setSelection(1);
                showdialog2();
            }

            // Toast.makeText(getBaseContext(),"Adult 1",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro1kids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro1adults,ro1kids)==1){
                ro1kids.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro1adults,ro1kids)==2){
                ro1kids.setSelection(1);
                showdialog2();
            }

            // Toast.makeText(getBaseContext(),"Kid 1",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro2adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro2adults,ro2kids)==1){
                ro2adults.setSelection(0);
                showdialog1();
            }

            else if(spinner_handler(ro2adults,ro2kids)==2){
                ro2adults.setSelection(1);
                showdialog2();
            }

        }



        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro2kids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro2adults,ro2kids)==1){

                ro2kids.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro2adults,ro2kids)==2){

                ro2kids.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro3adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro3adults,ro3kids)==1){

                ro3adults.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro3adults,ro3kids)==2){

                ro3kids.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro3kids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro3adults,ro3kids)==1){

                ro3kids.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro3adults,ro3kids)==2){

                ro3kids.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro4adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro4adults,ro4kids)==1){

                ro4adults.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro4adults,ro4kids)==2){

                ro4adults.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro4kids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro4adults,ro4kids)==1){

                ro4kids.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro4adults,ro4kids)==2){

                ro4kids.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro5adults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro5adults,ro5kids)==1){

                ro5adults.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro5adults,ro5kids)==2){

                ro5adults.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });

    ro5kids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3)
        {
            if(spinner_handler(ro5adults,ro5kids)==1){

                ro5kids.setSelection(0);
                showdialog1();
            }
            else if(spinner_handler(ro5adults,ro5kids)==2){

                ro5kids.setSelection(1);
                showdialog2();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    });*/
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

/*    private void showdialog1(){
    AlertDialog alertDialog= new AlertDialog.Builder(HotelRoom_S.this)
    .setTitle("Sorry, you've exceeded the max number !")
    .setIcon(R.drawable.caution)
    .setMessage("Room contains at maximum 4 persons")
    .setCancelable(false)
    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    }).create();
    alertDialog.show();
    }

    private void showdialog2(){
        AlertDialog alertDialog=new AlertDialog.Builder(HotelRoom_S.this)
                .setTitle("Sorry, you've gave a wrong number !")
                .setIcon(R.drawable.caution)
                .setMessage("Room contains at least 1 persons")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        alertDialog.show();
    }*/

    private int spinner_handler(Spinner sp1, Spinner sp2){
            int val1,val2;
        val1=Integer.parseInt(sp1.getSelectedItem().toString());
        val2=Integer.parseInt(sp2.getSelectedItem().toString());
        if((val1+val2)>4)
        return 1;
        else if((val1+val2)==0)

            return 2;
        else
            return 0;
    }

}
