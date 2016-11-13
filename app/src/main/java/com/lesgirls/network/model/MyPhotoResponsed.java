package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.MyPhotoList;

import java.util.ArrayList;

/**
 * Created by victor on 23.07.16.
 */
public class MyPhotoResponsed {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private MyPhotoList data;

    public int getStatus(){return this.status;}
    public String getMessage(){return this.message;}
    public MyPhotoList getData(){return this.data;}
}
