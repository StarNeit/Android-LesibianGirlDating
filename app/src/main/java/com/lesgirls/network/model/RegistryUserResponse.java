package com.lesgirls.network.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lesgirls.LesGirls;
import com.lesgirls.R;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class RegistryUserResponse {

    public static final String ID ="id";
    public static final String USERNAME = "username";
    public static final String FULLNAME = "full_name";
    public static final String PASSWORD = "password";
    public static final String GROUP_ID = "group_id";
    public static final String TYPE = "type";
    public static final String COUNTRY_ID = "country_id";
    public static final String IP_ADDRESS = "ip_address";
    public static final String EMAIL = "email";
    public static final String EMAIL_NOTIFICATION = "email_notification";
    public static final String CONFIRMATION = "confirmation";
    public static final String IS_ACTIVE = "is_active";
    public static final String HIDE_ONLINE = "hide_online";
    public static final String HIDE_LOCATION = "hide_location";
    public static final String SEARCH_DISTANCE = "search_distance";
    public static final String INVISIBLE = "invisible";
    public static final String SEXUAL_TYPE = "sexual_type";
    public static final String LOOKING_FOR = "looking_for";
    public static final String LAST_LOGIN = "lastlogin";
    public static final String LAST_ACTIVITY = "last_activity";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String EXPIRATION_DATE = "expiration_date";
    public static final String TOKEN = "token";
    public static final String GOOGLE_ACCESS_TOKEN = "google_access_token";
    public static final String GOOGLE_REFRESH_TOKEN = "google_refresh_token";
    public static final String LANGUAGE = "language";
    public static final String SEND_CONFIRMATION = "send_confirmation";
    public static final String CREATED = "created";
    public static final String MODIFIED = "modified";
    public static final String LAST__LOGIN = "last_login";
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_TYPE = "device_type";
    public static final String VALIDATION_ERROR = "validationErrors";
    public static final String REGISTRATION_ID = "registration_id";

    private long id;
    private String username;
    private String fullName;
    private String password;
    private int groupID;
    private int countryID;
    private String IPAddress;
    private String email;
    private int email_notification;
    private String confirmation;
    private boolean isActive;
    private boolean hideOnline;
    private boolean hideLocation;
    private int searchDistance;
    private boolean invisible;
    private int sexualType;
    private int lookingFor;
    private Date lastLogin;
    private Date lastActivity;
    private int type;
    private LatLng location;
    private Date dateOfBirth;
    private Date expirationDate;
    private String token;
    private String googleAccessToken;
    private String googleRefreshToken;
    private String language;
    private boolean send_confirmation;
    private Date created;
    private Date modified;
    private Date last_login;

    private String messageError;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailNotification() {
        return email_notification;
    }

    public void setEmailNotification(int email_notification) {
        this.email_notification = email_notification;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isHideOnline() {
        return hideOnline;
    }

    public void setHideOnline(boolean hideOnline) {
        this.hideOnline = hideOnline;
    }

    public boolean isHideLocation() {
        return hideLocation;
    }

    public void setHideLocation(boolean hideLocation) {
        this.hideLocation = hideLocation;
    }

    public int getSearchDistance() {
        return searchDistance;
    }

    public void setSearchDistance(int searchDistance) {
        this.searchDistance = searchDistance;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public int getSexualType() {
        return sexualType;
    }

    public void setSexualType(int sexualType) {
        this.sexualType = sexualType;
    }

    public int getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(int lookingFor) {
        this.lookingFor = lookingFor;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public void setGoogleAccessToken(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }

    public String getGoogleRefreshToken() {
        return googleRefreshToken;
    }

    public void setGoogleRefreshToken(String googleRefreshToken) {
        this.googleRefreshToken = googleRefreshToken;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSendConfirmation() {
        return send_confirmation;
    }

    public void setSendConfirmation(boolean send_confirmation) {
        this.send_confirmation = send_confirmation;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public void save(Context context){
        SharedPreferences regData = context.getSharedPreferences(context.getString(R.string.regData), Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = regData.edit();
        ed.putLong(ID,getID());
        ed.putString(USERNAME, getUsername());
        ed.putString(FULLNAME, getFullName());
        ed.putString(PASSWORD, getPassword());
        ed.putInt(GROUP_ID, getGroupID());
        ed.putInt(TYPE, getType());
        ed.putInt(COUNTRY_ID,getCountryID());
        ed.putString(IP_ADDRESS, getIPAddress());
        ed.putString(EMAIL, getEmail());
        ed.putInt(EMAIL_NOTIFICATION,getEmailNotification());
        ed.putString(CONFIRMATION,getConfirmation());
        ed.putBoolean(IS_ACTIVE, isActive());
        ed.putBoolean(HIDE_ONLINE, isHideOnline());
        ed.putBoolean(HIDE_LOCATION, isHideLocation());
        ed.putInt(SEARCH_DISTANCE, getSearchDistance());
        ed.putBoolean(INVISIBLE, isInvisible());
        ed.putInt(SEXUAL_TYPE, getSexualType());
        ed.putInt(LOOKING_FOR,getLookingFor());
        ed.putString(LAST_LOGIN, DateOperator.DateToString(getLastLogin()));
        ed.putString(LAST_ACTIVITY,DateOperator.DateToString(getLastActivity()));
        ed.putString(DATE_OF_BIRTH,DateOperator.DateToString(getDateOfBirth()));
        ed.putString(EXPIRATION_DATE,DateOperator.DateToString(getExpirationDate()));
        ed.putString(TOKEN, getToken());
        ed.putString(GOOGLE_ACCESS_TOKEN, getGoogleAccessToken());
        ed.putString(GOOGLE_REFRESH_TOKEN, getGoogleRefreshToken());
        ed.putString(LANGUAGE, getLanguage());
        ed.putBoolean(SEND_CONFIRMATION, isSendConfirmation());
        ed.putString(CREATED, DateOperator.DateToString(getCreated()));
        ed.putString(MODIFIED, DateOperator.DateToString(getModified()));
        ed.putString(LAST__LOGIN, DateOperator.DateToString(getLast_login()));
        ed.putString(DEVICE_ID, LesGirls.getAndroidID());
        ed.putInt(DEVICE_TYPE, LesGirls.getDeviceType());
        ed.commit();
    }
    public void load(Context context){
        SharedPreferences regData = context.getSharedPreferences(context.getString(R.string.regData), Context.MODE_PRIVATE);
        regData.getLong(ID,0);
        regData.getString(USERNAME, "");
        regData.getString(FULLNAME, "");
        regData.getString(PASSWORD, "");
        regData.getInt(GROUP_ID, 0);
        regData.getInt(TYPE, 0);
        regData.getInt(COUNTRY_ID,0);
        regData.getString(IP_ADDRESS, "127.0.0.1");
        regData.getString(EMAIL, "");
        regData.getInt(EMAIL_NOTIFICATION,0);
        regData.getString(CONFIRMATION,"");
        regData.getBoolean(IS_ACTIVE, false);
        regData.getBoolean(HIDE_ONLINE, false);
        regData.getBoolean(HIDE_LOCATION, true);
        regData.getInt(SEARCH_DISTANCE, 5);
        regData.getBoolean(INVISIBLE, false);
        regData.getInt(SEXUAL_TYPE, 2);
        regData.getInt(LOOKING_FOR,0);
        regData.getString(LAST_LOGIN, DateOperator.DateToString(new Date(0)));
        regData.getString(LAST_ACTIVITY,DateOperator.DateToString(new Date(0)));
        regData.getString(DATE_OF_BIRTH,DateOperator.DateToString(new Date(0)));
        regData.getString(EXPIRATION_DATE,DateOperator.DateToString(new Date(0)));
        regData.getString(TOKEN, "");
        regData.getString(GOOGLE_ACCESS_TOKEN, "");
        regData.getString(GOOGLE_REFRESH_TOKEN, "");
        regData.getString(LANGUAGE, "en");
        regData.getBoolean(SEND_CONFIRMATION, false);
        regData.getString(CREATED, DateOperator.DateToString(Calendar.getInstance().getTime()));
        regData.getString(MODIFIED, DateOperator.DateToString(Calendar.getInstance().getTime()));
        regData.getString(LAST__LOGIN, DateOperator.DateToString(new Date(0)));
        regData.getString(DEVICE_ID, LesGirls.getAndroidID());
        regData.getInt(DEVICE_TYPE, LesGirls.getDeviceType());
    }

}
