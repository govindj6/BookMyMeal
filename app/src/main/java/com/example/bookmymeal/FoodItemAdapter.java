package com.example.bookmymeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class FoodItemAdapter extends ArrayAdapter {
    Context context;
    ArrayList<FoodItem> al;

    public FoodItemAdapter(Context context,ArrayList<FoodItem>al) {
        super(context,R.layout.food_item_list,al);
        this.context=context;
        this.al=al;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodItem foodItem=al.get(position);
        View v= LayoutInflater.from(context).inflate(R.layout.food_item_list,null);
        TextView tvname=v.findViewById(R.id.tvName);
        TextView tvprice=v.findViewById(R.id.tv_price);
        ImageView ivFoodImage=v.findViewById(R.id.iv_image);
        tvname.setText(foodItem.getName());
        tvprice.setText("Price: "+foodItem.getPrice());

        final TextView tvQuantity=v.findViewById(R.id.tv_quantity);
        ImageView ivIncrement=v.findViewById(R.id.iv_increment);
        ImageView ivDecrement=v.findViewById(R.id.iv_decrement);
        ivIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(tvQuantity.getText().toString());
                tvQuantity.setText(""+(++qty));
            }
        });
        ivDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(tvQuantity.getText().toString());
                if (qty>0)
                tvQuantity.setText(""+(--qty));
            }
        });

        AQuery aQuery=new AQuery(context);
        aQuery.id(ivFoodImage).image(foodItem.getImage());
        return v;
    }
}
