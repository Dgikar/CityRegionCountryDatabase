package com.fowlcam.android.cityregioncountrydatabase.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class DataSource {

    private SQLiteDatabase database;
    private final FowlHelper helper;

    private static DataSource instance;

    private DataSource(@NonNull FowlHelper helper) {
        this.helper = helper;
    }

    public static DataSource getInstance(@NonNull FowlHelper helper) {
        if (instance == null) {
            instance = new DataSource(helper);
        }
        return instance;
    }

    public DataSource open() throws SQLException {
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public Storable getStorableByPrimaryKey(final Storable storable) {
        final String restrict = storable.getIdName() + "=" + storable.getPrimaryKeyValue();
        if (database.isOpen()) {
            final Cursor cursor =
                    database.query(true, storable.getTableName(), storable.getColNames(), restrict,
                            null, null, null, null, null);
            Storable run = null;
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                run = storable.get(cursor);
                cursor.close();
            }
            return run;
        }
        return null;
    }

    /**
     * Non-distinctly queries the database and turns returned cursor into list.
     *
     * @param storable      Storable form to query
     * @param selection     Restriction like "id=1"
     * @param selectionArgs could be left null
     * @param groupBy       could be left null
     * @param having        could be left null
     * @param orderBy       could be left null
     * @param limit         could be left null
     * @return array of storables
     */
    public List<Storable> getStorableListByQuery(final Storable storable, String selection,
                                                 String[] selectionArgs, String groupBy,
                                                 String having, String orderBy, String limit) {
        try {
            final List<Storable> list = new ArrayList<Storable>();
            if (database.isOpen()) {
                final Cursor cursor =
                        database.query(storable.getTableName(), storable.getColNames(), selection,
                                selectionArgs, groupBy, having, orderBy, limit);
                if (!cursor.isClosed()) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        final Storable run = storable.get(cursor);
                        list.add(run);
                        cursor.moveToNext();
                    }
                    cursor.close();
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Storable>();
        }
    }

    public List<Storable> getStorableListByRawQuery(final Storable storable, String query, String[] args) {
        try {
            final List<Storable> list = new ArrayList<Storable>();
            Cursor cursor = database.rawQuery(query, args);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                final Storable run = storable.get(cursor);
                list.add(run);
                cursor.moveToNext();
            }
            cursor.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Storable>();
        }
    }


    public List<Storable> getStorableList(final Storable storable) {
        return getStorableListByQuery(storable, null, null, null, null, null, null);
    }

    public List<Storable> getStorableListOrderByColumn(final Storable storable, String columnName, boolean asc) {
        return getStorableListByQuery(storable, null, null, null, null, columnName + (asc ? " ASC" : " DESC"), null);
    }

    public boolean updateStorable(final Storable item, ContentValues values, String selection, String[] arguments) {
        return database.update(item.getTableName(), values, selection, arguments) > 0;
    }

    public boolean updateStorable(final Storable item) {
        final ContentValues args = item.getContentValues();
        final String restrict = item.getIdName() + "=" + item.getPrimaryKeyValue();
        return database.update(item.getTableName(), args, restrict, null) > 0;
    }

    public int deleteStorable(final Storable storable) {
        final long primaryKeyValue = storable.getPrimaryKeyValue();
        Log.d("DataSource", "Storable deleted with id: " + primaryKeyValue);
        return database.delete(storable.getTableName(),
                storable.getIdName() + " = " + primaryKeyValue, null);
    }

    public int deleteAllStorables(final Storable storable) {
        final long primaryKeyValue = storable.getPrimaryKeyValue();
        Log.d("DataSource", "Storable deleted with id: " + primaryKeyValue);
        try {
            return database.delete(storable.getTableName(), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Storable addStorable(final Storable storing) {
        final ContentValues values = storing.getContentValues();

        try {
            if (database.isOpen()) {
                final long insertId = database.insert(storing.getTableName(), null, values);
                if (insertId != -1) {
                    final Cursor cursor = database.query(storing.getTableName(), storing.getColNames(),
                            storing.getIdName() + " = " + insertId, null, null, null, null);
                    cursor.moveToFirst();
                    final Storable run = storing.get(cursor);
                    cursor.close();
                    return run;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Storable addOrUpdateStorable(final Storable storing) {
        final ContentValues values = storing.getContentValues();

        try {
            if (database.isOpen()) {
                if (getStorableByPrimaryKey(storing) != null) {
                    updateStorable(storing);
                } else {
                    final long insertId = database.insert(storing.getTableName(), null, values);
                    if (insertId != -1) {
                        final Cursor cursor = database.query(storing.getTableName(), storing.getColNames(),
                                storing.getIdName() + " = " + insertId, null, null, null, null);
                        cursor.moveToFirst();
                        final Storable run = storing.get(cursor);
                        cursor.close();
                        return run;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
