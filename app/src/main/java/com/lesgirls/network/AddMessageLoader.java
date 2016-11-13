package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lesgirls.network.model.SimpleResponse;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by victor on 31.07.16.
 */
public class AddMessageLoader extends BaseLoader {
    private static final String TAG = "AddMessageLoader";
    private JsonObject obj;

    public AddMessageLoader(Context context, JsonObject obj) {
        super(context);
        this.obj = obj;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.addMessage(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new SimpleResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
