package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 28.06.16.
 */
public class LoginedResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("token")
    private String token;
    @SerializedName("data")
    private LoginedUserResponse data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginedUserResponse getData() {
        return data;
    }

    public void setData(LoginedUserResponse data) {
        this.data = data;
    }

    //public String getMessageError(){return data.getMessageError();}
    public String getMessageError(){return "";}

}
