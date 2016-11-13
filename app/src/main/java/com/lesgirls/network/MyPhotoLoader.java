package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lesgirls.network.BaseLoader;
import com.lesgirls.network.RequestResult;
import com.lesgirls.network.Response;
import com.lesgirls.network.UserContract;
import com.lesgirls.network.UserContractFactory;
import com.lesgirls.network.model.MyPhotoResponse;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by victor on 23.07.16.
 */
public class MyPhotoLoader extends BaseLoader {
    private final JsonObject obj;
    private static final String TAG = "MyPhotoLoader";

    public MyPhotoLoader(Context context, JsonObject obj) {
        super(context);
        this.obj = obj;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.myPhoto(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new MyPhotoResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
