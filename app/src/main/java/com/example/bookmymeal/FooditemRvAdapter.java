package com.example.bookmymeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class FooditemRvAdapter extends RecyclerView.Adapter<FooditemRvAdapter.MyViewHolder> {
    Context context;
    ArrayList<FoodItem>al;

    public FooditemRvAdapter(Context context,ArrayList<FoodItem> al) {
        this.al = al;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
       View v=layoutInflater.inflate(R.layout.food_item_list,viewGroup,false);
       MyViewHolder myViewHolder=new MyViewHolder(v);
       return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        FoodItem position=al.get(i);
        myViewHolder.RVtvName.setText(position.getName());
        myViewHolder.RVtvPrice.setText("\u20B9 "+position.getPrice());

        AQuery aQuery=new AQuery(context);
        aQuery.id(myViewHolder.RVivFood).image(position.getImage());

        myViewHolder.RVivIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty=Integer.parseInt(myViewHolder.RVtvQuantity.getText().toString());
                myViewHolder.RVtvQuantity.setText(""+(++qty));
            }
        });
        myViewHolder.RVivDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int qty=Integer.parseInt(myViewHolder.RVtvQuantity.getText().toString());
                myViewHolder.RVtvQuantity.setText(""+(--qty));

            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView RVivFood,RVivDecrement,RVivIncrement;
        TextView RVtvName,RVtvPrice,RVtvQuantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            RVtvName=itemView.findViewById(R.id.tvName);
            RVtvPrice=itemView.findViewById(R.id.tv_price);
            RVtvQuantity=itemView.findViewById(R.id.tv_quantity);
            RVivFood=itemView.findViewById(R.id.iv_image);
            RVivDecrement=itemView.findViewById(R.id.iv_decrement);
            RVivIncrement=itemView.findViewById(R.id.iv_increment);
        }
    }
}
