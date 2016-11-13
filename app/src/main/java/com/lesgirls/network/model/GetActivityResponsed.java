package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;
import com.lesgirls.network.model.entity.ListDataActivity;

/**
 * Created by victor on 28.07.16.
 */
public class GetActivityResponsed {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ListDataActivity data;


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ListDataActivity getData() {
        return data;
    }
}
