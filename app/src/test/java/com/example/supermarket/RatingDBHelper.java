package com.example.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RatingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ratings.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_RATINGS = "CREATE TABLE ratings (" +
            "ratingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "liquor REAL, " +
            "meat REAL, " +
            "produce REAL, " +
            "dairy REAL, " +
            "cheese REAL, " +
            "ease REAL," +
            "FOREIGN KEY (ratingID) REFERENCES market(marketID))";

    public RatingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RATINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RatingDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion +
                        ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS ratings");
        onCreate(db);
    }
}
