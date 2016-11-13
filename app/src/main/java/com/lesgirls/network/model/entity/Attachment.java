package com.lesgirls.network.model.entity;

import java.util.Date;

/**
 * Created by victor on 28.06.16.
 */
public class Attachment {
    public static String ID = "id";
    public static String CLASS = "class";
    public static String FOREING_ID = "foreign_id";
    public static String FILENAME = "filename";
    public static String DIR = "dir";
    public static String MIMETYPE = "mimetype";
    public static String TYPE = "type";
    public static String FILESIZE = "filesize";
    public static String HEIGHT = "height";
    public static String WIDTH = "width";
    public static String DESCRIPTION = "description";
    public static String MODIFIED = "modified";
    public static String CREATED = "created";

    private long id;
    private String class_;
    private String foreignID;
    private String filename;
    private String dir;
    private String mimetype;
    private String type;
    private long filesize;
    private int height;
    private int width;
    private String description;
    private Date modified;
    private Date created;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass(String class_) {
        this.class_ = class_;
    }

    public String getForeignID() {
        return foreignID;
    }

    public void setForeignID(String foreignID) {
        this.foreignID = foreignID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
