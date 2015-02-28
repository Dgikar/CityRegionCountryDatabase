package com.fowlcam.android.cityregioncountrydatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fowlcam.android.cityregioncountrydatabase.model.City;
import com.fowlcam.android.cityregioncountrydatabase.model.Country;
import com.fowlcam.android.cityregioncountrydatabase.model.Region;
import com.fowlcam.android.cityregioncountrydatabase.model.ZipCode;


public final class FowlHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String RUN_DATABASE_NAME = "fowl.db";

    public FowlHelper(final Context context) {
        super(context, RUN_DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        Storable[] storables = getStorables();
        for (Storable storable : storables) {
            final String createQuery = storable.getCreateQuery();
            database.execSQL(createQuery);
        }
    }

    private Storable[] getStorables() {
        return new Storable[]{new City(), new Region(), new Country(), new ZipCode()};
    }

    @Override
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion,
                          final int newVersion) {
        Storable[] storables = getStorables();
        final String deleteQuery = "DROP TABLE IF EXISTS ";
        for (Storable storable : storables) {
            database.execSQL(deleteQuery + storable.getTableName());
        }
        onCreate(database);
    }

}