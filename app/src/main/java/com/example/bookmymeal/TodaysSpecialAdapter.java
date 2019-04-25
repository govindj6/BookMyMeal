package com.example.bookmymeal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class TodaysSpecialAdapter extends RecyclerView.Adapter<TodaysSpecialAdapter.SpecialViewHolder> {
    ArrayList<FoodItem>arrayList;
    Context context;

    public TodaysSpecialAdapter(ArrayList<FoodItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item_special,null);
        return new SpecialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecialViewHolder specialViewHolder, int i) {
        FoodItem position=arrayList.get(i);
        int price=position.getPrice();
        int dis=10/100*price;
        int afterDis=price-dis;
        AQuery aQuery=new AQuery(context);
        aQuery.id(specialViewHolder.ivFoodSpecial).image(position.getImage());
        specialViewHolder.tvNameSpecial.setText(position.getName());
        specialViewHolder.tvPriceSpecial.setText("\u20B9 "+price);
        specialViewHolder.tvDisSpecial.setText("\u20B9 "+afterDis);
        specialViewHolder.ivIncrementSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(specialViewHolder.tvQuantitySpecial.getText().toString());
                specialViewHolder.tvQuantitySpecial.setText(""+(++qty));

            }
        });
        specialViewHolder.ivDecrementSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(specialViewHolder.tvQuantitySpecial.getText().toString());
                specialViewHolder.tvQuantitySpecial.setText(""+(--qty));
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SpecialViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameSpecial,tvDisSpecial,tvPriceSpecial,tvQuantitySpecial;
        ImageView ivIncrementSpecial,ivDecrementSpecial;
        ImageView ivFoodSpecial;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameSpecial=itemView.findViewById(R.id.tvName_special);
            tvDisSpecial=itemView.findViewById(R.id.tvprice_dis_special);
            tvPriceSpecial=itemView.findViewById(R.id.tv_price_special);
            tvQuantitySpecial=itemView.findViewById(R.id.tv_quantity_special);
            ivDecrementSpecial=itemView.findViewById(R.id.iv_decrementSpecial);
            ivIncrementSpecial=itemView.findViewById(R.id.iv_incrementSpecial);
            ivFoodSpecial=itemView.findViewById(R.id.iv_imageSpecial);
        }
    }
}
