package com.projectxr.mehmetd.personelynetim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;
import com.projectxr.mehmetd.personelynetim.models.sonMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendActivity extends AppCompatActivity {
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

         text = findViewById(R.id.editText);
        String context = String.valueOf(text.getText());

    }
    public void sendMessage2(View v)
    {
         String mekanID = getIntent().getStringExtra("mekanID");
            String userType = getIntent().getStringExtra("user_type");
        String context = String.valueOf(text.getText());

        Call<String> call = RetrofitClient.getInstance().getApi().postMessage(context,userType,mekanID);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("sonResponse", "basarili");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("sonResponse", "basarisiz");
            }
        });

    }


}
