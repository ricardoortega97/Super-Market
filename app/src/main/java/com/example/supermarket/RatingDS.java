package com.example.supermarket;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.media.Rating;

public class RatingDS {

    private SQLiteDatabase database;
    private RatingDBHelper ratingDBHelper;

    public RatingDS(Context context) {
       ratingDBHelper = new RatingDBHelper(context);
    }
    public void open() {
        database =  ratingDBHelper.getWritableDatabase();
    }
    public void close() {
        ratingDBHelper.close();
    }

    public boolean insertRating(Ratings r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();


            initialValues.put("liquor", r.getLiquor());
            initialValues.put("meat", r.getMeat());
            initialValues.put("produce", r.getProduce());
            initialValues.put("dairy", r.getDairy());
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

            updateValues.put("liquor", r.getLiquor());
            updateValues.put("meat", r.getMeat());
            updateValues.put("produce", r.getProduce());
            updateValues.put("dairy", r.getDairy());
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
}
