package com.lesgirls.network;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;

/**
 * Created by victor on 13.07.16.
 */
public class Random10Loader extends BaseLoader {
    private static final String TAG = "LoginLoader";
    private final long id;
    private final String token;

    public Random10Loader(Context context, long id, String token) {
        super(context);
        this.id = id;
        this.token = token;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        MultipartBody.Part userToken = MultipartBody.Part.createFormData("data[token]", this.token);
        MultipartBody.Part userID = MultipartBody.Part.createFormData("data[Attachment][id]", String.valueOf(this.id));
        Call<JsonObject> call = service.getRandomUsers(userID, userToken);
        JsonObject result = call.execute().body();
        return new Random10Response().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
