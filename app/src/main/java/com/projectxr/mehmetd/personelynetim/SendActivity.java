package com.projectxr.mehmetd.personelynetim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        String context2 = text.getText().toString();
       Call<sonMessage> call = RetrofitClient.getInstance().getApi().postMessage(context2,userType,mekanID);
       call.enqueue(new Callback<sonMessage>() {
           @Override
           public void onResponse(Call<sonMessage> call, Response<sonMessage> response) {
                text.setText("");
                Toast.makeText(getApplicationContext(),"Gönderildi",Toast.LENGTH_LONG).show();
           }

           @Override
           public void onFailure(Call<sonMessage> call, Throwable t) {

           }
       });
    }


}
