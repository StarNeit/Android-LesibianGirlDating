package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 25.06.16.
 */
public class RegistryRequest {
    @SerializedName("User")
    private RegistryUserRequest User;

    public void setUserRequest(RegistryUserRequest user){
        this.User = user;
    }
    public RegistryUserRequest getUserRequest(){return this.User;}
}
