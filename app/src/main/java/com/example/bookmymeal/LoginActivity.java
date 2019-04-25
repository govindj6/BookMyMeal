package com.example.bookmymeal;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sp=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        sp=getSharedPreferences("user",MODE_PRIVATE);
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
                            String s[]=response.split(" ");
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("id",s[1]);
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,WelcomeActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Invalid mobile or password", Toast.LENGTH_SHORT).show();
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
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String id=sp.getString("id","0");
        if (!id.equals("0")){
            Intent in=new Intent(LoginActivity.this,WelcomeActivity.class);
            startActivity(in);
            finish();
        }
    }
}
