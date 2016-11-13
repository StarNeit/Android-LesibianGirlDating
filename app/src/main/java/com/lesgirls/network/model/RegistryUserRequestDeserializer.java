package com.lesgirls.network.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by victor on 25.06.16.
 */
public class RegistryUserRequestDeserializer implements JsonDeserializer<RegistryUserRequest> {
    @Override
    public RegistryUserRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RegistryUserRequest result = new RegistryUserRequest();
        result.setUsername(json.getAsJsonObject().get(RegistryUserResponse.USERNAME).getAsString());
        result.setPassword(json.getAsJsonObject().get(RegistryUserResponse.PASSWORD).getAsString());
        result.setEmail(json.getAsJsonObject().get(RegistryUserResponse.EMAIL).getAsString());
        result.setDeviceID(json.getAsJsonObject().get(RegistryUserResponse.DEVICE_ID).getAsString());
        result.setDeviceType(json.getAsJsonObject().get(RegistryUserResponse.USERNAME).getAsInt());
        result.setLocation(new LatLng(json.getAsJsonObject().get("latitude").getAsDouble(),json.getAsJsonObject().get("longitude").getAsDouble()));
        return result;
    }
}
