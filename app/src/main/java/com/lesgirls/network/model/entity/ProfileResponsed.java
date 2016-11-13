package com.lesgirls.network.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 23.07.16.
 */
public class ProfileResponsed {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private AppUser data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public AppUser getData() {
        return data;
    }
}
