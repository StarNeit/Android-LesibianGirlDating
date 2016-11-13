package com.lesgirls.network.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lesgirls.network.Response;
import com.lesgirls.network.model.entity.AppUser;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.AttachmentDeserializer;
import com.lesgirls.network.model.entity.Country;
import com.lesgirls.network.model.entity.CountryDeserializer;
import com.lesgirls.network.model.entity.ProfileResponsed;
import com.lesgirls.network.model.entity.User;
import com.lesgirls.network.model.entity.UserDeserializer;

/**
 * Created by victor on 23.07.16.
 */
public class ProfileResponse extends Response {
    private ProfileResponsed response;
    public int getStatus(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(Country.class, new CountryDeserializer())
                .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, ProfileResponsed.class);
        return response.getStatus();
    }
    public String getMessage(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(Country.class, new CountryDeserializer())
                .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, ProfileResponsed.class);
        return response.getMessage();
    }
    public AppUser getData(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(Country.class, new CountryDeserializer())
                .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                .create();
        JsonObject obj = getTypedAnswer();
        response = gson.fromJson(obj, ProfileResponsed.class);
        return response.getData();
    }

}
