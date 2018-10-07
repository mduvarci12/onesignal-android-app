package com.projectxr.mehmetd.personelynetim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.onesignal.OneSignal;
import com.projectxr.mehmetd.personelynetim.API.RetrofitClient;
import com.projectxr.mehmetd.personelynetim.models.LoginResponse;
import com.projectxr.mehmetd.personelynetim.models.playerId;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{



    private EditText editTextEmail, editTextPassword;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private String userKEY;
    private String playerID;


    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        sharedPreferences = this.getSharedPreferences("com.projectxr.mehmetd.personelynetim", Context.MODE_PRIVATE);



        //getImages();

            editTextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(false)
                .init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                if (registrationId != null)
                playerID=userId;
                System.out.println(playerID + "aqplayeridsi");
            }
        });
    }

    public void LoginButton(View view) {
        userLogin();

    }

    private void userLogin()
    {

        final String username = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTextEmail.setError("Kullanıcı adı giriniz");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Şifrenizi Giriniz");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 4) {
            editTextPassword.setError("Şifreniz en az altı karakter uzunluğunda olmalıdır");
            editTextPassword.requestFocus();
            return;
        }
            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userLogin(username,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();

                        if(loginResponse.getKey()!=null){
                            userKEY= loginResponse.getKey();
                            sharedPreferences.edit().putString("userKey",loginResponse.getKey()).apply();
                             String myKey = sharedPreferences.getString("userKey","bulunamadı" );

                            Intent i = new Intent(MainActivity.this,FirmaActivity.class);
                            i.putExtra("key",userKEY);
                    startActivity(i);
                } else {Toast.makeText(MainActivity.this," kullanıcı adı veya şifre hatalı", Toast.LENGTH_LONG).show();}
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    t.printStackTrace();
                }

            });
        String myKey = sharedPreferences.getString("userKey","bulunamadı" );
        userKEY=myKey;
        Log.d("userKey", userKEY);
        Log.d("userID", playerID);
        Call<playerId> call2 = RetrofitClient.getInstance().getApi().setPlayerId(userKEY,playerID);
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
  //  private void getImages(){
        //mNames.add("Link gelecek");
        //mIMageUrls.add("isim");
        //initRecyclerView();
    }
    /*
        private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
    */
}
