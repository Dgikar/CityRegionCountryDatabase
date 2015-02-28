package com.fowlcam.android.cityregioncountrydatabase.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface Storable {

    ContentValues getContentValues();

    long getPrimaryKeyValue();

    Storable get(Cursor cursor);

    String[] getColNames();

    String getTableName();

    String getIdName();

    String getCreateQuery();

    String getCreateParams();

    Storable get(String[] strings);
}
