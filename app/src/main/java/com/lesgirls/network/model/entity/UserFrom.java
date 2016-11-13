package com.lesgirls.network.model.entity;

import java.util.Date;

/**
 * Created by victor on 29.07.16.
 */
public class UserFrom {
    private long id;
    private String userName;
    private String fullName;
    private int groupID;
    private int type;
    private int countryID;
    private String IPAddress;
    private String password;
    private String email;
    private int emailNotification;
    private boolean confirmation;
    private boolean isActive;
    private boolean hideOnline;
    private boolean hideLocation;
    private int searchDistance;
    private boolean invisible;
    private int sexualType;
    private int lookingFor;
    private Date lastLogin;
    private Date lastActivity;
    private double latitude;
    private double longitude;
    private Date birthOfDate;
    private Date expirationDate;
    private String token;
    private String language;
    private boolean sendConfirmation;
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setType(int type){this.type = type;}
    public int getType(){return this.type;}

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
        return emailNotification;
    }

    public void setEmailNotification(int emailNotification) {
        this.emailNotification = emailNotification;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
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

    public void setLastActivity(Date lastAcyivity) {
        this.lastActivity= lastAcyivity;
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
        return birthOfDate;
    }

    public void setDateOfBirth(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSendConfirmation() {
        return sendConfirmation;
    }

    public void setSendConfirmation(boolean sendConfirmation) {
        this.sendConfirmation = sendConfirmation;
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

    public Date setLast_Login() {
        return last_login;
    }

    public void setLast_Login(Date last_login) {
        this.last_login = last_login;
    }
}
