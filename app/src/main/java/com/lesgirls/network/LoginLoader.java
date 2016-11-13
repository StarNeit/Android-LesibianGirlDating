package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by victor on 28.06.16.
 */
public class LoginLoader extends BaseLoader{
    private static final String TAG = "LoginLoader";
    private final JsonObject obj;

    public LoginLoader(Context context, JsonObject obj) {
        super(context);
        this.obj = obj;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.login(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new LoginResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
