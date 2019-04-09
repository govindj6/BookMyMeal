package com.example.bookmymeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        getSupportActionBar().hide();
        new SplashThread().start();
    }
    class SplashThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                Intent in=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(in);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
