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
 * Created by victor on 23.07.16.
 */
public class MyPhotoListDeserializer implements JsonDeserializer<MyPhotoList> {
    @Override
    public MyPhotoList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        MyPhotoList result = new MyPhotoList();
        JsonArray arr = null;
        try {
            arr = json.getAsJsonArray();
        }
        catch (Exception ex){
            ex.printStackTrace();
            arr = new JsonArray();
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Attachment.class, new AttachmentMyPhotoDeserializer())
                .create();
        for(JsonElement us : arr){
            try {
                if(us == null) continue;
                Attachment att = gson.fromJson(us, Attachment.class);
                if (att == null) continue;
                result.add(att);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
