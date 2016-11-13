package com.lesgirls.network.model.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by victor on 30.07.16.
 */
public class ListDataActivityDeserialiser implements JsonDeserializer<ListDataActivity> {
    @Override
    public ListDataActivity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ListDataActivity result = new ListDataActivity();

        JsonArray arr = json.getAsJsonArray();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AppActivity.class, new AppActivityDeserializer())
                .registerTypeAdapter(UserFrom.class, new UserFromDeserialiser())
                .create();

        for(JsonElement el : arr){
            DataActivity act = gson.fromJson(el,DataActivity.class);
            result.add(act);
        }

        return result;
    }
}
