package com.projectxr.mehmetd.personelynetim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;
import com.projectxr.mehmetd.personelynetim.models.BildirimResponse;
import com.projectxr.mehmetd.personelynetim.models.Bildirimler;
import com.projectxr.mehmetd.personelynetim.models.FeedBackModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bildirimlerim extends AppCompatActivity {
     public  String[] bildirimler;
    public  String[] bildirimler2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirimlerim);

        sharedPreferences = this.getSharedPreferences("com.projectxr.mehmetd.personelynetim", Context.MODE_PRIVATE);
        String playerid = sharedPreferences.getString("playerID", "bulunamadı3");

        sharedPreferences = this.getSharedPreferences("com.projectxr.mehmetd.personelynetim", Context.MODE_PRIVATE);



        Call<BildirimResponse> call =RetrofitClient.getInstance().getApi().bildirimlerim(playerid);
        call.enqueue(new Callback<BildirimResponse>() {
            @Override
            public void onResponse(Call<BildirimResponse> call, Response<BildirimResponse> response) {
            List<Bildirimler> bildirims = response.body().getBildirimler();

              bildirimler= new String[bildirims.size()];
              bildirimler2= new String[bildirims.size()];

                for (int a=0;a<bildirims.size();a++)
                {
                    bildirimler2[a]=bildirims.get(a).getBildirimId();
                    bildirimler[a]= (bildirims.get(a).getContent());
                    if (bildirims.get(a).getStatus()==1)
                    {
                        bildirims.get(a).setContent("ilgilenildi");
                        bildirimler[a]="ilgilenildi";
                    }
                }
                Collections.reverse(Arrays.asList(bildirimler));
                Collections.reverse(Arrays.asList(bildirimler2));

                ListView listView= findViewById(R.id.lvBildirim);
                ArrayAdapter<String > adapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1, bildirimler);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if (bildirimler[position].equals("ilgilenildi"))
                        { Toast.makeText(getApplicationContext(),"İstek ile zaten ilgilenildi",Toast.LENGTH_LONG).show();}
                        else{
                        Call<FeedBackModel> call3 = RetrofitClient.getInstance().getApi().feedback(bildirimler2[position]);
                        call3.enqueue(new Callback<FeedBackModel>() {
                            @Override
                            public void onResponse(Call<FeedBackModel> call, Response<FeedBackModel> response) {
                                { Toast.makeText(getApplicationContext(),"ilgileneceğinizi belirttiniz",Toast.LENGTH_LONG).show();}
                            }

                            @Override
                            public void onFailure(Call<FeedBackModel> call, Throwable t) {

                            }
                        });
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<BildirimResponse> call, Throwable t) {

            }
        });
    }
}
   // public void signout(View view) {
 //       sharedPreferences.edit().putBoolean("flag", false).commit();
 //       finishAndRemoveTask();

 //       finishActivity(1);
 //       System.exit(1);
 //       System.exit(0);
 //   }


