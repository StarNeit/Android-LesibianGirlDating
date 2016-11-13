package com.lesgirls.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.model.LogindedUserResponseDeserializer;
import com.lesgirls.network.model.LoginedResponse;
import com.lesgirls.network.model.LoginedUserResponse;
import com.lesgirls.network.model.Random10Responsed;
import com.lesgirls.network.model.entity.RandomUserList;
import com.lesgirls.network.model.entity.RandomUserListDeserializer;

/**
 * Created by victor on 14.07.16.
 */
public class Random10Response extends Response{
    private Random10Responsed response = null;

    public int getStatus(){
        if(response == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RandomUserList.class, new RandomUserListDeserializer())
                    .create();
            JsonObject obj = getTypedAnswer();
            response = gson.fromJson(obj, Random10Responsed.class);
        }
        return response.getStatus();
    }
    public String getMessage(){
        if(response == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Random10Responsed.class, new RandomUserListDeserializer())
                    .create();
            JsonObject obj = getTypedAnswer();
            response = gson.fromJson(obj, Random10Responsed.class);
        }
        return response.getMessage();
    }
    public RandomUserList getData(){
        if(response == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Random10Responsed.class, new RandomUserListDeserializer())
                    .create();
            JsonObject obj = getTypedAnswer();
            response = gson.fromJson(obj, Random10Responsed.class);
        }
        return response.getUsers();}
}
