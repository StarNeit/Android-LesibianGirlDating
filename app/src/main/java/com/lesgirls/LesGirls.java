package com.lesgirls;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.lesgirls.security.Security;

import java.io.IOException;

/**
 * Created by victor on 19.06.16.
 */
public class LesGirls extends Application {
    private static final String TAG = "LesGirls";

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String NAME = "name";
    public static final String AVATAR_URI = "avatar_uri";
    public static final String TOKEN = "token";
    public static final String base_url = "http://50.16.140.39/apis/";

    private static String gcm_token;
    private static String user_token;
    private static long userID;

    private LastPair pair;

    public static String getToken() {
        return user_token;
    }

    public static void seToken(String user_token) {
        LesGirls.user_token = user_token;
    }

    public static long getUserID() {
        return userID;
    }

    public static void setUserID(long userID) {
        LesGirls.userID = userID;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        gcm_token = null;
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastRegistrationReceiver,
                new IntentFilter(LesGirlsGCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastRegistrationReceiver,
                new IntentFilter(LesGirlsGCMRegistrationIntentService.REGISTRATION_ERROR));

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.showErrorNotification(resultCode, this);
            } else {
            }
        }
        else {
            startService(new Intent(this, LesGirlsGCMRegistrationIntentService.class));
        }
    }

    public static String getAndroidID(){
        return Security.md5(Settings.Secure.ANDROID_ID);
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static int getDeviceType() {
        return 2;
    }



    public void setLastPair(String log, String pass){
        pair = new LastPair(log, pass);
    }
    public LastPair getLastPair(){
        return pair;
    }



    public static class LastPair{
        private String lastLoging;
        private String lastPassword;

        public LastPair(String login, String password){
            this.lastLoging = login;
            this.lastPassword = password;
        }

        public String getLastLoging() {
            return lastLoging;
        }

        public void setLastLoging(String lastLoging) {
            this.lastLoging = lastLoging;
        }

        public String getLastPassword() {
            return lastPassword;
        }

        public void setLastPassword(String lastPassword) {
            this.lastPassword = lastPassword;
        }
    }

    public static String getGCMToken(){
        return gcm_token;
    }

    private BroadcastReceiver broadcastRegistrationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(LesGirlsGCMRegistrationIntentService.REGISTRATION_SUCCESS)){
                gcm_token = intent.getStringExtra("token");
                Log.i(TAG, gcm_token);
            } else if(intent.getAction().equals(LesGirlsGCMRegistrationIntentService.REGISTRATION_ERROR)){
                Log.i(TAG, "GCM token error");
            } else {
                Log.i(TAG, "Error");
            }
        }
    };
}
