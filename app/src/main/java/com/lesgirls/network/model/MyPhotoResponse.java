package com.lesgirls.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.MyPhotoList;
import com.lesgirls.network.model.entity.MyPhotoListDeserializer;

import java.util.ArrayList;

/**
 * Created by victor on 23.07.16.
 */
public class MyPhotoResponse extends Response {
    private MyPhotoResponsed response;
    public int getStatus(){
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(MyPhotoList.class, new MyPhotoListDeserializer())
            .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, MyPhotoResponsed.class);
        return response.getStatus();
    }
    public String getMessage(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MyPhotoList.class, new MyPhotoListDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, MyPhotoResponsed.class);
        return response.getMessage();
    }
    public MyPhotoList getData(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MyPhotoList.class, new MyPhotoListDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, MyPhotoResponsed.class);
        return response.getData();
    }
}
