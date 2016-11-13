package com.lesgirls.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.entity.ListDataActivity;
import com.lesgirls.network.model.entity.ListDataActivityDeserialiser;
import com.lesgirls.network.model.entity.MyPhotoList;

/**
 * Created by victor on 28.07.16.
 */
public class GetActivityResponse extends Response {
    private GetActivityResponsed response;
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ListDataActivity.class, new ListDataActivityDeserialiser())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, GetActivityResponsed.class);
        return response.getStatus();
    }
    public String getMessage(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ListDataActivity.class, new ListDataActivityDeserialiser())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, GetActivityResponsed.class);
        return response.getMessage();
    }

    public ListDataActivity getData(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ListDataActivity.class, new ListDataActivityDeserialiser())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, GetActivityResponsed.class);
        return response.getData();
    }

}
