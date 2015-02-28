package com.fowlcam.android.cityregioncountrydatabase.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.fowlcam.android.cityregioncountrydatabase.db.Storable;

import java.util.Locale;

/**
 * Created by Margarita Litkevych on 12/11/14.
 */
public class ZipCode implements Storable {

    /*
    geonameid         : integer id of record in geonames database
zip              : zip of geographical point (utf8) varchar(200)
asciiname         : zip of geographical point in plain ascii characters, varchar(200)
alternatenames    : alternatenames, comma separated, ascii names automatically transliterated, convenience attribute from alternatename table, varchar(10000)
latitude          : latitude in decimal degrees (wgs84)
longitude         : longitude in decimal degrees (wgs84)
feature class     : see http://www.geonames.org/export/codes.html, char(1)
feature code      : see http://www.geonames.org/export/codes.html, varchar(10)
country code      : ISO-3166 2-letter country code, 2 characters
cc2               : alternate country codes, comma separated, ISO-3166 2-letter country code, 60 characters
admin1 code       : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
admin2 code       : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
admin3 code       : code for third level administrative division, varchar(20)
admin4 code       : code for fourth level administrative division, varchar(20)
population        : bigint (8 byte int)
elevation         : in meters, integer
dem               : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
timezone          : the timezone id (see file timeZone.txt) varchar(40)
modification date : date of last modification in yyyy-MM-dd format
     */


    //CityId,CountryID,RegionID,City,Latitude,Longitude,TimeZone,DmaId,Code
    private long id = -1;
    private String zip;
    private double latitude;
    private double longitude;
    private String countryCode;

    private static final String ID = "id";
    private static final String ZIP = "zip";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String COUNTRY_CODE = "countryCode";



    public ZipCode get(String[] strings) {
        if (strings.length < 11) return new ZipCode();

        countryCode = strings[0];
        zip = strings[1];

        try {
            latitude = Double.parseDouble(strings[9]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            longitude = Double.parseDouble(strings[10]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ZipCode getUsZip(String[] strings) {
        if (strings.length < 11) return new ZipCode();
        zip = strings[0];

        try {
            latitude = Double.parseDouble(strings[9]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            longitude = Double.parseDouble(strings[10]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        countryCode = "US";
        return this;
    }


    public ZipCode() {
    }


    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues(5);
//        values.put(ID, id);
        values.put(ZIP, zip);
        values.put(LATITUDE, latitude);
        values.put(LONGITUDE, longitude);
        values.put(COUNTRY_CODE, countryCode);
        return values;
    }

    @Override
    public long getPrimaryKeyValue() {
        return id;
    }

    @Override
    public Storable get(final Cursor cursor) {
        ZipCode city = new ZipCode();
        city.id = cursor.getLong(cursor.getColumnIndex(ID));
        city.zip = cursor.getString(cursor.getColumnIndex(ZIP));
        city.latitude = (cursor.getLong(cursor.getColumnIndex(LATITUDE)));
        city.longitude = (cursor.getLong(cursor.getColumnIndex(LONGITUDE)));
        city.countryCode = cursor.getString(cursor.getColumnIndex(COUNTRY_CODE));
        return city;
    }

    @Override
    public String[] getColNames() {
        return new String[]{ID, ZIP, LATITUDE, LONGITUDE, COUNTRY_CODE};
    }

    @Override
    public String getTableName() {
        return "ZipCode";
    }

    @Override
    public String getIdName() {
        return ID;
    }

    @Override
    public String getCreateQuery() {
        return "create table ZipCode" + getCreateParams();
    }

    @Override
    public String getCreateParams() {
        return String.format(Locale.getDefault(), "(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT, " +
                "%s INTEGER, " +
                "%s INTEGER," +
                "%s TEXT)", ID, ZIP, LATITUDE, LONGITUDE, COUNTRY_CODE);
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "id=" + id +
                ", zip='" + zip + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
