package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 23.07.16.
 */
public class SimpleResponsed {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public int getStatus(){return this.status;}
    public String getMessage(){return  this.message;}
}
