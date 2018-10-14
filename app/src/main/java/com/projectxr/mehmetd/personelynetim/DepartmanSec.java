package com.projectxr.mehmetd.personelynetim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

class Departman {
    public String departmanTitle;
}

interface RetrofitService{
    @GET("/users")
    Call<ResponseBody> listRepos();//function to call api
}


public class DepartmanSec extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_sec);

        final String mekanID = getIntent().getStringExtra("mekanID");

        System.out.print(mekanID);
        Call<String> call = RetrofitClient.getInstance().getApi().postDepartman(mekanID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("departmanlar", response.body().toString());
                String result = response.body().toString();


                String asd = result.replace("[", "");
                String dsa = asd.replace("]", "");
                String das = dsa.replace("\"","");

                final String[] strings = das.split(",");


                String[] string;
                string = strings;


                // ADAPTERDE KULLANILACAK ARRAY strings
                Log.e("string arrayi0", strings[0]);
                Log.e("string arrayi1", strings[1]);
                Log.e("string arrayi2", strings[2]);
                Log.e("string arrayisize"," "+  strings.length);

                ListView listView = findViewById(R.id.listview);
                ArrayAdapter<String > adapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1, strings);
                listView.setAdapter(adapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                       // mekanID
                    Log.e("position",strings[position]);
                       Intent i = new Intent(DepartmanSec.this,SendActivity.class);
                       i.putExtra("mekanID", mekanID);
                       i.putExtra("user_type", strings[position]);
                       startActivity(i);

                   }

               });

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ASDASD" , "FAIL");
            }
        });

        }

}



