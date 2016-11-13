package com.lesgirls.network;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by victor on 25.06.16.
 */
public interface UserContract {
    @POST("register")
    Call<JsonObject> registration(@Body JsonObject userInfo);
    @POST("login")
    Call<JsonObject> login(@Body JsonObject userInfo);
    @POST("getUserProfile")
    Call<JsonObject> getProfile(@Body JsonObject userInfo);
    @Multipart
    @POST("updateAvatar")
    Call<JsonObject> updateAvatar(@Part MultipartBody.Part userId, @Part MultipartBody.Part file);
    @Multipart
    @POST("addImages")
    Call<JsonObject> addImage(@Part MultipartBody.Part token, @Part MultipartBody.Part file);
    @Multipart
    @POST("addImages")
    Call<JsonObject> downloadImage(@Part MultipartBody.Part token, @Part MultipartBody.Part id_photo);
    @Multipart
    @POST("getRandomUsers")
    Call<JsonObject> getRandomUsers(@Part MultipartBody.Part id, @Part MultipartBody.Part token);
    @POST("getUserImages")
    Call<JsonObject> myPhoto(@Body JsonObject att);
    @POST("deleteImage")
    Call<JsonObject> deleteMyPhoto(@Body JsonObject att);
    @POST("updateProfileSettings")
    Call<JsonObject> updateProfile(@Body JsonObject att);
    @POST("addActivity")
    Call<JsonObject> addActivity(@Body JsonObject att);
    @Headers({"Cache-Control: max-age=0"})
    @POST("getActivities")
    Call<JsonObject> getActivity(@Body JsonObject att);
    @POST("block")
    Call<JsonObject> block(@Body JsonObject att);
    @POST("addMessage")
    Call<JsonObject> addMessage(@Body JsonObject att);


}
