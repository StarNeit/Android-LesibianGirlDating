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
public class BlockLoader extends BaseLoader {
    private static final String TAG = "GetActivityLoader";
    private JsonObject obj;
    public BlockLoader(Context context, JsonObject json) {
        super(context);
        this.obj = json;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.block(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new SimpleResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
