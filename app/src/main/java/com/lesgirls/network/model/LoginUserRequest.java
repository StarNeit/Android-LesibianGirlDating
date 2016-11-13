package com.lesgirls.network.model;

import com.lesgirls.LesGirls;

/**
 * Created by victor on 28.06.16.
 */
public class LoginUserRequest {
    private String password;
    private String email;
    private String device_id;
    private int device_type;
    private String registration_id;

    public LoginUserRequest(String email, String password){
        this.email = email;
        this.password = password;
        this.device_id = LesGirls.getAndroidID();
        this.device_type = 2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeviceID() {
        return device_id;
    }

    public void setDeviceID(String device_id) {
        this.device_id = device_id;
    }

    public int getDeviceType() {
        return device_type;
    }

    public void setDeviceType(int device_type) {
        this.device_type = device_type;
    }

    public String getRegistrationID() {
        return registration_id;
    }

    public void setRegistrationID(String registration_id) {
        this.registration_id = registration_id;
    }
}
