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

public class FoodPackagesActivity extends Fragment {
    ListView lv_package;
    ArrayList<FoodPackage>al;
    ArrayAdapter<FoodPackage>adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.package_fragment, null);
        lv_package=v.findViewById(R.id.foodPackages_lv);
        String api=Server.SERVER_ADDRESS+"/getpackagelist.jsp";
        new PackageTask().execute(api);
        return v;
    }
    class PackageTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data="";
            try{
                HttpURLConnection con=(HttpURLConnection)new URL(strings[0]).openConnection();
                con.setRequestMethod("GET");
                con.connect();
                InputStream in=con.getInputStream();
                while (true){
                    int x=in.read();
                    if(x==-1)
                        break;
                    data += (char)x;
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
                JSONArray arr=new JSONArray(s);
                for (int i=0;i<arr.length();i++){
                    JSONObject obj=arr.getJSONObject(i);

                    int id=obj.getInt("id");
                    String name=obj.getString("name");
                    int price=obj.getInt("price");
                    String desc=obj.getString("description");
                    String package_image = Server.SERVER_ADDRESS + "/food_images/" + obj.getString("package_image");

                    FoodPackage p=new FoodPackage(id,name,price,desc,package_image);
                    al.add(p);
                }
                adapter=new FoodPackageAdapter(getActivity(),al);
                lv_package.setAdapter(adapter);

            }catch(Exception e){
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
