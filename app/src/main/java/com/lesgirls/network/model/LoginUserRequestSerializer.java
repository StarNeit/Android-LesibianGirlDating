package com.lesgirls.network.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by victor on 28.06.16.
 */
public class LoginUserRequestSerializer  implements JsonSerializer<LoginUserRequest> {
    @Override
    public JsonElement serialize(LoginUserRequest src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty(RegistryUserResponse.EMAIL, src.getEmail());
        obj.addProperty(RegistryUserResponse.PASSWORD, src.getPassword());
        obj.addProperty(RegistryUserResponse.REGISTRATION_ID, src.getRegistrationID());
        obj.addProperty(RegistryUserResponse.DEVICE_ID, src.getDeviceID());
        obj.addProperty(RegistryUserResponse.DEVICE_TYPE, src.getDeviceType());
        return  obj;
    }
}
