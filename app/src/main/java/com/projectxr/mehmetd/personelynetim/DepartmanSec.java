package com.projectxr.mehmetd.personelynetim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;
import com.projectxr.mehmetd.personelynetim.models.departmanModel;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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

        String mekanID = getIntent().getStringExtra("mekanID");

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

                String[] strings = das.split(",");


                // ADAPTERDE KULLANILACAK ARRAY strings
                Log.e("string arrayi", strings[0]);



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ASDASD" , "FAIL");
            }
        });

        }


}



