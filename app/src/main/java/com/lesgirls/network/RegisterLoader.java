package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;


public class RegisterLoader extends BaseLoader {
    private static final String TAG = "RegisterLoader";
    private final JsonObject obj;

    public RegisterLoader(Context context, JsonObject obj) {
        super(context);
        this.obj = obj;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.registration(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new RegisterResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
