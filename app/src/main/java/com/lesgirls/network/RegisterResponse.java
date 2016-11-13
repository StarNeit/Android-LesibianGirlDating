package com.lesgirls.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.model.RegistryResponse;
import com.lesgirls.network.model.RegistryUserResponse;
import com.lesgirls.network.model.RegistryUserResponseDeserializer;

public class RegisterResponse extends Response {
    private RegistryResponse response;

    public RegistryUserResponse getData(){
        Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RegistryUserResponse.class, new RegistryUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, RegistryResponse.class);
        return response.getData();
    }
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegistryUserResponse.class, new RegistryUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, RegistryResponse.class);
        return response.getStatus();
    }
    public String getMessageError(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegistryUserResponse.class, new RegistryUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, RegistryResponse.class);
        return response.getMessageError();
    }
}
