package com.fowlcam.android.cityregioncountrydatabase.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.fowlcam.android.cityregioncountrydatabase.db.Storable;

import java.util.Locale;

/**
 * Created by Margarita Litkevych on 12/11/14.
 */
public class Region implements Storable {

    //RegionId,CountryId,Region,Code,ADM1Code
    private long regionId;
    private long countryId;
    private String regionName;
    private String regionCode;

    private static final String REGION_ID = "regionId";
    private static final String COUNTRY_ID = "countryId";
    private static final String REGION_NAME = "regionName";
    private static final String REGION_CODE = "regionCode";

    public Region(final long regionId, final long countryId, final String regionName, final String regionCode) {
        this.regionId = regionId;
        this.countryId = countryId;
        this.regionName = regionName;
        this.regionCode = regionCode;
    }

    public Region get(String[] strings) {
        if (strings.length != 5) return new Region();
        int i = 0;
        try {
            regionId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            countryId = Long.parseLong(strings[i++]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        regionName = strings[i++];
        regionCode = strings[i++];
        return this;
    }

    public Region(final long regionId) {
        this.regionId = regionId;
    }

    public Region() {
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(4);
        values.put(REGION_ID, regionId);
        values.put(COUNTRY_ID, countryId);
        values.put(REGION_NAME, regionName);
        values.put(REGION_CODE, regionCode);
        return values;
    }

    @Override
    public long getPrimaryKeyValue() {
        return regionId;
    }

    @Override
    public Storable get(final Cursor cursor) {
        Region region = new Region();
        region.setRegionId(cursor.getLong(cursor.getColumnIndex(REGION_ID)));
        region.setCountryId(cursor.getLong(cursor.getColumnIndex(COUNTRY_ID)));
        region.setRegionName(cursor.getString(cursor.getColumnIndex(REGION_NAME)));
        region.setRegionCode(cursor.getString(cursor.getColumnIndex(REGION_CODE)));
        return region;
    }

    @Override
    public String[] getColNames() {
        return new String[]{REGION_ID, COUNTRY_ID, REGION_NAME, REGION_CODE};
    }

    @Override
    public String getTableName() {
        return "Region";
    }

    @Override
    public String getIdName() {
        return REGION_ID;
    }

    @Override
    public String getCreateQuery() {
        return "create table Region " + getCreateParams();
    }

    @Override
    public String getCreateParams() {
        return String.format(Locale.getDefault(), "(%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s TEXT);", REGION_ID, COUNTRY_ID, REGION_NAME, REGION_CODE);
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(final long regionId) {
        this.regionId = regionId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(final long countryId) {
        this.countryId = countryId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(final String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", countryId=" + countryId +
                ", regionName='" + regionName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                '}';
    }
}
