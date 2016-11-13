package com.lesgirls.network.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.lesgirls.network.model.entity.Attachment;
import com.lesgirls.network.model.entity.Country;
import com.lesgirls.utils.DateOperator;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by victor on 28.06.16.
 */
public class LogindedUserResponseDeserializer implements JsonDeserializer<LoginedUserResponse> {
    @Override
    public LoginedUserResponse deserialize(JsonElement jsonstart, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        boolean b = false;
        LoginedUserResponse result = new LoginedUserResponse();
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
        result.setLastLogin(b? Calendar.getInstance().getTime():new Date(json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN).getAsString().isEmpty()?"0000-00-00 00:00:00":json.getAsJsonObject().get(RegistryUserResponse.LAST_LOGIN).getAsString()));


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

        Country country = new Country();

        if(json.getAsJsonObject().get("Country") != null){
            JsonElement elCountry = json.getAsJsonObject().get("Country");
            b = (elCountry.getAsJsonObject().get(Country.ID) == null) || (elCountry.getAsJsonObject().get(Country.ID) instanceof JsonNull);
            country.setID(b?0:elCountry.getAsJsonObject().get(Country.ID).getAsLong());

            b = (elCountry.getAsJsonObject().get(Country.NAME) == null) || (elCountry.getAsJsonObject().get(Country.NAME) instanceof JsonNull);
            country.setName(b?"":elCountry.getAsJsonObject().get(Country.NAME).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.FIPS_104) == null) || (elCountry.getAsJsonObject().get(Country.FIPS_104) instanceof JsonNull);
            country.setFips104(b?"":elCountry.getAsJsonObject().get(Country.FIPS_104).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.ISO2) == null) || (elCountry.getAsJsonObject().get(Country.ISO2) instanceof JsonNull);
            country.setIso2(b?"":elCountry.getAsJsonObject().get(Country.ISO2).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.ISO3) == null) || (elCountry.getAsJsonObject().get(Country.ISO3) instanceof JsonNull);
            country.setIso3(b?"":elCountry.getAsJsonObject().get(Country.ISO3).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.ISON) == null) || (elCountry.getAsJsonObject().get(Country.ISON) instanceof JsonNull);
            country.setIson(b?"":elCountry.getAsJsonObject().get(Country.ISON).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.INTERNET) == null) || (elCountry.getAsJsonObject().get(Country.INTERNET) instanceof JsonNull);
            country.setInternet(b?"":elCountry.getAsJsonObject().get(Country.INTERNET).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.CAPITAL) == null) || (elCountry.getAsJsonObject().get(Country.CAPITAL) instanceof JsonNull);
            country.setCapital(b?"":elCountry.getAsJsonObject().get(Country.CAPITAL).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.MAP_REFERENCE) == null) || (elCountry.getAsJsonObject().get(Country.MAP_REFERENCE) instanceof JsonNull);
            country.setMapReference(b?"":elCountry.getAsJsonObject().get(Country.MAP_REFERENCE).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.NATIONALY_SINGULAR) == null) || (elCountry.getAsJsonObject().get(Country.NATIONALY_SINGULAR) instanceof JsonNull);
            country.setNationalitySingular(b?"":elCountry.getAsJsonObject().get(Country.NATIONALY_SINGULAR).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.NATIONALY_PLIRAL) == null) || (elCountry.getAsJsonObject().get(Country.NATIONALY_PLIRAL) instanceof JsonNull);
            country.setNationalityPlural(b?"":elCountry.getAsJsonObject().get(Country.NATIONALY_PLIRAL).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.CURRENCY) == null) || (elCountry.getAsJsonObject().get(Country.CURRENCY) instanceof JsonNull);
            country.setCurrency(b?"":elCountry.getAsJsonObject().get(Country.CURRENCY).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.CURRENCY_CODE) == null) || (elCountry.getAsJsonObject().get(Country.CURRENCY_CODE) instanceof JsonNull);
            country.setCurrencyCode(b?"":elCountry.getAsJsonObject().get(Country.CURRENCY_CODE).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.POPULATION) == null) || (elCountry.getAsJsonObject().get(Country.POPULATION) instanceof JsonNull);
            country.setPopulation(b?"":elCountry.getAsJsonObject().get(Country.POPULATION).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.TITLE) == null) || (elCountry.getAsJsonObject().get(Country.TITLE) instanceof JsonNull);
            country.setTitle(b?"":elCountry.getAsJsonObject().get(Country.TITLE).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.COMMENT) == null) || (elCountry.getAsJsonObject().get(Country.COMMENT) instanceof JsonNull);
            country.setComment(b?"":elCountry.getAsJsonObject().get(Country.COMMENT).getAsString());

            b = (elCountry.getAsJsonObject().get(Country.SLUNG) == null) || (elCountry.getAsJsonObject().get(Country.SLUNG) instanceof JsonNull);
            country.setSlug(b?"":elCountry.getAsJsonObject().get(Country.SLUNG).getAsString());
        }
        result.setCountry(country);
        Attachment attachment = new Attachment();
        if(json.getAsJsonObject().get("Attachment") != null){
            JsonElement elAttachment = json.getAsJsonObject().get("Attachment");
            b = (elAttachment.getAsJsonObject().get(Attachment.ID) == null) || (elAttachment.getAsJsonObject().get(Attachment.ID) instanceof JsonNull);
            attachment.setID(b?0:elAttachment.getAsJsonObject().get(Country.ID).getAsLong());

            b = (elAttachment.getAsJsonObject().get(Attachment.CLASS) == null) || (elAttachment.getAsJsonObject().get(Attachment.CLASS) instanceof JsonNull);
            attachment.setClass(b?"":elAttachment.getAsJsonObject().get(Attachment.CLASS).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.FOREING_ID) == null) || (elAttachment.getAsJsonObject().get(Attachment.FOREING_ID) instanceof JsonNull);
            attachment.setForeignID(b?"":elAttachment.getAsJsonObject().get(Attachment.FOREING_ID).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.FILENAME) == null) || (elAttachment.getAsJsonObject().get(Attachment.FILENAME) instanceof JsonNull);
            attachment.setFilename(b?"":elAttachment.getAsJsonObject().get(Attachment.FILENAME).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.DIR) == null) || (elAttachment.getAsJsonObject().get(Attachment.DIR) instanceof JsonNull);
            attachment.setDir(b?"":elAttachment.getAsJsonObject().get(Attachment.DIR).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.MIMETYPE) == null) || (elAttachment.getAsJsonObject().get(Attachment.MIMETYPE) instanceof JsonNull);
            attachment.setMimetype(b?"":elAttachment.getAsJsonObject().get(Attachment.MIMETYPE).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.TYPE) == null) || (elAttachment.getAsJsonObject().get(Attachment.TYPE) instanceof JsonNull);
            attachment.setType(b?"":elAttachment.getAsJsonObject().get(Attachment.TYPE).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.FILESIZE) == null) || (elAttachment.getAsJsonObject().get(Attachment.FILESIZE) instanceof JsonNull);
            attachment.setFilesize(b?0:elAttachment.getAsJsonObject().get(Attachment.FILESIZE).getAsInt());

            b = (elAttachment.getAsJsonObject().get(Attachment.HEIGHT) == null) || (elAttachment.getAsJsonObject().get(Attachment.HEIGHT) instanceof JsonNull);
            attachment.setHeight(b?0:elAttachment.getAsJsonObject().get(Attachment.HEIGHT).getAsInt());

            b = (elAttachment.getAsJsonObject().get(Attachment.WIDTH) == null) || (elAttachment.getAsJsonObject().get(Attachment.WIDTH) instanceof JsonNull);
            attachment.setWidth(b?0:elAttachment.getAsJsonObject().get(Attachment.WIDTH).getAsInt());

            b = (elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION) == null) || (elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION) instanceof JsonNull);
            attachment.setDescription(b?"":elAttachment.getAsJsonObject().get(Attachment.DESCRIPTION).getAsString());

            b = (elAttachment.getAsJsonObject().get(Attachment.CREATED) == null) || (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) instanceof JsonNull)||(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString().isEmpty())||(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString().equals("0000-00-00 00:00:00"));
            attachment.setCreated(b?new Date(0):DateOperator.StringToDate(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString()));

            b = (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) == null) || (elAttachment.getAsJsonObject().get(Attachment.MODIFIED) instanceof JsonNull)||(elAttachment.getAsJsonObject().get(Attachment.MODIFIED).getAsString().isEmpty())||(elAttachment.getAsJsonObject().get(Attachment.MODIFIED).getAsString().equals("0000-00-00 00:00:00"));
            attachment.setModified(b?new Date(0):DateOperator.StringToDate(elAttachment.getAsJsonObject().get(Attachment.CREATED).getAsString()));
        }
        result.setAttachment(attachment);
        return result;
    }
}
