package com.lesgirls.network.model.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by victor on 13.07.16.
 */
public class AttachmentDeserializer implements JsonDeserializer<Attachment>{
    @Override
    public Attachment deserialize(JsonElement elAttachment, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        boolean b = false;
        Attachment attachment = new Attachment();
        b = (elAttachment.getAsJsonObject().get(Attachment.ID) == null) || (elAttachment.getAsJsonObject().get(Attachment.ID) instanceof JsonNull);
        attachment.setID(b?0:elAttachment.getAsJsonObject().get(Attachment.ID).getAsLong());

        b = (elAttachment.getAsJsonObject().get(Attachment.CLASS) == null) || (elAttachment.getAsJsonObject().get(Attachment.CLASS) instanceof JsonNull);
        attachment.setClass(b?"":elAttachment.getAsJsonObject().get(Attachment.CLASS).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.FOREING_ID) == null) || (elAttachment.getAsJsonObject().get(Attachment.FOREING_ID) instanceof JsonNull);
        attachment.setForeignID(b?"":elAttachment.getAsJsonObject().get(Attachment.FOREING_ID).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.FILENAME) == null) || (elAttachment.getAsJsonObject().get(Attachment.FILENAME) instanceof JsonNull);
        attachment.setFilename(b?"":elAttachment.getAsJsonObject().get(Attachment.FILENAME).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.DIR) == null) || (elAttachment.getAsJsonObject().get(Attachment.DIR) instanceof JsonNull);
        attachment.setDir(b?"":elAttachment.getAsJsonObject().get(Attachment.DIR).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.MIMETYPE) == null) || (elAttachment.getAsJsonObject().get(Attachment.MIMETYPE) instanceof JsonNull);
        attachment.setMimetype(b?"":elAttachment.getAsJsonObject().get(Attachment.MIMETYPE).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.TYPE) == null) || (elAttachment.getAsJsonObject().get(Attachment.TYPE) instanceof JsonNull);
        attachment.setType(b?"":elAttachment.getAsJsonObject().get(Attachment.TYPE).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.FILESIZE) == null) || (elAttachment.getAsJsonObject().get(Attachment.FILESIZE) instanceof JsonNull);
        attachment.setFilesize(b?0:elAttachment.getAsJsonObject().get(Attachment.FILESIZE).getAsInt());

        b = (elAttachment.getAsJsonObject().get(Attachment.HEIGHT) == null) || (elAttachment.getAsJsonObject().get(Attachment.HEIGHT) instanceof JsonNull);
        attachment.setHeight(b?0:elAttachment.getAsJsonObject().get(Attachment.HEIGHT).getAsInt());

        b = (elAttachment.getAsJsonObject().get(Attachment.WIDTH) == null) || (elAttachment.getAsJsonObject().get(Attachment.WIDTH) instanceof JsonNull);
        attachment.setWidth(b?0:elAttachment.getAsJsonObject().get(Attachment.WIDTH).getAsInt());

        b = (elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION) == null) || (elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION) instanceof JsonNull);
        attachment.setDescription(b?"":elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION).getAsString());

        b = (elAttachment.getAsJsonObject().get(Attachment.CREATED) == null) || (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) instanceof JsonNull)||(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString().isEmpty())||(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString().equals("0000-00-00 00:00:00"));
        attachment.setCreated(b?new Date(0): DateOperator.StringToDate(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString()));

        b = (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) == null) || (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) instanceof JsonNull)||(elAttachment.getAsJsonObject().get(Attachment.MODIFIED).getAsString().isEmpty())||(elAttachment.getAsJsonObject().get(Attachment.MODIFIED).getAsString().equals("0000-00-00 00:00:00"));
        attachment.setModified(b?new Date(0):DateOperator.StringToDate(elAttachment.getAsJsonObject().get(Attachment.MODIFIED).getAsString()));

        return attachment;
    }
}
