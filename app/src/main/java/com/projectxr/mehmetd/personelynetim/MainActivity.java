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

        String userName = sharedPreferences.getString("username","no" );
        String userPassword = sharedPreferences.getString("password","no" );
        String KEY = sharedPreferences.getString("userKey","no" );

        if(sharedPreferences.getString("username","a") == "a")
        {

        }else{
            Intent i = new Intent(MainActivity.this,FirmaActivity.class);
            i.putExtra("key",KEY);
            i.putExtra("playerID",playerID);
            startActivity(i);
        }

    }

    public void LoginButton(View view) {
        userLogin();

    }

    private void userLogin()
    {

        final String username = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

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
        if(username.equals("") && !password.equals(""))
        {
            Intent i = new Intent(MainActivity.this,FirmaActivity.class);
            i.putExtra("key",userKEY);
            startActivity(i);
        }

            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userLogin(username,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
                {
                    LoginResponse loginResponse = response.body();

                        if(loginResponse.getKey()!=null)
                        {
                            userKEY= loginResponse.getKey();
                            sharedPreferences.edit().putString("userKey",loginResponse.getKey()).apply();

                            sharedPreferences.edit().putString("username", username).apply();
                            sharedPreferences.edit().putString("password", password).apply();

                                Intent i = new Intent(MainActivity.this,FirmaActivity.class);
                                i.putExtra("key",userKEY);
                                startActivity(i);
                                  editTextEmail.setText("");
                             editTextPassword.setText("");
                        } else {Toast.makeText(MainActivity.this," kullanıcı adı veya şifre hatalı", Toast.LENGTH_LONG).show();}
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    t.printStackTrace();
                }

                            }       );
        String myKey = sharedPreferences.getString("userKey","bulunamadı" );
        userKEY=myKey;

    }

}
