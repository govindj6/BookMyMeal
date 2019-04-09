package com.example.bookmymeal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText etName,etMobile,etEmail,etPassword;
    Button btnRegistration;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        getSupportActionBar().hide();
        etName=findViewById(R.id.etName);
        etMobile=findViewById(R.id.etMobile);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnRegistration=findViewById(R.id.btnRegister);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=etName.getText().toString();
                final String mobile=etMobile.getText().toString();
                final String email=etEmail.getText().toString();
                final String password=etPassword.getText().toString();
                final String api=Server.SERVER_ADDRESS+"/register_User.jsp";
                final ProgressDialog pd=new ProgressDialog(RegistrationActivity.this);
                pd.setMessage("Registering...");
                pd.show();


                if(name.trim().length() == 0) {
                    etName.setError("Enter Name");
                    return;
                }
                else if(password.length()==0)
                {
                    etPassword.setError("Enter password");
                    return;
                }
                else if(password.length()<6 || password.length()>10)
                {
                    etPassword.setError("Kepp password 6 to 10 letter long");
                    return;
                }
                else if(mobile.length()==0)
                {
                    etMobile.setError("Enter mobile");
                    return;
                }
                else if(mobile.length()!=10)
                {
                    etMobile.setError("Mobile must have 10 digit");
                    return;
                }
                else{
                    try{
                        Long.parseLong(mobile);
                        StringRequest request=new StringRequest(Request.Method.POST, api, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.dismiss();
                                Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.contains("success")){
                                    Toast.makeText(RegistrationActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },null){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hm=new HashMap<String, String>();
                                hm.put("name",name);
                                hm.put("mobile",mobile);
                                hm.put("email",email);
                                hm.put("password",password);
                                return hm;
                            }
                        };
                        RequestQueue queue= Volley.newRequestQueue(RegistrationActivity.this);
                        queue.add(request);
                    }
                    catch (Exception e)
                    {
                        etMobile.setError("Only digit allowed");
                        return;
                    }
                }

            }
        });
    }
}
