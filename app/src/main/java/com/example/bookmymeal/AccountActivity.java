package com.example.bookmymeal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    SharedPreferences sp= null;
    ArrayList<User>arrayList;
    TextView accName,accMobile,accEmail,accAddress,accOrders,accSupport,accLogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        final ActionBar ab=getSupportActionBar();
        ab.setTitle("My Profile");

        sp=getSharedPreferences("user",MODE_PRIVATE);
        accName=findViewById(R.id.accName);
        accMobile=findViewById(R.id.accMobile);
        accEmail=findViewById(R.id.accEmail);
        accAddress=findViewById(R.id.accMyAddress);
        accOrders=findViewById(R.id.accMyOrder);
        accSupport=findViewById(R.id.accSupport);
        accLogout = findViewById(R.id.accLogout);

        final String value=sp.getString("id","");
        //Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        final String userapi=Server.SERVER_ADDRESS+"/getUserDetails.jsp";

        StringRequest request=new StringRequest(Request.Method.POST, userapi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray array= null;
                arrayList=new ArrayList<>();
                try {
                    String username="";
                    String email="";
                    String mobile="";
                    array = new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);

                        int id=object.getInt("id");
                        username=object.getString("username");
                        email=object.getString("email");
                        mobile=object.getString("mobile");
                    }
                    accName.setText(username);
                    accEmail.setText(email);
                    accMobile.setText(mobile);
                } catch (Exception e) {
                    Toast.makeText(AccountActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        },null){
            protected Map<String,String> getParams()throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",value);
                return hashMap;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(AccountActivity.this);
        queue.add(request);

        accLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("id","0");
                editor.commit();
                Intent in=new Intent(AccountActivity.this,LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
