package com.fowlcam.android.cityregioncountrydatabase.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.fowlcam.android.cityregioncountrydatabase.db.Storable;

import java.util.Locale;

/**
 * Created by Margarita Litkevych on 12/11/14.
 */
public class Country implements Storable {
    //    CountryId	Country	FIPS104	ISO2	ISO3	ISON	Internet	Capital	MapReference	NationalitySingular	NationalityPlural	Currency	CurrencyCode	Population	Title	Comment
    private long countryId;
    private String country;
    private String fips;
    private String iso2;
    private String iso3;
    private int ison;
    private String internet;
    private String capital;
    private String mapReference;
    private String nationalitySingular;
    private String nationalityPlural;
    private String currency;
    private String currencyCode;
    private String population;
    private String title;

    private static final String COUNTRY_ID = "countryId";
    private static final String COUNTRY = "country";
    private static final String FIPS = "fips";
    private static final String ISO2 = "iso3";
    private static final String ISO3 = "iso2";
    private static final String ISON = "ison";
    private static final String INTERNET = "internet";
    private static final String CAPITAL = "capital";
    private static final String MAPREFERENCE = "mapReference";
    private static final String NATIONALITY_SINGULAR = "nationalitySingular";
    private static final String NATIONALITY_PLURAL = "nationalityPlural";
    private static final String CURRENCY = "currency";
    private static final String CURRENCY_CODE = "currencyCode";
    private static final String POPULATION = "population";
    private static final String TITLE = "title";

    public Country get(String[] strings) {
        if (strings.length != 16) return new Country();
        int i = 0;
        try {
            countryId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        country = strings[i++];
        fips = strings[i++];
        iso2 = strings[i++];
        iso3 = strings[i++];
        try {
            ison = Integer.parseInt(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        internet = strings[i++];
        capital = strings[i++];
        mapReference = strings[i++];
        nationalitySingular = strings[i++];
        nationalityPlural = strings[i++];
        currency = strings[i++];
        currencyCode = strings[i++];
        population = strings[i++];
        title = strings[i++];
        return this;
    }

    public Country(final long countryId, final String country, final String fips, final String iso2, final String iso3, final int ison, final String internet, final String capital, final String mapReference, final String nationalitySingular, final String nationalityPlural, final String currency, final String currencyCode, final String population, final String title) {
        this.countryId = countryId;
        this.country = country;
        this.fips = fips;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.ison = ison;
        this.internet = internet;
        this.capital = capital;
        this.mapReference = mapReference;
        this.nationalitySingular = nationalitySingular;
        this.nationalityPlural = nationalityPlural;
        this.currency = currency;
        this.currencyCode = currencyCode;
        this.population = population;
        this.title = title;
    }

    public Country(final long countryId) {
        this.countryId = countryId;
    }

    public Country() {
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(14);
        values.put(COUNTRY_ID, countryId);
        values.put(COUNTRY, country);
        values.put(FIPS, fips);
        values.put(ISO2, iso2);
        values.put(ISO3, iso3);
        values.put(ISON, ison);
        values.put(INTERNET, internet);
        values.put(CAPITAL, capital);
        values.put(MAPREFERENCE, mapReference);
        values.put(NATIONALITY_SINGULAR, nationalitySingular);
        values.put(NATIONALITY_PLURAL, nationalityPlural);
        values.put(CURRENCY, currency);
        values.put(CURRENCY_CODE, currencyCode);
        values.put(POPULATION, population);
        values.put(TITLE, title);
        return values;
    }

    @Override
    public long getPrimaryKeyValue() {
        return countryId;
    }

    @Override
    public Storable get(final Cursor cursor) {
        Country country = new Country();
        country.setCountryId(cursor.getLong(cursor.getColumnIndex(COUNTRY_ID)));
        country.setCountry(cursor.getString(cursor.getColumnIndex(COUNTRY)));
        country.setFips(cursor.getString(cursor.getColumnIndex(FIPS)));
        country.setIso2(cursor.getString(cursor.getColumnIndex(ISO2)));
        country.setIso3(cursor.getString(cursor.getColumnIndex(ISO3)));
        country.setIson(cursor.getInt(cursor.getColumnIndex(ISON)));
        country.setInternet(cursor.getString(cursor.getColumnIndex(INTERNET)));
        country.setCapital(cursor.getString(cursor.getColumnIndex(CAPITAL)));
        country.setMapReference(cursor.getString(cursor.getColumnIndex(MAPREFERENCE)));
        country.setNationalitySingular(cursor.getString(cursor.getColumnIndex(NATIONALITY_SINGULAR)));
        country.setNationalityPlural(cursor.getString(cursor.getColumnIndex(NATIONALITY_PLURAL)));
        country.setCurrency(cursor.getString(cursor.getColumnIndex(CURRENCY)));
        country.setCurrencyCode(cursor.getString(cursor.getColumnIndex(CURRENCY_CODE)));
        country.setPopulation(cursor.getString(cursor.getColumnIndex(POPULATION)));
        country.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        return country;
    }

    @Override
    public String[] getColNames() {
        return new String[]{COUNTRY_ID, COUNTRY, FIPS, ISO2, ISO3, ISON, INTERNET, CAPITAL, MAPREFERENCE, NATIONALITY_SINGULAR, NATIONALITY_PLURAL, CURRENCY, CURRENCY_CODE, POPULATION, TITLE};
    }

    @Override
    public String getTableName() {
        return "Country";
    }

    @Override
    public String getIdName() {
        return COUNTRY_ID;
    }

    @Override
    public String getCreateQuery() {
        return "create table Country " + getCreateParams();
    }

    @Override
    public String getCreateParams() {
        return String.format(Locale.getDefault(), "(%s INTEGER PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT,  " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT, " +
                        "%s  TEXT)",
                COUNTRY_ID, COUNTRY, FIPS, ISO2, ISO3, ISON, INTERNET, CAPITAL, MAPREFERENCE, NATIONALITY_SINGULAR, NATIONALITY_PLURAL, CURRENCY, CURRENCY_CODE, POPULATION, TITLE);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(final long countryId) {
        this.countryId = countryId;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(final String fips) {
        this.fips = fips;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(final String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(final String iso3) {
        this.iso3 = iso3;
    }

    public int getIson() {
        return ison;
    }

    public void setIson(final int ison) {
        this.ison = ison;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(final String internet) {
        this.internet = internet;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(final String capital) {
        this.capital = capital;
    }

    public String getMapReference() {
        return mapReference;
    }

    public void setMapReference(final String mapReference) {
        this.mapReference = mapReference;
    }

    public String getNationalitySingular() {
        return nationalitySingular;
    }

    public void setNationalitySingular(final String nationalitySingular) {
        this.nationalitySingular = nationalitySingular;
    }

    public String getNationalityPlural() {
        return nationalityPlural;
    }

    public void setNationalityPlural(final String nationalityPlural) {
        this.nationalityPlural = nationalityPlural;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(final String population) {
        this.population = population;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                "country=" + country +
                ", fips='" + fips + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", iso3='" + iso3 + '\'' +
                ", ison=" + ison +
                ", internet='" + internet + '\'' +
                ", capital='" + capital + '\'' +
                ", mapReference='" + mapReference + '\'' +
                ", nationalitySingular='" + nationalitySingular + '\'' +
                ", nationalityPlural='" + nationalityPlural + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", population='" + population + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
