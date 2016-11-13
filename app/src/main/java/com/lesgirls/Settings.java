package com.lesgirls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by victor on 11.07.16.
 */
public class Settings {
    public static final String SETTINGS = "Settings";
    public static final String ID = "id";
    public static final String TOKEN = "token";
    private long userID;
    private String token;
    private Context context;
    private SharedPreferences data;

    public Settings(Context context){
        this.context = context;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void save(){
        SharedPreferences settings = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putLong(ID,getUserID());
        ed.putString(TOKEN, getToken());
        ed.commit();
    }
    public void load(){
        SharedPreferences settings = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        setUserID(settings.getLong(ID,-1));
        setToken(settings.getString(TOKEN,""));
    }
}
