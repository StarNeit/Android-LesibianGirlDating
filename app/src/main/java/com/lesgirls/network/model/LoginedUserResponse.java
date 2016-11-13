package com.lesgirls.network.model;

import com.google.android.gms.maps.model.LatLng;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.Country;

import java.util.Date;

/**
 * Created by victor on 28.06.16.
 */
public class LoginedUserResponse {
    private long id;
    private String username;
    private String fullName;
    private int  groupID;
    private int type;
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

    private Country country;

    private Attachment attachment;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
