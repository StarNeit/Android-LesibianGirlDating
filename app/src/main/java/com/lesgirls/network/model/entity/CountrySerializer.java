package com.lesgirls.network.model.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by victor on 17.07.16.
 */
public class CountrySerializer implements JsonSerializer<Country> {
    @Override
    public JsonElement serialize(Country src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(Country.ID, src.getID());
        result.addProperty(Country.NAME, src.getName());
        result.addProperty(Country.FIPS_104, src.getFips104());
        result.addProperty(Country.ISO2, src.getIso2());
        result.addProperty(Country.ISO3, src.getIso3());
        result.addProperty(Country.ISON, src.getIson());
        result.addProperty(Country.INTERNET, src.getInternet());
        result.addProperty(Country.CAPITAL, src.getCapital());
        result.addProperty(Country.MAP_REFERENCE, src.getMapReference());
        result.addProperty(Country.NATIONALY_SINGULAR, src.getNationalitySingular());
        result.addProperty(Country.NATIONALY_PLIRAL, src.getNationalityPlural());
        result.addProperty(Country.CURRENCY, src.getCurrency());
        result.addProperty(Country.CURRENCY_CODE, src.getCurrencyCode());
        result.addProperty(Country.POPULATION, src.getPopulation());
        result.addProperty(Country.TITLE, src.getTitle());
        result.addProperty(Country.COMMENT, src.getComment());
        result.addProperty(Country.SLUNG, src.getSlug());
        return result;
    }
}
