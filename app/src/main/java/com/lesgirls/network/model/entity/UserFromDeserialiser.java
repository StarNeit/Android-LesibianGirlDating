package com.lesgirls.network.model.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by victor on 30.07.16.
 */
public class UserFromDeserialiser implements JsonDeserializer<UserFrom> {
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String FULLNAME= "full_name";
    private static final String GROUP_ID = "group_id";
    private static final String TYPE = "type";
    private static final String COUNTRY_ID = "country_id";
    private static final String IP_ADDRESS = "ip_address";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String EMAIL_NOTIFICATION = "email_notification";
    private static final String CONFIRMATION = "confirmation";
    private static final String IS_ACTIVE = "is_active";
    private static final String HIDE_ONLINE = "hide_online";
    private static final String HIDE_LOCATION = "hide_location";
    private static final String SEARCH_DISTANCE = "search_distance";
    private static final String INVISIBLE = "invisible";
    private static final String SEXUAL_TYPE = "sexual_type";
    private static final String LOOKING_FOR = "looking_for";
    private static final String LAST_LOGIN = "lastlogin";
    private static final String LAST_ACTIVITY = "last_activity";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String EXPIRATION_DATE = "expiration_date";
    private static final String TOKEN = "token";
    private static final String LANGUAGE = "language";
    private static final String SEND_CONFIRMATION = "send_confirmation";
    private static final String CREATED = "created";
    private static final String MODIFIED = "modified";
    private static final String LAST__LOGIN = "last_login";

    @Override
    public UserFrom deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        UserFrom result = new UserFrom();
        boolean b = false;

        b = (json.getAsJsonObject().get(ID) == null) || (json.getAsJsonObject().get(ID) instanceof JsonNull);
        result.setID(b?0:json.getAsJsonObject().get(ID).getAsLong());

        b = (json.getAsJsonObject().get(USERNAME) == null) || (json.getAsJsonObject().get(USERNAME) instanceof JsonNull);
        result.setUserName(b?"":json.getAsJsonObject().get(USERNAME).getAsString());

        b = (json.getAsJsonObject().get(FULLNAME) instanceof JsonNull) || (json.getAsJsonObject().get(FULLNAME) == null);
        result.setFullName(b?"":json.getAsJsonObject().get(FULLNAME).getAsString());

        b = (json.getAsJsonObject().get(GROUP_ID) instanceof JsonNull) || (json.getAsJsonObject().get(GROUP_ID) == null);
        result.setGroupID(b?0:json.getAsJsonObject().get(GROUP_ID).getAsInt());

        b = (json.getAsJsonObject().get(TYPE) instanceof JsonNull) || (json.getAsJsonObject().get(TYPE) == null);
        result.setType(b?0:json.getAsJsonObject().get(TYPE).getAsInt());

        b = (json.getAsJsonObject().get(COUNTRY_ID) instanceof JsonNull) || (json.getAsJsonObject().get(COUNTRY_ID) == null);
        result.setCountryID(b?0:json.getAsJsonObject().get(COUNTRY_ID).getAsInt());

        b = (json.getAsJsonObject().get(IP_ADDRESS) instanceof JsonNull) || (json.getAsJsonObject().get(IP_ADDRESS) == null);
        result.setIPAddress(b?"":json.getAsJsonObject().get(IP_ADDRESS).getAsString());

        b = (json.getAsJsonObject().get("password") instanceof JsonNull) || (json.getAsJsonObject().get("password") == null);
        result.setPassword(b?"":json.getAsJsonObject().get("password").getAsString());

        b = (json.getAsJsonObject().get(EMAIL) instanceof JsonNull) || (json.getAsJsonObject().get(EMAIL) == null);
        result.setEmail(b?"":json.getAsJsonObject().get(EMAIL).getAsString());

        b = (json.getAsJsonObject().get(EMAIL_NOTIFICATION) instanceof JsonNull) || (json.getAsJsonObject().get(EMAIL_NOTIFICATION) == null);
        result.setEmailNotification(b?0:json.getAsJsonObject().get(EMAIL_NOTIFICATION).getAsInt());

        b = (json.getAsJsonObject().get(CONFIRMATION) instanceof JsonNull) || (json.getAsJsonObject().get(CONFIRMATION) == null);
        result.setConfirmation(b?false:json.getAsJsonObject().get(CONFIRMATION).getAsInt()>0);

        b = (json.getAsJsonObject().get(IS_ACTIVE) instanceof JsonNull) || (json.getAsJsonObject().get(IS_ACTIVE) == null);
        result.setActive(b?false:json.getAsJsonObject().get(IS_ACTIVE).getAsInt()>0);

        b = (json.getAsJsonObject().get(HIDE_ONLINE) instanceof JsonNull) || (json.getAsJsonObject().get(HIDE_ONLINE) == null);
        result.setHideOnline(b?false:json.getAsJsonObject().get(HIDE_ONLINE).getAsInt()>0);

        b = (json.getAsJsonObject().get(HIDE_LOCATION) instanceof JsonNull) || (json.getAsJsonObject().get(HIDE_LOCATION) == null);
        result.setHideLocation(b?false:json.getAsJsonObject().get(HIDE_LOCATION).getAsInt()>0);

        b = (json.getAsJsonObject().get(SEARCH_DISTANCE) instanceof JsonNull) || (json.getAsJsonObject().get(SEARCH_DISTANCE) == null);
        result.setSearchDistance(b?5:json.getAsJsonObject().get(SEARCH_DISTANCE).getAsInt());

        b = (json.getAsJsonObject().get(INVISIBLE) instanceof JsonNull) || (json.getAsJsonObject().get(INVISIBLE) == null);
        result.setInvisible(b?false:json.getAsJsonObject().get(INVISIBLE).getAsInt()>0);

        b = (json.getAsJsonObject().get(SEXUAL_TYPE) instanceof JsonNull) || (json.getAsJsonObject().get(SEXUAL_TYPE) == null);
        result.setSexualType(b?2:json.getAsJsonObject().get(SEXUAL_TYPE).getAsInt());

        b = (json.getAsJsonObject().get(LOOKING_FOR) instanceof JsonNull) || (json.getAsJsonObject().get(LOOKING_FOR) == null);
        result.setLookingFor(b?0:json.getAsJsonObject().get(LOOKING_FOR).getAsInt());

        b = (json.getAsJsonObject().get(LAST_LOGIN) == null)||(json.getAsJsonObject().get(LAST_LOGIN) instanceof JsonNull);
        Date res;
        if(b){
            res = Calendar.getInstance().getTime();
        }
        else {
            if(json.getAsJsonObject().get(LAST_LOGIN).getAsString().isEmpty()){
                //res = new Date("1970-01-01 00:00");
                res = DateOperator.StringToDate("1970-01-01 00:00");
            }
            else{
                //res = new Date(json.getAsJsonObject().get(LAST_LOGIN).getAsString());
                res = DateOperator.StringToDate(json.getAsJsonObject().get(LAST_LOGIN).getAsString());
            }
        }


        result.setLastLogin(res);


        b = (json.getAsJsonObject().get(LAST_ACTIVITY) == null)?true:((json.getAsJsonObject().get(LAST_ACTIVITY) instanceof JsonNull)||(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString().equals("0000-00-00 00:00:00")));
        result.setLastActivity(b?new Date(0): DateOperator.StringToDate(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString()));

        b = (json.getAsJsonObject().get("latitude") == null) || (json.getAsJsonObject().get("latitude") instanceof JsonNull) || (json.getAsJsonObject().get("latitude") instanceof JsonNull);
        result.setLatitude(b?0:json.getAsJsonObject().get("latitude").getAsDouble());

        b = (json.getAsJsonObject().get("longitude") == null) || (json.getAsJsonObject().get("longitude") instanceof JsonNull) || (json.getAsJsonObject().get("longitude") instanceof JsonNull);
        result.setLatitude(b?0:json.getAsJsonObject().get("longitude").getAsDouble());

        b = (json.getAsJsonObject().get(DATE_OF_BIRTH) == null)?true:((json.getAsJsonObject().get(DATE_OF_BIRTH) instanceof JsonNull)||(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(DATE_OF_BIRTH).getAsString().equals("0000-00-00 00:00:00")));
        result.setDateOfBirth(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(DATE_OF_BIRTH).getAsString()));

        b = (json.getAsJsonObject().get(EXPIRATION_DATE) == null)?true:((json.getAsJsonObject().get(EXPIRATION_DATE) instanceof JsonNull)||(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(EXPIRATION_DATE).getAsString().equals("0000-00-00 00:00:00")));
        result.setExpirationDate(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(DATE_OF_BIRTH).getAsString()));

        b = (json.getAsJsonObject().get(TOKEN) == null) || (json.getAsJsonObject().get(TOKEN) instanceof JsonNull);
        result.setToken(b?"":json.getAsJsonObject().get(TOKEN).getAsString());

        b = (json.getAsJsonObject().get(LANGUAGE) == null) || (json.getAsJsonObject().get(LANGUAGE) instanceof JsonNull);
        result.setLanguage(b?"en":json.getAsJsonObject().get(LANGUAGE).getAsString());

        b = (json.getAsJsonObject().get(SEND_CONFIRMATION ) == null) || (json.getAsJsonObject().get(SEND_CONFIRMATION) instanceof JsonNull);
        result.setSendConfirmation(b?false:json.getAsJsonObject().get(SEND_CONFIRMATION).getAsInt()>0);

        b = (json.getAsJsonObject().get(CREATED) == null)?true:((json.getAsJsonObject().get(CREATED) instanceof JsonNull)||(json.getAsJsonObject().get(CREATED).getAsString().isEmpty())||(json.getAsJsonObject().get(CREATED).getAsString().equals("0000-00-00 00:00:00")));
        result.setCreated(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(CREATED).getAsString()));

        b = (json.getAsJsonObject().get(MODIFIED) == null)?true:((json.getAsJsonObject().get(MODIFIED) instanceof JsonNull)||(json.getAsJsonObject().get(MODIFIED).getAsString().isEmpty())||(json.getAsJsonObject().get(MODIFIED).getAsString().equals("0000-00-00 00:00:00")));
        result.setModified(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(MODIFIED).getAsString()));

        b = (json.getAsJsonObject().get(LAST__LOGIN)== null)?true:((json.getAsJsonObject().get(LAST__LOGIN) instanceof JsonNull)||(json.getAsJsonObject().get(LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(LAST__LOGIN).getAsString().equals("0000-00-00 00:00:00")));
        result.setLast_Login(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(LAST__LOGIN).getAsString()));

        return result;
    }
}
