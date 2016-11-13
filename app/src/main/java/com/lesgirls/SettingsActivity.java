package com.lesgirls;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.lesgirls.fragments.settings.MainSettingsFragment;
import com.lesgirls.fragments.settings.MatchSettingsFragment;
import com.lesgirls.fragments.settings.MyPhotoFragment;
import com.lesgirls.fragments.settings.ProfileFragment;
import com.lesgirls.fragments.splash.SignUpFragment;
import com.lesgirls.network.ImageUploader;
import com.lesgirls.network.LoginResponse;
import com.lesgirls.network.MyPhotoDeleteLoader;
import com.lesgirls.network.ProfileLoader;
import com.lesgirls.network.Response;
import com.lesgirls.network.MyPhotoLoader;
import com.lesgirls.network.UpdateProfileLoader;
import com.lesgirls.network.model.LoginedUserResponse;
import com.lesgirls.network.model.ProfileResponse;
import com.lesgirls.network.model.SimpleResponse;
import com.lesgirls.network.model.MyPhotoResponse;
import com.lesgirls.network.model.entity.AppUser;
import com.lesgirls.network.model.entity.MyPhotoList;
import com.lesgirls.utils.DateOperator;
import com.lesgirls.utils.FileOperator;
import com.lesgirls.views.VSPageAdapter;
import com.lesgirls.views.VSwiper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SettingsActivity extends BaseActivity implements MainSettingsFragment.OnMainSettingsListener,
        ProfileFragment.OnProfile, MyPhotoFragment.OnMyPhotoListener, MatchSettingsFragment.OnSettingMatches{
    private static final String TAG = "SettingsActivity";
    public static final int SELECT_PHOTO = 101;
    private VSwiper pager;
    private ArrayList<Fragment> arr;
    private VSPageAdapter adapter;
    private MyPhotoList list;
    private boolean isUploaded = false;
    private boolean updated = false;
    private AppUser user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isUploaded = false;
        updated = false;
        pager = (VSwiper) findViewById(R.id.vpPager);
        arr = new ArrayList<Fragment>();
        arr.add(0,MainSettingsFragment.newInstance());
        arr.add(1, ProfileFragment.newInstance());
        arr.add(2, MyPhotoFragment.newInstance());
        arr.add(3, MatchSettingsFragment.newInstance());
        adapter = new VSPageAdapter(this, getSupportFragmentManager(), arr);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO){
            if(resultCode ==  RESULT_OK){
                Uri selectedImage = data.getData();
                String filePath = FileOperator.getRealPathFromURI(this, selectedImage);
                Bundle b = new Bundle();
                b.putString("fileName", filePath);
                b.putString("token", getSettings().getToken());
                Log.i("ImageUploader", filePath.toString());
                //getLoaderManager().getLoader(R.id.image_uploader).reset();
                if(!isUploaded) {getLoaderManager().initLoader(R.id.image_uploader,b,SettingsActivity.this); isUploaded = true;}
                else getLoaderManager().restartLoader(R.id.image_uploader,b,SettingsActivity.this);
            }
        }
    }

        //MainSettingFragment
    @Override
    public void onProfile() {
        pager.setCurrentItem(1);
    }
    //MainSettingFragment
    @Override
    public void onMyPhoto() {
        getLoaderManager().initLoader(R.id.myphoto_loader, Bundle.EMPTY, this);
    }
    //MainSettingFragment
    @Override
    public void onChangeMyProfilePicture() {

    }
    //MainSettingFragment
    @Override
    public void onTellAFriends() {

    }
    //MainSettingFragment
    @Override
    public void onMatch() {
        pager.setCurrentItem(3);
    }
    //MainSettingFragment
    @Override
    public void onVideo() {

    }
    //MainSettingFragment
    @Override
    public void onAccount() {

    }
    //MainSettingFragment
    @Override
    public void onLogout() {
        getSettings().setUserID(-1);
        getSettings().setToken("");
        getSettings().save();
        startActivity(new Intent(this, StartActivity.class));
        finish();
    }
    //ProfileFragment
    @Override
    public void onProfileName(String profileName) {
        View view = LayoutInflater.from(this).inflate(R.layout.one_edit_text,null);
        final TextInputLayout tl = (TextInputLayout) view.findViewById(R.id.tlProfileName);
        tl.setHint(profileName);
        final EditText ed = (EditText) view.findViewById(R.id.edText);
        //ed.setHint(profileName);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(this.getResources().getString(R.string.profile_name))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sName = ed.getText().toString();
                        if(!sName.isEmpty()) {
                            ProfileFragment fragment = (ProfileFragment) arr.get(1);
                            fragment.setProfileName(sName);
                            dialog.dismiss();

                            updateData(sName, user.getUser().getSexualType(), user.getUser().getLookingFor(), user.getUser().getDateOfBirth());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setView(view);

        builder.show();
    }
    //ProfileFragment
    @Override
    public void onAge(Date date) {
        final Calendar cal = new GregorianCalendar(Locale.getDefault());
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int age = Calendar.getInstance().get(Calendar.YEAR) - year - 1;
                ((ProfileFragment) arr.get(1)).setAge(age);
            }
        }, year, month, day);
        dialog.setTitle(R.string.birth_date);
        Log.i(TAG, "Year:"+year+" Month:"+month+" day:"+day);
        Log.i(TAG, date.toString());
        dialog.updateDate(year, month, day);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface inter, int which) {
                        int year = dialog.getDatePicker().getYear();
                        int month = dialog.getDatePicker().getMonth();
                        int day = dialog.getDatePicker().getDayOfMonth();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        Date birth = calendar.getTime();
                        updateData(user.getUser().getFullName(), user.getUser().getSexualType(), user.getUser().getLookingFor(),birth);
                    }
                });
        dialog.show();
        //updateData(fragment.getFullName(),fragment.getSexualType(),fragment.getLooking(),fragment.getBirthDate());
    }
    //ProfileFragment
    @Override
    public void onIamA(int iType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setItems(R.array.sexual_type, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Selected:"+which);
                        ((ProfileFragment) arr.get(1)).setSexualType(which);
                        updateData(user.getUser().getFullName(), which, user.getUser().getLookingFor(), user.getUser().getDateOfBirth());
                    }
                });
        builder.show();
    }
    //ProfileFragment
    @Override
    public void onLoockingFor(int iLooking) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setItems(R.array.looking, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Selected:"+which);
                        ((ProfileFragment) arr.get(1)).setLooking(which);
                        updateData(user.getUser().getFullName(), user.getUser().getSexualType(), which, user.getUser().getDateOfBirth());
                    }
                });
        builder.show();
    }
    //ProfileFragment
    @Override
    public void onPassword() {
        final String pass = "qwerty";
        View view = LayoutInflater.from(this).inflate(R.layout.three_edit_text,null);
        final TextInputLayout tl = (TextInputLayout) view.findViewById(R.id.tlProfileName);
        final EditText edOldPassword = (EditText) view.findViewById(R.id.edOldPassword);
        final EditText edNewPassword = (EditText) view.findViewById(R.id.edNewPassword);
        final EditText edConfirmPassword = (EditText) view.findViewById(R.id.edConfirmPassword);
        //ed.setHint(profileName);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(this.getResources().getString(R.string.change_password))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sOlsPass = edOldPassword.getText().toString();
                        if(sOlsPass.equals(pass)) {
                            String sNew = edNewPassword.getText().toString();
                            String sConfirm = edConfirmPassword.getText().toString();
                            if(sConfirm.equals(sNew)) {
                                ((ProfileFragment) arr.get(1)).setPassword(sNew);
                            }
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setView(view);

        builder.show();

    }
    //ProfileFragment
    @Override
    public void onViewMyProfile() {

    }
    //ProfileFragment
    @Override
    public void onShareMyProfile() {

    }
    //ProfileFragment
    @Override
    public void onDescription() {

    }
    //My Photo fragment
    @Override
    public void onUpload() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    //Match fragment
    @Override
    public void onMatches() {

    }
    private void updateData(String fullName, int sexType, int iLooking, Date birth) {
        Bundle b = new Bundle();
        if (fullName == null) fullName = "Profile name";
        b.putString("fullname",fullName);
        b.putInt("sextype", sexType);
        b.putInt("looking",iLooking);
        if(birth == null) birth = Calendar.getInstance().getTime();
        b.putString("birth", DateOperator.DateToString(birth));
        if(updated) {getLoaderManager().restartLoader(R.id.updateProfile_loader,b,this);updated = true;}
        getLoaderManager().initLoader(R.id.updateProfile_loader,b,this);
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id){
            case R.id.profile_loader:{
                JsonObject user = new JsonObject();
                user.addProperty("id", getSettings().getUserID());
                JsonObject obj = new JsonObject();
                obj.add("User", user);
                obj.addProperty("token", getSettings().getToken());
                return new ProfileLoader(this, obj);
            }
            case R.id.updateProfile_loader:{
                JsonObject obj = new JsonObject();
                JsonObject user = new JsonObject();
                user.addProperty("sexual_type", args.getInt("sextype"));
                user.addProperty("looking_for",args.getInt("looking"));
                user.addProperty("full_name", args.getString("fullname"));
                user.addProperty("date_of_birth", args.getString("birth"));
                obj.add("User", user);
                obj.addProperty("token", getSettings().getToken());
                return new UpdateProfileLoader(this, obj);
            }
            case R.id.myphoto_loader:{
                JsonObject att = new JsonObject();
                att.addProperty("emptyField", 1);
                JsonObject obj = new JsonObject();
                obj.add("Attachment", att);
                obj.addProperty("token", getSettings().getToken());
                return new MyPhotoLoader(this, obj);
            }
            case R.id.myphotodelete_loader:{
                long l = args.getLong("id");
                JsonObject att = new JsonObject();
                att.addProperty("id", l);
                JsonObject obj = new JsonObject();
                obj.add("Attachment", att);
                obj.addProperty("token", getSettings().getToken());
                return new MyPhotoDeleteLoader(this, obj);
            }
            case R.id.image_uploader:{
                String sToken = args.getString("token");
                String sFileName = args.getString("fileName");
                return new ImageUploader(this, sToken, sFileName);
            }
            default: return null;
        }

    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        switch (loader.getId()){
            case R.id.profile_loader:{
                int status = ((ProfileResponse)data).getStatus();
                if(status==200) {
                    ProfileFragment fragment = (ProfileFragment) arr.get(1);
                    user = ((ProfileResponse)data).getData();
                    fragment.setProfileName(user.getUser().getFullName()==null?"Profile name":user.getUser().getFullName());
                    Date birth = user.getUser().getDateOfBirth();
                    //Date current = Calendar.getInstance().getTime();
                    //int age = current.getYear()-birth.getYear();
                    fragment.setAge(birth);
                    fragment.setSexualType(user.getUser().getSexualType());
                    fragment.setLooking(user.getUser().getLookingFor());
                    break;
                }
            }
            case R.id.updateProfile_loader:{
                int status = ((SimpleResponse)data).getStatus();
                if(status == 200){
                    Bundle b = new Bundle();
                    b.putLong("id", getSettings().getUserID());
                    getLoaderManager().restartLoader(R.id.profile_loader,b,this);
                }
                break;
            }
            case R.id.myphoto_loader:{
                pager.setCurrentItem(2);
                int status = ((MyPhotoResponse)data).getStatus();
                if(status == 200){
                    list = ((MyPhotoResponse)data).getData();
                    ((MyPhotoFragment) arr.get(2)).setData(list);
                }
                break;
            }
            case R.id.myphotodelete_loader:{
                int status = ((SimpleResponse)data).getStatus();
                String sMessage = ((SimpleResponse)data).getMessage();
                if(status == 200){
                    getLoaderManager().restartLoader(R.id.myphoto_loader,Bundle.EMPTY,this);
                }
                break;
            }

            case R.id.image_uploader:{
                getLoaderManager().restartLoader(R.id.myphoto_loader, Bundle.EMPTY, this);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }
}
