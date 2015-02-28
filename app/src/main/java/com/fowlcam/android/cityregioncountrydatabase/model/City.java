package com.fowlcam.android.cityregioncountrydatabase.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.fowlcam.android.cityregioncountrydatabase.db.Storable;

import java.util.Locale;

/**
 * Created by Margarita Litkevych on 12/11/14.
 */
public class City implements Storable {

    //CityId,CountryID,RegionID,City,Latitude,Longitude,TimeZone,DmaId,Code
    private long cityId;
    private long countryId;
    private long regionId;
    private String cityName;
    private double latitude;
    private double longitude;
    private String timeZone;
    private String code;

    private static final String CITY_ID="cityId";
    private static final String COUNTRY_ID="countryId";
    private static final String REGION_ID="regionId";
    private static final String CITY_NAME="cityName";
    private static final String LATITUDE="latitude";
    private static final String LONGITUDE="longitude";
    private static final String TIMEZONE="timezone";
    private static final String CODE="code";

    public City(final long cityId, final long countryId, final long regionId, final String cityName, final double latitude, final double longitude, final String timeZone, final String code) {
        this.cityId = cityId;
        this.countryId = countryId;
        this.regionId = regionId;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = timeZone;
        this.code = code;
    }

    public City get(String[] strings) {
        if (strings.length != 9) return new City();
        int i = 0;
        try {
            cityId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            countryId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            regionId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        cityName = strings[i++];
        try {
            latitude = Double.parseDouble(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            longitude = Double.parseDouble(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        timeZone = strings[i++];
        code = strings[i++];
        return this;
    }

    public City() {}

    public City(final long cityId) {
        this.cityId = cityId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(final long cityId) {
        this.cityId = cityId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(final long countryId) {
        this.countryId = countryId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(final long regionId) {
        this.regionId = regionId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(8);
        values.put(CITY_ID, cityId);
        values.put(COUNTRY_ID, countryId);
        values.put(REGION_ID, regionId);
        values.put(CITY_NAME, cityName);
        values.put(LATITUDE, latitude);
        values.put(LONGITUDE, longitude);
        values.put(TIMEZONE, timeZone);
        values.put(CODE, code);
        return values;
    }

    @Override
    public long getPrimaryKeyValue() {
        return cityId;
    }

    @Override
    public Storable get(final Cursor cursor) {
        City city = new City();
        city.setCityId(cursor.getLong(cursor.getColumnIndex(CITY_ID)));
        city.setCountryId(cursor.getLong(cursor.getColumnIndex(REGION_ID)));
        city.setRegionId(cursor.getLong(cursor.getColumnIndex(COUNTRY_ID)));
        city.setCityName(cursor.getString(cursor.getColumnIndex(CITY_NAME)));
        city.setLatitude(cursor.getLong(cursor.getColumnIndex(LATITUDE)));
        city.setLongitude(cursor.getLong(cursor.getColumnIndex(LONGITUDE)));
        city.setTimeZone(cursor.getString(cursor.getColumnIndex(TIMEZONE)));
        city.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
        return city;
    }

    @Override
    public String[] getColNames() {
        return new String[]{CITY_ID, REGION_ID, COUNTRY_ID, CITY_NAME, LATITUDE, LONGITUDE, TIMEZONE, CODE};
    }

    @Override
    public String getTableName() {
        return "City";
    }

    @Override
    public String getIdName() {
        return CITY_ID;
    }

    @Override
    public String getCreateQuery() {
        return   "create table City" + getCreateParams();
    }

    @Override
    public String getCreateParams() {
        return String.format(Locale.getDefault(), "(%s INTEGER PRIMARY KEY, " +
                "%s INTEGER, " +
                "%s INTEGER, " +
                "%s TEXT, " +
                "%s INTEGER, " +
                "%s INTEGER," +
                "%s TEXT, " +
                "%s TEXT)", CITY_ID, REGION_ID, COUNTRY_ID, CITY_NAME, LATITUDE, LONGITUDE, TIMEZONE, CODE);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", countryId=" + countryId +
                ", regionId=" + regionId +
                ", cityName='" + cityName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timeZone='" + timeZone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
