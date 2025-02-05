package com.example.supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SuperMarketDS {

    private SQLiteDatabase database;
    private SuperMarketDBHelper SuperMarketDBHelper;

    public SuperMarketDS(Context context) {
        SuperMarketDBHelper = new SuperMarketDBHelper(context);
    }
    public void open() {
        database =  SuperMarketDBHelper.getWritableDatabase();
    }
    public void close() {
        SuperMarketDBHelper.close();
    }

    public boolean insertRating(Ratings r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("marketID", r.getMarketID());
            initialValues.put("liquor", r.getLiquor());
            initialValues.put("meat", r.getMeat());
            initialValues.put("produce", r.getProduce());
            initialValues.put("cheese", r.getCheese());
            initialValues.put("ease", r.getEase());

            didSucceed = database.insert("ratings", null, initialValues) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public boolean updateRating(Ratings r) {
        boolean didSucceed = false;
        try {
            long rowId = (long) r.getRatingID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("marketID", r.getMarketID());
            updateValues.put("liquor", r.getLiquor());
            updateValues.put("meat", r.getMeat());
            updateValues.put("produce", r.getProduce());
            updateValues.put("cheese", r.getCheese());
            updateValues.put("ease", r.getEase());

            didSucceed = database.update("ratings", updateValues, "_id=" + rowId, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public int getLastRatingId() {
        int lastId;
        try {
            String query = "Select MAX(ratingID) from ratings";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
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
            String query = "Select MAX(marketID) from market";
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
