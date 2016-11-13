package com.lesgirls.network.model.entity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by victor on 13.07.16.
 */
public class CountryDeserializer implements JsonDeserializer<Country> {
    @Override
    public Country deserialize(JsonElement elCountry, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        boolean b = true;
        Country country = new Country();
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
        return country;
    }
}
