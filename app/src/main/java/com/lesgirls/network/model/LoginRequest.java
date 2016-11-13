package com.lesgirls.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 28.06.16.
 */
public class LoginRequest {
    @SerializedName("User")
    private LoginUserRequest User;

    public LoginUserRequest getUserRequest() {
        return User;
    }

    public void setUserRequest(LoginUserRequest user) {
        User = user;
    }
}
