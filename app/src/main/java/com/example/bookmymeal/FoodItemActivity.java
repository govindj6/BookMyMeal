package com.example.bookmymeal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FoodItemActivity extends Fragment {
    ListView food_lv;
    ArrayList<FoodItem>al;
    ArrayAdapter<FoodItem>adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.food_item_fragment,null);
        food_lv=v.findViewById(R.id.foodItems_lv);
        String api=Server.SERVER_ADDRESS+"/getFoodList.jsp";
        new FoodItemTask().execute(api);
        return v;
    }
    class FoodItemTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data="";
            try{
                HttpURLConnection con=(HttpURLConnection) new URL(strings[0]).openConnection();
                con.setRequestMethod("GET");
                con.connect();
                if (con.getResponseCode()==HttpURLConnection.HTTP_OK){
                    InputStream in=con.getInputStream();
                    while (true){
                        int x=in.read();
                        if(x==-1)
                            break;
                        data+=(char)x;
                    }
                }
            }catch (Exception e){
                data=e.toString();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                al=new ArrayList<>();
                JSONArray jsonArray=new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);
                    int id=object.getInt("id");
                    String name=object.getString("name");
                    int price=object.getInt("price");
                    String description=object.getString("description");
                    int categoryId=object.getInt("categoryId");
                    String categoryName=object.getString("categoryName");
                    String image=Server.SERVER_ADDRESS+"/food_images/"+object.getString("image");

                    FoodItem f=new FoodItem(id,name,price,description,categoryId,categoryName,image);
                    al.add(f);
                    System.out.print(f);
                }
                adapter=new FoodItemAdapter(getActivity(),al);
                food_lv.setAdapter(adapter);

            }catch (Exception e){
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
