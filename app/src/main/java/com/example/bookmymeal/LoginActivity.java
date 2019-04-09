package com.example.bookmymeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText mobile,password;
    Button btnLogin;
    TextView tvNewUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        mobile=findViewById(R.id.etMobileLogin);
        password=findViewById(R.id.etPasswordLogin);
        btnLogin=findViewById(R.id.btnLogin);
        tvNewUser=findViewById(R.id.tvNewUser);
        final String api=Server.SERVER_ADDRESS+"/login_user.jsp";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request= new StringRequest(Request.Method.POST, api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("success")){
                            Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,WelcomeActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }
                },null){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<String, String>();
                        hm.put("mobile",mobile.getText().toString());
                        hm.put("password",password.getText().toString());
                        return hm;
                    }
                };
                RequestQueue queue=Volley.newRequestQueue(LoginActivity.this);
                queue.add(request);

            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
