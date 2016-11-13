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
 * Created by victor on 14.07.16.
 */
public class RandomUserListDeserializer implements JsonDeserializer<RandomUserList> {
    @Override
    public RandomUserList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RandomUserList result = new RandomUserList();
        JsonArray arr = null;
        try {
            arr = json.getAsJsonArray();
        }
        catch (Exception ex){
            ex.printStackTrace();
            arr = new JsonArray();
        }


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(Country.class, new CountryDeserializer())
                .registerTypeAdapter(Attachment.class, new AttachmentDeserializer())
                .create();
        for(JsonElement us : arr){
            try {
                if(us == null) continue;
                AppUser uss = gson.fromJson(us, AppUser.class);
                if (uss == null) continue;
                result.add(uss);
                if(result.size()>=10) break;
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
