package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;
import com.lesgirls.network.model.entity.RandomUserList;

/**
 * Created by victor on 13.07.16.
 */
public class Random10Responsed {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private RandomUserList users;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RandomUserList getUsers() {
        return users;
    }

    public void setUsers(RandomUserList users) {
        this.users = users;
    }
}
