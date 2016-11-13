package com.lesgirls.network.model.entity;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ObjectConstructor;

/**
 * Created by victor on 14.07.16.
 */
public class AppUser {
    @SerializedName("User")
    private User user;
    @SerializedName("Country")
    private Country country;
    @SerializedName("Attachment")
    private Attachment attachment;
    @SerializedName("UsersHobby")
    private Object usersHobby;
    @SerializedName("Message")
    private Object messages;
    @SerializedName("MessageUnread")
    private Object unbread;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
