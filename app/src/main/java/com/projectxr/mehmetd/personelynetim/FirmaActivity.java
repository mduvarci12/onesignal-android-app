package com.projectxr.mehmetd.personelynetim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

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
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    public List<Firmam> firmaArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences;
        Intent i = getIntent();
        String id = i.getStringExtra("key");
        String playerID = i.getStringExtra("playerID");

        Call<playerId> call2 = RetrofitClient.getInstance().getApi().setPlayerId(id,playerID);
        call2.enqueue(new Callback<playerId>() {
            @Override
            public void onResponse(Call<playerId> call, Response<playerId> response) {
                //  System.out.println(userKEY);
                //  System.out.println(playerID);

            }
            @Override
            public void onFailure(Call<playerId> call, Throwable t) {

            }
        });

        getFirmaData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);


        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
    }
    public void getFirmaData()
    {  SharedPreferences sharedPreferences;

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
        SharedPreferences sharedPreferences;
        sharedPreferences = this.getSharedPreferences("com..mehmetd.personelynetim", Context.MODE_PRIVATE);
      sharedPreferences.edit().putString("username", "no").commit();
        sharedPreferences.edit().putString("password", "no").commit();
        sharedPreferences.edit().putString( "username","no");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);


    }
}
