package com.projectxr.mehmetd.personelynetim;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;
import com.projectxr.mehmetd.personelynetim.models.Item;
import com.projectxr.mehmetd.personelynetim.models.ItemResponse;
import com.projectxr.mehmetd.personelynetim.models.playerId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




class Firmam
{
    SharedPreferences sharedPreferences;
    public String firmaAdı;
    public String firmaId;
    public String foto;
}

public class FirmaActivity extends AppCompatActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {}

        return false;
        // Disable back button..............
    }

    SharedPreferences sharedPreferences;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    public List<Firmam> firmaArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);
        Intent i = getIntent();
        String id = i.getStringExtra("key");

        final String playerID = i.getStringExtra("playerID");
        sharedPreferences = this.getSharedPreferences("com.projectxr.mehmetd.personelynetim", Context.MODE_PRIVATE);

        String username2;
        username2 = sharedPreferences.getString("txt", ".");
       username2=  username2.toUpperCase();
        Toast.makeText(getApplicationContext(),"MuhattAPP'a Hoşgeldin " + username2,Toast.LENGTH_LONG).show();



        Call<playerId> call2 = RetrofitClient.getInstance().getApi().setPlayerId(id,playerID);
        call2.enqueue(new Callback<playerId>() {
            @Override
            public void onResponse(Call<playerId> call, Response<playerId> response) {
                //  System.out.println(userKEY);
                //  System.out.println(playerID);
       //         Log.e("PlayerIDGitti",playerID);

            }
            @Override
            public void onFailure(Call<playerId> call, Throwable t) {

            }
        });

        getFirmaData();



        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
    }
    public void getFirmaData()
    {

        Intent i = getIntent();
       String id = i.getStringExtra("key");

        Call<Item> call = RetrofitClient.getInstance().getApi().getFirma(id);
       call.enqueue(new Callback<Item>() {
           @Override
           public void onResponse(Call<Item> call, Response<Item> response) {
               int a=0;
           //    List<ItemResponse> firmalar = response.body().getFirmalar();
               List<ItemResponse> items = response.body().getFirmalar();
               for (a=0;a<items.size();a++)
               {
                   Firmam firma = new Firmam();

                   ItemResponse IP = items.get(a);

                   firma.foto = IP.getMekanFoto();
                   firma.firmaAdı = IP.getTitle();
                   firma.firmaId = IP.getMekanId();

                   ListItem list = new ListItem(IP.getMekanFoto(),IP.getTitle(),IP.getMekanId());

                   listItems.add(list);

                    adapter = new MyAdapter(listItems, getApplicationContext());
               //    firmaArray.add(firma);
                     recyclerView.setAdapter(adapter);

                   Log.d("tag", IP.getMekanFoto());
               }

           }

           @Override
           public void onFailure(Call<Item> call, Throwable t) {

               Log.d("response", "basarisiz");
           }
       });

    }


    public void signoutMethod(View view) {
        sharedPreferences.edit().putBoolean("flag", false).commit();
        finishAndRemoveTask();

        finishActivity(1);
        System.exit(1);
        System.exit(0);
    }
}
