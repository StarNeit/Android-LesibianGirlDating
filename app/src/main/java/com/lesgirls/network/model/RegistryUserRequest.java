package com.lesgirls.network.model;

import com.google.android.gms.maps.model.LatLng;

public class RegistryUserRequest {
    private String username;
    private String password;
    private String email;
    private String device_id;
    private int device_type;
    private LatLng location;
    private String registration_id;

    public RegistryUserRequest(){
        this("","","",new LatLng(0,0));
    }

    public RegistryUserRequest(String email, String password, String androidID, LatLng location){
        this.username = email;
        this.password = password;
        this.email = email;
        this.device_id = androidID;
        this.device_type = 2;
        this.location = location;
    }
    public void setUsername(String username){this.username = username;}
    public String getUsername(){return this.username;}
    public void setPassword(String password){this.password = password;}
    public String getPassword(){return this.password;}
    public void setEmail(String email){this.email =email;}
    public String getEmail(){return this.email;}
    public void setDeviceID(String deviceID){this.device_id = deviceID;}
    public String getDeviceID(){return this.device_id;}
    public void setDeviceType(int deviceType){this.device_type = deviceType;}
    public int getDeviceType(){return this.device_type;}
    public void setLocation(LatLng location){this.location = location;}
    public LatLng getLocation(){return this.location;}
    public String getRegistrationID() {return registration_id;}
    public void setRegistrationID(String registration_id) {this.registration_id = registration_id;}
}
