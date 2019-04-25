package com.example.bookmymeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodaysSpecialActivity extends Fragment {
    RecyclerView rvToadaysSpecial;
    ArrayList<FoodItem> arrayList;
    TodaysSpecialAdapter todaysSpecialAdapter;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_special_fragment, null);
        Toast.makeText(getActivity(), "Today's Special", Toast.LENGTH_SHORT).show();
        rvToadaysSpecial=v.findViewById(R.id.rv_todaysSpecial);
        String api=Server.SERVER_ADDRESS+"/getTodaySpecial.jsp";
        requestQueue= Volley.newRequestQueue(getActivity());
        arrayList=new ArrayList<>();
        todaysSpecialAdapter=new TodaysSpecialAdapter(arrayList,getActivity());
        rvToadaysSpecial.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvToadaysSpecial.setAdapter(todaysSpecialAdapter);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(api, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        FoodItem f=new FoodItem();
                        f.setCategoryId(jsonObject.getInt("categoryId"));
                        f.setName(jsonObject.getString("name"));
                        f.setId(jsonObject.getInt("id"));
                        f.setDescription(jsonObject.getString("description"));
                        f.setPrice(jsonObject.getInt("price"));
                        f.setImage(Server.SERVER_ADDRESS+"/food_images/"+jsonObject.getString("image"));

                        arrayList.add(f);
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                todaysSpecialAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
        return v;
    }
}
