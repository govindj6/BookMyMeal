package com.example.bookmymeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class FoodPackageAdapter extends ArrayAdapter {
Context context;
ArrayList<FoodPackage>al;
    public FoodPackageAdapter(Context context,ArrayList<FoodPackage>al) {
        super(context,R.layout.food_item_list,al);
        this.context=context;
        this.al=al;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodPackage f=al.get(position);
        View v = LayoutInflater.from(context).inflate(R.layout.food_item_list, null);
        TextView packagename=v.findViewById(R.id.tvName);
        TextView packageprice=v.findViewById(R.id.tv_price);
        ImageView packageimage=v.findViewById(R.id.iv_image);
        final TextView qty=v.findViewById(R.id.tv_quantity);
        ImageView increment=v.findViewById(R.id.iv_increment);
        ImageView decrement=v.findViewById(R.id.iv_decrement);

        packagename.setText(f.getName());
        packageprice.setText("Price : "+f.getPrice());
        AQuery aQuery=new AQuery(context);
        aQuery.id(packageimage).image(f.getPackage_image());

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(qty.getText().toString());
                qty.setText(""+(++quantity));
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(qty.getText().toString());
                if (quantity>0)
                qty.setText(""+(--quantity));
            }
        });
        return v;
    }
}
