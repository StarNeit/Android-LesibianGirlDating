package com.lesgirls;

import android.app.Application;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.lesgirls.network.Response;
import com.lesgirls.services.LocationService;

/**
 * Created by victor on 19.06.16.
 */
public abstract class BaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response>{
    private static final String TAG = "BaseActivity";
    private LesGirls app;
    private LatLng location;
    private Settings settings;
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);

        settings = new Settings(this);
        settings.load();

        location = new LatLng(0,0);
        app = (LesGirls) getApplication();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LocationService.BROADCAST_ACTION);
        registerReceiver(locationReceiver, filter);
        startService(new Intent(this, LocationService.class));
    }
    @Override
    public void onDestroy(){
        unregisterReceiver(locationReceiver);
        super.onDestroy();
    }
    public LesGirls getApp() {
        return app;
    }
    public LatLng getLocation(){return location;}
    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null) {
                location = new LatLng(intent.getExtras().getDouble(LocationService.LATITUDE), intent.getExtras().getDouble(LocationService.LONGITUDE));
                Log.i(TAG, ""+getLocation().latitude+","+getLocation().longitude);
            }
        }
    };
    public Settings getSettings(){
        return this.settings;
    }
}
