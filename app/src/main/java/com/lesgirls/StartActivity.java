package com.lesgirls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lesgirls.fragments.splash.ForgotFragment;
import com.lesgirls.fragments.splash.LoginFragment;
import com.lesgirls.fragments.splash.SelphieFragment;
import com.lesgirls.fragments.splash.SignUpFragment;
import com.lesgirls.fragments.splash.SplashFragment;
import com.lesgirls.network.AvatarUpdater;
import com.lesgirls.network.AvatarUpdaterResponse;
import com.lesgirls.network.LoginLoader;
import com.lesgirls.network.LoginResponse;
import com.lesgirls.network.RegisterLoader;
import com.lesgirls.network.RegisterResponse;
import com.lesgirls.network.Response;
import com.lesgirls.network.UserContract;
import com.lesgirls.network.model.LoginRequest;
import com.lesgirls.network.model.LoginUserRequest;
import com.lesgirls.network.model.LoginUserRequestSerializer;
import com.lesgirls.network.model.LoginedUserResponse;
import com.lesgirls.network.model.RegistryRequest;
import com.lesgirls.network.model.RegistryUserRequest;
import com.lesgirls.network.model.RegistryUserRequestDeserializer;
import com.lesgirls.network.model.RegistryUserRequestSerializer;
import com.lesgirls.network.model.RegistryUserResponse;
import com.lesgirls.utils.FileOperator;
import com.lesgirls.views.VSwiper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class StartActivity extends BaseActivity implements SplashFragment.OnSplashActionsListener,
        LoginFragment.OnLoginListener,
        SignUpFragment.OnSignUpListener,
        ForgotFragment.OnForgotListener,SelphieFragment.OnSelphie{
    private static final String TAG = "StartActivity";

    private VSwiper vp;
    public int prevFragment;
    private ArrayList<Fragment> arr;
    private PageAdapter adapter;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private ProfileTracker profileTracker;
    private UserContract contract;
    private Uri avatarURI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        vp = (VSwiper) findViewById(R.id.vpSplash);
        arr = new ArrayList<Fragment>();
        arr.add(0,SplashFragment.newInstance());
        adapter = new PageAdapter(getSupportFragmentManager(), arr);
        vp.setAdapter(adapter);
        LoginFragment lf = LoginFragment.newInstance();
        arr.add(1,lf);
        SignUpFragment sf = SignUpFragment.newInstance();
        arr.add(2, sf);
        ForgotFragment ff= ForgotFragment.newInstance();
        arr.add(3, ff);
        adapter.notifyDataSetChanged();
        vp.setCurrentItem(0);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                Log. i(TAG, currentAccessToken.toString());
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };
        callbackManager = CallbackManager.Factory.create();
        prevFragment = 0;

        if(getSettings().getUserID() != -1 && getSettings().getToken() != ""){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed(){
        int currentID = vp.getCurrentItem();
        Fragment currentFragment = arr.get(currentID);
        if(currentFragment instanceof LoginFragment) vp.setCurrentItem(0);
        else if(currentFragment instanceof SignUpFragment) vp.setCurrentItem(prevFragment);
        else if(currentFragment instanceof ForgotFragment) vp.setCurrentItem(1);
        else super.onBackPressed();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ((SignUpFragment) arr.get(2)).setBitmap(imageBitmap);
            //mImageView.setImageBitmap(imageBitmap);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }
    //Splash fragment
    @Override
    public void onLogin() {
        prevFragment = vp.getCurrentItem();
        vp.setCurrentItem(1);
    }
    //Splash fragment
    @Override
    public void onRegistration() {
        prevFragment = vp.getCurrentItem();
        vp.setCurrentItem(2);
    }
    //Login fragment
    @Override
    public void onLogin(String email, String password) {
        prevFragment = vp.getCurrentItem();
        boolean isEmptyEmail = true;
        boolean isEmptyPassword = true;
        if(!email.isEmpty()){;
            isEmptyEmail = false;
        }
        if(!password.isEmpty()){
            isEmptyPassword = false;
        }
        if(!isEmptyEmail && !isEmptyPassword){
            LoginUserRequest logUser = new LoginUserRequest(email,password);
            LoginRequest req = new LoginRequest();
            req.setUserRequest(logUser);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LoginUserRequest.class, new LoginUserRequestSerializer())
                    .create();
            String s = gson.toJson(req);
            Bundle b = new Bundle();
            b.putString("json",s.toString());
            getApp().setLastPair(email, password);
            getLoaderManager().initLoader(R.id.login_loader,b,StartActivity.this);

        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Error ")
                    .setMessage(getString(R.string.login_password_correct))
                    .setCancelable(true)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        }
    }
    //Login fragment
    @Override
    public void onFBLogin() {
        prevFragment = vp.getCurrentItem();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        intent.putExtra(getApp().FIRST_NAME, profile.getFirstName());
                        intent.putExtra(getApp().LAST_NAME, profile.getLastName());
                        intent.putExtra(getApp().MIDDLE_NAME, profile.getMiddleName());
                        intent.putExtra(getApp().NAME, profile.getName());
                        intent.putExtra(getApp().AVATAR_URI, profile.getProfilePictureUri(100,100));
                        intent.putExtra(getApp().TOKEN, accessToken);
                        startActivity(intent);
                        Log.i(TAG, "Success login with Facebook");
                        finish();
                    }
                    @Override
                    public void onCancel() {
                        Log.i(TAG, "Cancel login with Facebook");
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Log.i(TAG, "Error login with Facebook");
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }
    //Login fragment
    @Override
    public void onSignup() {
        prevFragment = vp.getCurrentItem();
        vp.setCurrentItem(2);
    }
    //Login fragment
    @Override
    public void onForgot() {
        prevFragment = vp.getCurrentItem();
        vp.setCurrentItem(3);
    }
    //Signup fragment
    @Override
    public void onSignUp(String email, String password) {
        prevFragment = vp.getCurrentItem();

        RegistryUserRequest reqUser = new RegistryUserRequest(email,password,LesGirls.getAndroidID(), getLocation());
        reqUser.setRegistrationID(LesGirls.getGCMToken());
        RegistryRequest req = new RegistryRequest();
        req.setUserRequest(reqUser);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegistryUserRequest.class, new RegistryUserRequestSerializer())
                .registerTypeAdapter(RegistryUserRequest.class, new RegistryUserRequestDeserializer())
                .create();
        String s = gson.toJson(req);
        Bundle b = new Bundle();
        b.putString("json",s.toString());
        b.putBoolean("isFB", false);
        getApp().setLastPair(email, password);
        getLoaderManager().initLoader(R.id.register_loader,b,StartActivity.this);
    }
    //Signup fragment
    @Override
    public void onFBSignUp() {
        prevFragment = vp.getCurrentItem();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        intent.putExtra(getApp().FIRST_NAME, profile.getFirstName());
                        intent.putExtra(getApp().LAST_NAME, profile.getLastName());
                        intent.putExtra(getApp().MIDDLE_NAME, profile.getMiddleName());
                        intent.putExtra(getApp().NAME, profile.getName());
                        intent.putExtra(getApp().AVATAR_URI, profile.getProfilePictureUri(100,100));
                        intent.putExtra(getApp().TOKEN, accessToken);
                        startActivity(intent);

                        Log.i(TAG, "Success login with Facebook");
                        finish();
                    }
                    @Override
                    public void onCancel() {
                        Log.i(TAG, "Cancel login with Facebook");
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Log.i(TAG, "Error login with Facebook");
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

    }
    //Signup fragment
    @Override
    public void onLogin(boolean checked){
        prevFragment = vp.getCurrentItem();
        vp.setCurrentItem(1);
    }
    //Signup fragment
    @Override
    public void onBack(boolean checked){
        vp.setCurrentItem(prevFragment);
    }
    @Override
    public void onSend(String email) {
    }
    @Override
    public void onBack(int i) {
        vp.setCurrentItem(1);
    }
    //Selphie fragment
    @Override
    public void onTake(Uri uri) {
        for(Fragment f : arr){
            if(f instanceof SelphieFragment){
                arr.remove(f);
                adapter.notifyDataSetChanged();
            }
        }
        avatarURI = uri;
        ((SignUpFragment) arr.get(2)).setBitmap(uri);
        vp.setCurrentItem(2);
    }

    public void takeSelphie(){
        SelphieFragment sf = SelphieFragment.newInstance();
        arr.add(4,sf);
        adapter.notifyDataSetChanged();
        vp.setCurrentItem(4);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id){
            case R.id.register_loader:{
                String s = args.getString("json");
                JsonParser parser = new JsonParser();
                JsonObject obj = (JsonObject) parser.parse(s);
                return new RegisterLoader(this,obj);
            }
            case R.id.login_loader:{
                String s = args.getString("json");
                JsonParser parser = new JsonParser();
                JsonObject obj = (JsonObject) parser.parse(s);
                return new LoginLoader(this,obj);
            }
            case R.id.avatar_updater:{
                long user_id = args.getLong("userID");
                String filename = args.getString("filename");
                return new AvatarUpdater(this, user_id, filename);
            }

            default:return null;
        }
    }
    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        switch (loader.getId()){
            case R.id.register_loader:{
                int status = ((RegisterResponse)data).getStatus();
                if(status==200) {
                    RegistryUserResponse r = ((RegisterResponse) data).getData();
                    r.save(getApp());
                    Bundle b = new Bundle();
                    b.putLong("userID", r.getID());
                    b.putString("filename", FileOperator.getRealPathFromURI(this, avatarURI));
                    getLoaderManager().initLoader(R.id.avatar_updater, b, StartActivity.this);
                    //startActivity(new Intent(getApp(), MainActivity.class));
                    finish();

                    break;
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Error "+String.valueOf(status))
                            .setMessage(((RegisterResponse)data).getMessageError())
                            .setCancelable(true)
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                    loader.reset();
                    break;
                }
            }
            case R.id.avatar_updater:{
                int status = ((AvatarUpdaterResponse)data).getStatus();
                String mess = ((AvatarUpdaterResponse)data).getMessage();
                Log.i(TAG, "Response: status:"+status+", Message:"+mess);
                Intent intent = new Intent(getApp(), MainActivity.class);
                intent.putExtra("token", LesGirls.getToken());
                startActivity(intent);
                finish();
                break;
            }
            case R.id.login_loader:{
                int status = ((LoginResponse)data).getStatus();
                if(status==200) {
                    LoginedUserResponse r = ((LoginResponse) data).getData();
                    Log.i(TAG, r.getEmail()+" "+r.getUsername()+" "+r.getID());
                    String token = ((LoginResponse) data).getToken();
                    //LesGirls.seToken(token);
                    //LesGirls.setUserID(r.getID());
                    Intent intent = new Intent(getApp(), MainActivity.class);
                    //intent.putExtra("token", token);
                    getSettings().setUserID(r.getID());
                    getSettings().setToken(token);
                    getSettings().save();
                    startActivity(intent);
                    finish();
                    break;
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Error "+String.valueOf(status))
                            .setMessage(((LoginResponse)data).getMessageError())
                            .setCancelable(true)
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                    loader.reset();
                    break;
                }
            }


        }
    }
    @Override
    public void onLoaderReset(Loader<Response> loader) {
    }
    public class PageAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> list;
        public PageAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
