package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 25.06.16.
 */
public class RegistryResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private RegistryUserResponse data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RegistryUserResponse getData() {
        return data;
    }

    public void setData(RegistryUserResponse data) {
        this.data = data;
    }

    public String getMessageError(){return data.getMessageError();}
}
