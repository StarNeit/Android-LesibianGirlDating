package com.lesgirls.network.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by victor on 25.06.16.
 */
public class RegistryUserRequestSerializer implements JsonSerializer<RegistryUserRequest> {
    @Override
    public JsonElement serialize(RegistryUserRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty(RegistryUserResponse.USERNAME, src.getUsername());
        obj.addProperty(RegistryUserResponse.PASSWORD, src.getPassword());
        obj.addProperty(RegistryUserResponse.EMAIL, src.getEmail());
        obj.addProperty(RegistryUserResponse.DEVICE_ID, src.getDeviceID());
        obj.addProperty(RegistryUserResponse.DEVICE_TYPE, src.getDeviceType());
        obj.addProperty("latitude",src.getLocation().latitude);
        obj.addProperty("longitude", src.getLocation().longitude);
        obj.addProperty("registration_id", src.getRegistrationID());
        return  obj;
    }
}
