package com.projectxr.mehmetd.personelynetim;

import android.content.Intent;
import android.os.Debug;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class Firmam
{
    public String firmaAdı;
    public String firmaId;
    public String foto;
}

public class FirmaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    public List<Firmam> firmaArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);
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
   //            Item f =response.body();
Log.d("response", "basarili");
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


}
