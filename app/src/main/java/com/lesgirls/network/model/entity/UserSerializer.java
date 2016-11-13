package com.lesgirls.network.model.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;

/**
 * Created by victor on 17.07.16.
 */
public class UserSerializer implements JsonSerializer<User> {
    public static final String ID ="id";
    public static final String USERNAME = "username";
    public static final String FULLNAME = "full_name";
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
    public static final String LANGUAGE = "language";
    public static final String SEND_CONFIRMATION = "send_confirmation";
    public static final String CREATED = "created";
    public static final String MODIFIED = "modified";
    public static final String LAST__LOGIN = "last_login";

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(ID, src.getID());
        result.addProperty(USERNAME, src.getUserName());
        result.addProperty(FULLNAME, src.getFullName());
        result.addProperty(GROUP_ID, src.getGroupID());
        result.addProperty(TYPE, src.getType());
        result.addProperty(COUNTRY_ID, src.getCountryID());
        result.addProperty(IP_ADDRESS, src.getIPAddress());
        result.addProperty(EMAIL, src.getEmail());
        result.addProperty(EMAIL_NOTIFICATION, src.getEmailNotification());
        result.addProperty(CONFIRMATION, src.getConfirmation());
        result.addProperty(IS_ACTIVE, src.getActive()?0:1);
        result.addProperty(HIDE_ONLINE, src.getHideOnline()?0:1);
        result.addProperty(HIDE_LOCATION, src.getHideLocation()?0:1);
        result.addProperty(SEARCH_DISTANCE, src.getSearchDistance());
        result.addProperty(INVISIBLE, src.getInvisible()?0:1);
        result.addProperty(SEXUAL_TYPE, src.getSexualType());
        result.addProperty(LOOKING_FOR, src.getLookingFor());
        result.addProperty(LAST_LOGIN, DateOperator.DateToString(src.getLastLogin()));
        result.addProperty(LAST_ACTIVITY, DateOperator.DateToString(src.getLastActivity()));
        result.addProperty(DATE_OF_BIRTH, DateOperator.DateToString(src.getDateOfBirth()));
        result.addProperty(EXPIRATION_DATE, DateOperator.DateToString(src.getExpirationDate()));
        result.addProperty(TOKEN, src.getToken());
        result.addProperty(LANGUAGE, src.getLanguage());
        result.addProperty(SEND_CONFIRMATION, src.getSendConfirmation()?0:1);
        result.addProperty(CREATED, DateOperator.DateToString(src.getCreated()));
        result.addProperty(MODIFIED, DateOperator.DateToString(src.getModified()));
        result.addProperty(LAST__LOGIN, DateOperator.DateToString(src.getLast_Login()));
        return result;
    }
}
