package com.lesgirls.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.Response;

/**
 * Created by victor on 23.07.16.
 */
public class SimpleResponse extends Response {
    private SimpleResponsed response;
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, SimpleResponsed.class);
        return response.getStatus();
    }
    public String getMessage(){
        Gson gson = new GsonBuilder()
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, SimpleResponsed.class);
        return response.getMessage();
    }
}
