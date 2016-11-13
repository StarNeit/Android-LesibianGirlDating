package com.lesgirls.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitImageAPI {
    @POST("downloadImage")
    Call<ResponseBody> getImageDetails();
}