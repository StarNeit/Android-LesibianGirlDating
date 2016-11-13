package com.lesgirls.network.model.entity;

import java.util.Date;

/**
 * Created by victor on 13.07.16.
 */
public class User {
    private long id;
    private String username;
    private String full_name;
    private int group_id;
    private int type;
    private int country_id;
    private String ip_address;
    private String password;
    private String email;
    private int email_notification;
    private String confirmation;
    private boolean is_active;
    private boolean hide_online;
    private boolean hide_location;
    private int search_distance;
    private boolean invisible;
    private int sexual_type;
    private int looking_for;
    private Date lastlogin;
    private Date last_activity;
    private double  latitude;
    private double longitude;
    private Date date_of_birth;
    private Date expiration_date;
    private String token;
    private String language;
    private boolean send_confirmation;
    private Date created;
    private Date modified;
    private Date last_login;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public int getGroupID() {
        return group_id;
    }

    public void setGroupID(int group_id) {
        this.group_id = group_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCountryID() {
        return country_id;
    }

    public void setCountryID(int country_id) {
        this.country_id = country_id;
    }

    public String getIPAddress() {
        return ip_address;
    }

    public void setIPAddress(String ip_address) {
        this.ip_address = ip_address;
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

    public boolean getActive() {
        return is_active;
    }

    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean getHideOnline() {
        return hide_online;
    }

    public void setHideOnline(boolean hide_online) {
        this.hide_online = hide_online;
    }

    public boolean getHideLocation() {
        return hide_location;
    }

    public void setHideLocation(boolean hide_location) {
        this.hide_location = hide_location;
    }

    public int getSearchDistance() {
        return search_distance;
    }

    public void setSearchDistance(int search_distance) {
        this.search_distance = search_distance;
    }

    public boolean getInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public int getSexualType() {
        return sexual_type;
    }

    public void setSexualType(int sexual_type) {
        this.sexual_type = sexual_type;
    }

    public int getLookingFor() {
        return looking_for;
    }

    public void setLookingFor(int looking_for) {
        this.looking_for = looking_for;
    }

    public Date getLastLogin() {
        return lastlogin;
    }

    public void setLastLogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Date getLastActivity() {
        return last_activity;
    }

    public void setLastActivity(Date last_activity) {
        this.last_activity = last_activity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getExpirationDate() {
        return expiration_date;
    }

    public void setExpirationDate(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getSendConfirmation() {
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

    public Date getLast_Login() {
        return last_login;
    }

    public void setLast_Login(Date last_login) {
        this.last_login = last_login;
    }
}
