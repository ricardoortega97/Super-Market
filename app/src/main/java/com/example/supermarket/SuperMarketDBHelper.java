package com.example.supermarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SuperMarketDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "supermarket.db";
    private static final int DATABASE_VERSION = 1; // Incremented version for schema changes

    private static final String CREATE_TABLE_MARKET = "CREATE TABLE market (" +
            "marketID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "address TEXT, " +
            "city TEXT, " +
            "state TEXT, " +
            "zip TEXT)";

    private static final String CREATE_TABLE_RATINGS = "CREATE TABLE ratings (" +
            "ratingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "marketID INTEGER, " + // Reference to market table
            "liquor REAL, " +
            "meat REAL, " +
            "produce REAL, " +
            "cheese REAL, " +
            "ease REAL, " +
            "FOREIGN KEY (marketID) REFERENCES market(marketID))";

    public SuperMarketDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARKET);
        db.execSQL(CREATE_TABLE_RATINGS);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SuperMarketDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion +
                        ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS ratings");
        db.execSQL("DROP TABLE IF EXISTS market");
        onCreate(db);
    }

}
