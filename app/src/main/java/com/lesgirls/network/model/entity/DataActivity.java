package com.lesgirls.network.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 30.07.16.
 */
public class DataActivity {
    @SerializedName("Activity")
    public AppActivity activity;
    @SerializedName("UserFromId")
    public UserFrom uf;
    public UserTo ut;
}
