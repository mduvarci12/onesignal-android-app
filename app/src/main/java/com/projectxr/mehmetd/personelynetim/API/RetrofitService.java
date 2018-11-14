package com.projectxr.mehmetd.personelynetim.API;

import com.projectxr.mehmetd.personelynetim.models.BildirimResponse;
import com.projectxr.mehmetd.personelynetim.models.FeedBackModel;
import com.projectxr.mehmetd.personelynetim.models.Item;
import com.projectxr.mehmetd.personelynetim.models.LoginResponse;
import com.projectxr.mehmetd.personelynetim.models.playerId;
import com.projectxr.mehmetd.personelynetim.models.sonMessage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password

    );
    @FormUrlEncoded
    @POST("users/set_player_id")
    Call<playerId> setPlayerId(
            @Field("user_key") String user_key,
            @Field("player_id") String player_id
    );
    @FormUrlEncoded
    @POST(RetrofitClient.BASE_URL + "firma/get_user_firma")
    Call<Item> getFirma(
            @Field("user_key") String user_key
    );
    @FormUrlEncoded
    @POST("users/departmanlar")
    Call<String> postDepartman(
            @Field("firma_id") String firma_id);

    @FormUrlEncoded
    @POST("/bildirim")
    Call<sonMessage> postMessage(
            @Field("content") String context,
            @Field("user_type") String user_type,
            @Field("firma_id") String firma_id,
            @Field("user_key") String user_key,
            @Field("device_id") String deviceId
    );

    @FormUrlEncoded
    @POST("/bildirimlerim")
    Call<BildirimResponse> bildirimlerim(
            @Field("player_id") String PlayerID
    );
    @FormUrlEncoded
    @POST("/feedback")
    Call<FeedBackModel> feedback(
            @Field("bildirim_id") String bildirimID
    );


}
