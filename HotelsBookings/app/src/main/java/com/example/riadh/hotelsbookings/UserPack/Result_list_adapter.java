package com.example.riadh.hotelsbookings.UserPack;
import android.content.*;
import android.view.LayoutInflater;
import android.view.*;
import android.widget.*;
import android.graphics.Bitmap;
import com.example.riadh.hotelsbookings.R;

import java.util.*;

/**
 * Created by riadh on 8/27/2016.
 */
public class Result_list_adapter extends BaseAdapter {

    // Declare Variables
    Context context;
    ArrayList<String> rank;
    ArrayList<String> name;
    ArrayList<String> price;
    ArrayList<Bitmap> image;
    LayoutInflater inflater;

    public Result_list_adapter(Context context, ArrayList<String> rank, ArrayList<String> name,
                           ArrayList<String> price, ArrayList<Bitmap> image) {
        this.context = context;
        this.rank = rank;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    @Override
    public int getCount() {
        return rank.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        RatingBar txtrank;
        TextView txtname;
        TextView txtprice;
        ImageView imgimage;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.result_list_item, parent, false);

        // Locate the TextViews in listview_item.xml
        txtrank = (RatingBar) itemView.findViewById(R.id.lst_hotelrank_txt);
        txtname = (TextView) itemView.findViewById(R.id.lst_hotelname_txt);
        txtprice = (TextView) itemView.findViewById(R.id.lst_hotelprice_txt);
        // Locate the ImageView in listview_item.xml
        imgimage = (ImageView) itemView.findViewById(R.id.hotelimg);

        // Capture position and set to the TextViews
        txtrank.setRating(rank.get(position).length());
        txtname.setText(name.get(position));
        txtprice.setText(price.get(position)+"TND");

        // Capture position and set to the ImageView
        try {
            imgimage.setImageBitmap(image.get(position));
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }


        return itemView;
    }
}
