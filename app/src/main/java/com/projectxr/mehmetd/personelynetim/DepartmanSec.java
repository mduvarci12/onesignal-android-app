package com.projectxr.mehmetd.personelynetim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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


                String[] string;
                string = strings;


                // ADAPTERDE KULLANILACAK ARRAY strings
                Log.e("string arrayi0", strings[0]);
                Log.e("string arrayi1", strings[1]);
                Log.e("string arrayi2", strings[2]);
                Log.e("string arrayisize"," "+  strings.length);

                ArrayAdapter<String > adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_firma_sec, strings);
                ListView listView = findViewById(R.id.listView1);
                listView.setAdapter(adapter);







            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ASDASD" , "FAIL");
            }
        });

        }


}



