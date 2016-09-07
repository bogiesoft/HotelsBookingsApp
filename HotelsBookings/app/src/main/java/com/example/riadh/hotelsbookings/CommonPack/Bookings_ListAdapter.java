package com.example.riadh.hotelsbookings.CommonPack;

import android.app.*;
import android.view.*;
import android.widget.*;

import com.example.riadh.hotelsbookings.R;

import java.util.*;

/**
 * Created by riadh on 9/2/2016.
 */
public class Bookings_ListAdapter  extends BaseAdapter
{
    public ArrayList<HashMap> list;
    Activity activity;
    public int nb_columns;
    public Bookings_ListAdapter(Activity activity, ArrayList<HashMap> list,int nb_columns) {
        super();
        this.activity = activity;
        this.list = list;
        this.nb_columns=nb_columns;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;

        TextView txtFifith;
        TextView txtSixth;
        TextView txtSeventh;
        TextView txtEigth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.view_bookings_row, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.vbr_FirstText);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.vbr_SecondText);
            holder.txtThird = (TextView) convertView.findViewById(R.id.vbr_ThirdText);
            holder.txtFourth = (TextView) convertView.findViewById(R.id.vbr_FourthText);

            holder.txtFifith = (TextView) convertView.findViewById(R.id.vbr_FifthText);
            holder.txtSixth = (TextView) convertView.findViewById(R.id.vbr_SixthText);
            holder.txtSeventh = (TextView) convertView.findViewById(R.id.vbr_SeventhText);
            holder.txtEigth = (TextView) convertView.findViewById(R.id.vbr_EigthText);

            switch (nb_columns){
                case 1:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.GONE);
                    holder.txtThird.setVisibility(View.GONE);
                    holder.txtFourth.setVisibility(View.GONE);
                    holder.txtFifith.setVisibility(View.GONE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 2:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.GONE);
                    holder.txtFourth.setVisibility(View.GONE);
                    holder.txtFifith.setVisibility(View.GONE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 3:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.GONE);
                    holder.txtFifith.setVisibility(View.GONE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 4:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.VISIBLE);
                    holder.txtFifith.setVisibility(View.GONE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 5:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.VISIBLE);
                    holder.txtFifith.setVisibility(View.VISIBLE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 6:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.VISIBLE);
                    holder.txtFifith.setVisibility(View.VISIBLE);
                    holder.txtSixth.setVisibility(View.VISIBLE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 7:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.VISIBLE);
                    holder.txtFifith.setVisibility(View.VISIBLE);
                    holder.txtSixth.setVisibility(View.VISIBLE);
                    holder.txtSeventh.setVisibility(View.VISIBLE);
                    holder.txtEigth.setVisibility(View.GONE);break;
                case 8:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.VISIBLE);
                    holder.txtThird.setVisibility(View.VISIBLE);
                    holder.txtFourth.setVisibility(View.VISIBLE);
                    holder.txtFifith.setVisibility(View.VISIBLE);
                    holder.txtSixth.setVisibility(View.VISIBLE);
                    holder.txtSeventh.setVisibility(View.VISIBLE);
                    holder.txtEigth.setVisibility(View.VISIBLE);break;
                default:
                    holder.txtFirst.setVisibility(View.VISIBLE);
                    holder.txtSecond.setVisibility(View.GONE);
                    holder.txtThird.setVisibility(View.GONE);
                    holder.txtFourth.setVisibility(View.GONE);
                    holder.txtFifith.setVisibility(View.GONE);
                    holder.txtSixth.setVisibility(View.GONE);
                    holder.txtSeventh.setVisibility(View.GONE);
                    holder.txtEigth.setVisibility(View.GONE);break;
            }

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.txtFirst.setText((String) map.get(BookingsConstant.FIRST_COLUMN));
        holder.txtSecond.setText((String) map.get(BookingsConstant.SECOND_COLUMN));
        holder.txtThird.setText((String) map.get(BookingsConstant.THIRD_COLUMN));
        holder.txtFourth.setText((String) map.get(BookingsConstant.FOURTH_COLUMN));

        holder.txtFifith.setText((String) map.get(BookingsConstant.FIFTH_COLUMN));
        holder.txtSixth.setText((String) map.get(BookingsConstant.SIXTH_COLUMN));
        holder.txtSeventh.setText((String) map.get(BookingsConstant.SEVENTH_COLUMN));
        holder.txtEigth.setText((String) map.get(BookingsConstant.EIGTH_COLUMN));
        return convertView;
    }

}