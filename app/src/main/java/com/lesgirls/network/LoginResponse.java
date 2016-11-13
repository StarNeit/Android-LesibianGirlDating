package com.lesgirls.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.model.LoginedResponse;
import com.lesgirls.network.model.LoginedUserResponse;
import com.lesgirls.network.model.RegistryResponse;
import com.lesgirls.network.model.LoginedUserResponse;
import com.lesgirls.network.model.LogindedUserResponseDeserializer;

/**
 * Created by victor on 28.06.16.
 */
public class LoginResponse extends Response {
    private LoginedResponse response;
    public String getToken(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LoginedUserResponse.class, new LogindedUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, LoginedResponse.class);
        return response.getToken();
    }
    public LoginedUserResponse getData(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LoginedUserResponse.class, new LogindedUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, LoginedResponse.class);
        return response.getData();
    }
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LoginedUserResponse.class, new LogindedUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, LoginedResponse.class);
        return response.getStatus();
    }
    public String getMessageError(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LoginedResponse.class, new LogindedUserResponseDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        //response = gson.fromJson(obj, LoginedResponse.class);
        return "Login/password error";//response.getMessageError();
    }
}
