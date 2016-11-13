package com.lesgirls.network.model.entity;

import java.util.Date;

/**
 * Created by victor on 28.07.16.
 */
public class AppActivity {
    private long id;
    private long userTo;
    private long userFrom;
    private int activityID;
    private boolean isReady;
    private boolean hideUserToID;
    private boolean hideUserFromID;
    private Date modified;
    private Date created;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isHideUserToID() {
        return hideUserToID;
    }

    public void setHideUserToID(boolean hideUserToID) {
        this.hideUserToID = hideUserToID;
    }

    public boolean isHideUserFromID() {
        return hideUserFromID;
    }

    public void setHideUserFromID(boolean hideUserFromID) {
        this.hideUserFromID = hideUserFromID;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
