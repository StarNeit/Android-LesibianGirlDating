package com.lesgirls.network.model.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by victor on 29.07.16.
 */
public class AppActivityDeserializer implements JsonDeserializer<AppActivity> {
    private static final String ID = "id";
    private static final String USER_TO = "user_to_id";
    private static final String USER_FROM = "user_from_id";
    private static final String ACTIVITY = "status";
    private static final String IS_READY = "is_read";
    private static final String HIDE_USER_TO = "hide_user_to_id";
    private static final String HIDE_USER_FROM = "hide_user_from_id";
    private static final String MODIFIED = "modified";
    private static final String CREATED = "created";

    @Override
    public AppActivity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        AppActivity result = new AppActivity();
        boolean b = false;

        b = (json.getAsJsonObject().get(ID) == null) || (json.getAsJsonObject().get(ID) instanceof JsonNull);
        result.setID(b?0:json.getAsJsonObject().get(ID).getAsLong());

        b = (json.getAsJsonObject().get(USER_TO) == null) || (json.getAsJsonObject().get(USER_TO) instanceof JsonNull);
        result.setUserTo(b?0:json.getAsJsonObject().get(USER_TO).getAsLong());

        b = (json.getAsJsonObject().get(USER_FROM) == null) || (json.getAsJsonObject().get(USER_FROM) instanceof JsonNull);
        result.setUserFrom(b?0:json.getAsJsonObject().get(USER_FROM).getAsLong());

        b = (json.getAsJsonObject().get(ACTIVITY) == null) || (json.getAsJsonObject().get(ACTIVITY) instanceof JsonNull);
        result.setActivityID(b?0:json.getAsJsonObject().get(ACTIVITY).getAsInt());

        b = (json.getAsJsonObject().get(IS_READY) == null) || (json.getAsJsonObject().get(IS_READY) instanceof JsonNull);
        result.setReady(b?false:json.getAsJsonObject().get(IS_READY).getAsInt()>0);

        b = (json.getAsJsonObject().get(HIDE_USER_TO) == null) || (json.getAsJsonObject().get(HIDE_USER_TO) instanceof JsonNull);
        result.setHideUserToID(b?false:json.getAsJsonObject().get(HIDE_USER_TO).getAsInt()>0);

        b = (json.getAsJsonObject().get(HIDE_USER_FROM) == null) || (json.getAsJsonObject().get(HIDE_USER_FROM) instanceof JsonNull);
        result.setHideUserFromID(b?false:json.getAsJsonObject().get(HIDE_USER_FROM).getAsInt()>0);

        b = (json.getAsJsonObject().get(MODIFIED) == null) || (json.getAsJsonObject().get(MODIFIED) instanceof JsonNull)||(json.getAsJsonObject().get(MODIFIED).getAsString().isEmpty())||(json.getAsJsonObject().get(MODIFIED).getAsString().equals("0000-00-00 00:00:00"));
        result.setModified(b?new Date(0): DateOperator.StringToDate(json.getAsJsonObject().get(MODIFIED).getAsString()));

        b = (json.getAsJsonObject().get(CREATED) == null) || (json.getAsJsonObject().get(CREATED) instanceof JsonNull)||(json.getAsJsonObject().get(CREATED).getAsString().isEmpty())||(json.getAsJsonObject().get(CREATED).getAsString().equals("0000-00-00 00:00:00"));
        result.setCreated(b?new Date(0): DateOperator.StringToDate(json.getAsJsonObject().get(CREATED).getAsString()));

        return result;
    }
}
