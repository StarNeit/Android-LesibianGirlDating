package com.lesgirls.network.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by victor on 25.06.16.
 */
public class RegistryUserResponseDeserializer implements JsonDeserializer<RegistryUserResponse> {
    @Override
    public RegistryUserResponse deserialize(JsonElement jsonstart, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        boolean b = false;
        RegistryUserResponse result = new RegistryUserResponse();
        JsonElement json = jsonstart.getAsJsonObject().get("User");
        b = (json.getAsJsonObject().get(RegistryUserResponse.ID) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.ID) == null);
        result.setID(b? 0:json.getAsJsonObject().get(RegistryUserResponse.ID).getAsLong());

        b = (json.getAsJsonObject().get(RegistryUserResponse.USERNAME) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.USERNAME) == null);
        result.setUsername(b?"":json.getAsJsonObject().get(RegistryUserResponse.USERNAME).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.FULLNAME) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.FULLNAME) == null);
        result.setFullName(b?"":json.getAsJsonObject().get(RegistryUserResponse.FULLNAME).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.GROUP_ID) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.GROUP_ID) == null);
        result.setGroupID(b?0:json.getAsJsonObject().get(RegistryUserResponse.GROUP_ID).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.TYPE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.TYPE) == null);
        result.setType(b?0:json.getAsJsonObject().get(RegistryUserResponse.TYPE).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.COUNTRY_ID) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.COUNTRY_ID) == null);
        result.setCountryID(b?0:json.getAsJsonObject().get(RegistryUserResponse.COUNTRY_ID).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.IP_ADDRESS) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.IP_ADDRESS) == null);
        result.setIPAddress(b?"":json.getAsJsonObject().get(RegistryUserResponse.IP_ADDRESS).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.EMAIL) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.EMAIL) == null);
        result.setEmail(b?"":json.getAsJsonObject().get(RegistryUserResponse.EMAIL).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.EMAIL_NOTIFICATION) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.EMAIL_NOTIFICATION) == null);
        result.setEmailNotification(b?0:json.getAsJsonObject().get(RegistryUserResponse.EMAIL_NOTIFICATION).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.CONFIRMATION) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.CONFIRMATION) == null);
        result.setConfirmation(b?"":json.getAsJsonObject().get(RegistryUserResponse.CONFIRMATION).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.IS_ACTIVE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.IS_ACTIVE) == null);
        result.setActive(b?false:json.getAsJsonObject().get(RegistryUserResponse.IS_ACTIVE).getAsInt()>0);

        b = (json.getAsJsonObject().get(RegistryUserResponse.HIDE_ONLINE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.HIDE_ONLINE) == null);
        result.setHideOnline(b?false:json.getAsJsonObject().get(RegistryUserResponse.HIDE_ONLINE).getAsInt()>0);

        b = (json.getAsJsonObject().get(RegistryUserResponse.HIDE_LOCATION) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.HIDE_LOCATION) == null);
        result.setHideLocation(b?false:json.getAsJsonObject().get(RegistryUserResponse.HIDE_LOCATION).getAsInt()>0);

        b = (json.getAsJsonObject().get(RegistryUserResponse.SEARCH_DISTANCE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.SEARCH_DISTANCE) == null);
        result.setSearchDistance(b?5:json.getAsJsonObject().get(RegistryUserResponse.SEARCH_DISTANCE).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.INVISIBLE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.INVISIBLE) == null);
        result.setInvisible(b?false:json.getAsJsonObject().get(RegistryUserResponse.INVISIBLE).getAsInt()>0);

        b = (json.getAsJsonObject().get(RegistryUserResponse.SEXUAL_TYPE) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.SEXUAL_TYPE) == null);
        result.setSexualType(b?2:json.getAsJsonObject().get(RegistryUserResponse.SEXUAL_TYPE).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.LOOKING_FOR) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.LOOKING_FOR) == null);
        result.setLookingFor(b?0:json.getAsJsonObject().get(RegistryUserResponse.LOOKING_FOR).getAsInt());

        b = (json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN) instanceof JsonNull) || (json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN) == null);
        result.setLastLogin(b?Calendar.getInstance().getTime():new Date(json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN).getAsString().isEmpty()?"0000-00-00 00:00:00":json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN).getAsString()));


        b = (json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY) == null)?true:((json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString().equals("0000-00-00 00:00:00")));
        result.setLastActivity(b?new Date(0): DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString()));


        b = (json.getAsJsonObject().get("latitude") == null) || (json.getAsJsonObject().get("latitude") instanceof JsonNull) || (json.getAsJsonObject().get("latitude") instanceof JsonNull);
        result.setLocation(b?new LatLng(0,0):new LatLng(json.getAsJsonObject().get("latitude").getAsDouble(),json.getAsJsonObject().get("longitude").getAsDouble()));

        b = (json.getAsJsonObject().get(RegistryUserResponse.DATE_OF_BIRTH) == null)?true:((json.getAsJsonObject().get(RegistryUserResponse.DATE_OF_BIRTH) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.DATE_OF_BIRTH).getAsString().equals("0000-00-00 00:00:00")));
        result.setDateOfBirth(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.DATE_OF_BIRTH).getAsString()));

        b = (json.getAsJsonObject().get(RegistryUserResponse.EXPIRATION_DATE) == null)?true:((json.getAsJsonObject().get(RegistryUserResponse.EXPIRATION_DATE) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.EXPIRATION_DATE).getAsString().equals("0000-00-00 00:00:00")));
        result.setExpirationDate(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.DATE_OF_BIRTH).getAsString()));

        b = (json.getAsJsonObject().get(RegistryUserResponse.TOKEN) == null) || (json.getAsJsonObject().get(RegistryUserResponse.TOKEN) instanceof JsonNull);
        result.setToken(b?"":json.getAsJsonObject().get(RegistryUserResponse.TOKEN).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_ACCESS_TOKEN) == null) || (json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_ACCESS_TOKEN) instanceof JsonNull);
        result.setGoogleAccessToken(b?"":json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_ACCESS_TOKEN).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_REFRESH_TOKEN) == null) || (json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_REFRESH_TOKEN) instanceof JsonNull);
        result.setGoogleRefreshToken(b?"":json.getAsJsonObject().get(RegistryUserResponse.GOOGLE_REFRESH_TOKEN).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.LANGUAGE) == null) || (json.getAsJsonObject().get(RegistryUserResponse.LANGUAGE) instanceof JsonNull);
        result.setLanguage(b?"en":json.getAsJsonObject().get(RegistryUserResponse.LANGUAGE).getAsString());

        b = (json.getAsJsonObject().get(RegistryUserResponse.SEND_CONFIRMATION ) == null) || (json.getAsJsonObject().get(RegistryUserResponse.SEND_CONFIRMATION) instanceof JsonNull);
        result.setSendConfirmation(b?false:json.getAsJsonObject().get(RegistryUserResponse.SEND_CONFIRMATION).getAsInt()>0);

        b = (json.getAsJsonObject().get(RegistryUserResponse.CREATED) == null)?true:((json.getAsJsonObject().get(RegistryUserResponse.CREATED) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.CREATED).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.CREATED).getAsString().equals("0000-00-00 00:00:00")));
        result.setCreated(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.CREATED).getAsString()));

        b = (json.getAsJsonObject().get(RegistryUserResponse.MODIFIED) == null)?true:((json.getAsJsonObject().get(RegistryUserResponse.MODIFIED) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.MODIFIED).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.MODIFIED).getAsString().equals("0000-00-00 00:00:00")));
        result.setModified(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.MODIFIED).getAsString()));

        b = (json.getAsJsonObject().get(RegistryUserResponse.LAST__LOGIN)== null)?true:((json.getAsJsonObject().get(RegistryUserResponse.LAST__LOGIN) instanceof JsonNull)||(json.getAsJsonObject().get(RegistryUserResponse.LAST_ACTIVITY).getAsString().isEmpty())||(json.getAsJsonObject().get(RegistryUserResponse.LAST__LOGIN).getAsString().equals("0000-00-00 00:00:00")));
        result.setLast_login(b?new Date(0):DateOperator.StringToDate(json.getAsJsonObject().get(RegistryUserResponse.LAST__LOGIN).getAsString()));

        JsonElement validate = (json.getAsJsonObject().get(RegistryUserResponse.VALIDATION_ERROR));
        if(!(validate== null)){
            JsonObject ob = validate.getAsJsonObject();
            JsonArray text = ob.get(RegistryUserResponse.PASSWORD).getAsJsonArray();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i<text.size();i++){
                builder.append(text.get(i).getAsString());
            }
            result.setMessageError(builder.toString());
        }
        return result;
    }
}
