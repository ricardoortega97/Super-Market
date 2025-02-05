package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MarketDS {

    private SQLiteDatabase database;
    private MarketDBHelper marketDBHelper;

    public MarketDS(Context context) {
        marketDBHelper = new MarketDBHelper(context);
    }
    public void open() {
        database = marketDBHelper.getWritableDatabase();
    }
    public void close() {
        marketDBHelper.close();
    }

    public boolean insertMarket(Market m) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("name", m.getName());
            initialValues.put("address", m.getAddress());
            initialValues.put("city", m.getCity());
            initialValues.put("state", m.getState());
            initialValues.put("zip", m.getZip());

            didSucceed = database.insert("market", null, initialValues) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }
    public boolean updateMarket(Market m) {
        boolean didSucceed = false;
        try {
            Long rowId = Long.valueOf(m.getMarketID());
            ContentValues updateValues = new ContentValues();

            updateValues.put("name", m.getName());
            updateValues.put("address", m.getAddress());
            updateValues.put("city", m.getCity());
            updateValues.put("state", m.getState());
            updateValues.put("zip", m.getZip());

            didSucceed = database.update("market", updateValues, "_id=" + rowId, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public int getLastMarketId() {
        int lastId;
        try {
            String query = "Select MAX(_id) from market";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }
}
