package com.lesgirls.network.model.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by victor on 23.07.16.
 */
public class AttachmentMyPhotoDeserializer implements JsonDeserializer<Attachment> {
    @Override
    public Attachment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        boolean b = false;
        Attachment result = new Attachment();
        JsonObject obj = (JsonObject) json.getAsJsonObject().get("Attachment");
        b = (obj.getAsJsonObject().get(Attachment.ID) == null) || (obj.getAsJsonObject().get(Attachment.ID) instanceof JsonNull);
        result.setID(b?0:obj.getAsJsonObject().get(Attachment.ID).getAsLong());
        b = (obj.getAsJsonObject().get(Attachment.FILESIZE) == null) || (obj.getAsJsonObject().get(Attachment.FILESIZE) instanceof JsonNull);
        result.setFilesize(b?0:obj.getAsJsonObject().get(Attachment.FILESIZE).getAsLong());
        b = (obj.getAsJsonObject().get(Attachment.MIMETYPE) == null) || (obj.getAsJsonObject().get(Attachment.MIMETYPE) instanceof JsonNull);
        result.setMimetype(b?"":obj.getAsJsonObject().get(Attachment.MIMETYPE).getAsString());
        return result;
    }
}
