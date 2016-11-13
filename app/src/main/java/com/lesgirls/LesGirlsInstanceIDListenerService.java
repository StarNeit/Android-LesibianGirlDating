package com.lesgirls;

import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

public class LesGirlsInstanceIDListenerService extends InstanceIDListenerService {
    private String TAG = "LesGirlsInstanceIDListenerService";

    public LesGirlsInstanceIDListenerService() {
    }
    @Override
    public void onTokenRefresh() {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(this.getResources().getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.d(TAG, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
