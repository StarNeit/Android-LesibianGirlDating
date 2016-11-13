package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lesgirls.network.model.GetActivityResponse;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by victor on 28.07.16.
 */
public class GetActivityLoader extends BaseLoader {
    private static final String TAG = "GetActivityLoader";
    private JsonObject obj;
    public GetActivityLoader(Context context, JsonObject obj) {
        super(context);
        this.obj = obj;
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        Call<JsonObject> call = service.getActivity(this.obj);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new GetActivityResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
