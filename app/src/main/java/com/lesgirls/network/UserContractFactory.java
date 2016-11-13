package com.lesgirls.network;

import android.support.annotation.NonNull;

import com.lesgirls.LesGirls;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victor on 25.06.16.
 */
public class UserContractFactory {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    @NonNull
    public static UserContract getUserContract() {
        return getRetrofit().create(UserContract.class);
    }
    @NonNull
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(LesGirls.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }
}
