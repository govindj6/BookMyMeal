package com.example.bookmymeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FoodItem>al;
    Button checkout;
    TextView cartTotal,cartShipping,cartSubtotal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        ActionBar ab=getSupportActionBar();
        ab.setTitle("Cart");

        recyclerView=findViewById(R.id.cart_rv);
        checkout=findViewById(R.id.cart_btnCheckout);
        cartTotal=findViewById(R.id.cart_tvTotal);
        cartShipping=findViewById(R.id.cart_tvShipping);
        cartSubtotal=findViewById(R.id.cart_tvSubtotal);

    }
}
