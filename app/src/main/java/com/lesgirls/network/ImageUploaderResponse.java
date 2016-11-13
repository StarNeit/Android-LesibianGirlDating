package com.lesgirls.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.model.AvatarUpdatedResponse;
import com.lesgirls.network.model.LogindedUserResponseDeserializer;
import com.lesgirls.network.model.LoginedResponse;
import com.lesgirls.network.model.LoginedUserResponse;

/**
 * Created by victor on 11.07.16.
 */
public class ImageUploaderResponse extends Response {
    private ImageUploaderResponse response;
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, ImageUploaderResponse.class);
        return response.getStatus();
    }
    public String getMessage(){
        Gson gson = new GsonBuilder()
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, ImageUploaderResponse.class);
        return response.getMessage();
    }
}
