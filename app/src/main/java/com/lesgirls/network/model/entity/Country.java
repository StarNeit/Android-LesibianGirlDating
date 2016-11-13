package com.lesgirls.network.model.entity;

/**
 * Created by victor on 28.06.16.
 */
public class Country {
    public static String ID = "id";
    public static String NAME = "name";
    public static String FIPS_104 = "fips104";
    public static String ISO2 = "iso2";
    public static String ISO3 = "iso3";
    public static String ISON = "ison";
    public static String INTERNET = "internet";
    public static String CAPITAL = "capital";
    public static String MAP_REFERENCE = "map_reference";
    public static String NATIONALY_SINGULAR = "nationality_singular";
    public static String NATIONALY_PLIRAL = "nationality_plural";
    public static String CURRENCY = "currency";
    public static String CURRENCY_CODE = "currency_code";
    public static String POPULATION = "population";
    public static String TITLE = "title";
    public static String COMMENT = "comment";
    public static String SLUNG = "slug";

    private long id;
    private String name;
    private String fips104;
    private String iso2;
    private String iso3;
    private String ison;
    private String internet;
    private String capital;
    private String mapReference;
    private String nationalitySingular;
    private String nationalityPlural;
    private String currency;
    private String currencyCode;
    private String population;
    private String title;
    private String comment;
    private String slug;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFips104() {
        return fips104;
    }

    public void setFips104(String fips104) {
        this.fips104 = fips104;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getIson() {
        return ison;
    }

    public void setIson(String ison) {
        this.ison = ison;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMapReference() {
        return mapReference;
    }

    public void setMapReference(String mapReference) {
        this.mapReference = mapReference;
    }

    public String getNationalitySingular() {
        return nationalitySingular;
    }

    public void setNationalitySingular(String nationalitySingular) {
        this.nationalitySingular = nationalitySingular;
    }

    public String getNationalityPlural() {
        return nationalityPlural;
    }

    public void setNationalityPlural(String nationalityPlural) {
        this.nationalityPlural = nationalityPlural;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
