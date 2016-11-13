package com.lesgirls.network.model.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;

/**
 * Created by victor on 17.07.16.
 */
public class AttachmentSerializer implements JsonSerializer<Attachment> {
    @Override
    public JsonElement serialize(Attachment src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(Attachment.ID, src.getID());
        result.addProperty(Attachment.CLASS, src.getClass_());
        result.addProperty(Attachment.FOREING_ID, src.getForeignID());
        result.addProperty(Attachment.FILENAME, src.getFilename());
        result.addProperty(Attachment.DIR, src.getDir());
        result.addProperty(Attachment.MIMETYPE, src.getMimetype());
        result.addProperty(Attachment.TYPE, src.getType());
        result.addProperty(Attachment.FILESIZE, src.getFilesize());
        result.addProperty(Attachment.HEIGHT, src.getHeight());
        result.addProperty(Attachment.WIDTH, src.getWidth());
        result.addProperty(Attachment.DESCRIPTION, src.getDescription());
        result.addProperty(Attachment.MODIFIED, DateOperator.DateToString(src.getModified()));
        result.addProperty(Attachment.CREATED, DateOperator.DateToString(src.getCreated()));
        return result;
    }
}
