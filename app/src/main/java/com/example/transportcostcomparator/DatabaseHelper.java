package com.example.transportcostcomparator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TransportCost.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REPORTS = "transport_reports";

    public static final String COL_ID = "id";
    public static final String COL_MODE = "mode";
    public static final String COL_TYPE = "transport_type";
    public static final String COL_DISTANCE = "distance_per_day";
    public static final String COL_COST_PER_KM = "cost_per_km";
    public static final String COL_TRAVEL_DAYS = "travel_days";
    public static final String COL_DAILY_COST = "daily_cost";
    public static final String COL_MONTHLY_COST = "monthly_cost";
    public static final String COL_MONTHLY_DISTANCE = "monthly_distance";
    public static final String COL_CATEGORY = "category";
    public static final String COL_RECOMMENDATION = "recommendation";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =
                "CREATE TABLE " + TABLE_REPORTS + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_MODE + " TEXT, " +
                        COL_TYPE + " TEXT, " +
                        COL_DISTANCE + " REAL, " +
                        COL_COST_PER_KM + " REAL, " +
                        COL_TRAVEL_DAYS + " INTEGER, " +
                        COL_DAILY_COST + " REAL, " +
                        COL_MONTHLY_COST + " REAL, " +
                        COL_MONTHLY_DISTANCE + " REAL, " +
                        COL_CATEGORY + " TEXT, " +
                        COL_RECOMMENDATION + " TEXT" +
                        ")";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {

        db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_REPORTS
        );

        onCreate(db);
    }


    // Save a calculation
    public boolean insertReport(
            String mode,
            String transportType,
            double distance,
            double costPerKm,
            int travelDays,
            double dailyCost,
            double monthlyCost,
            double monthlyDistance,
            String category,
            String recommendation
    ) {

        SQLiteDatabase db =
                this.getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(COL_MODE, mode);
        values.put(COL_TYPE, transportType);
        values.put(COL_DISTANCE, distance);
        values.put(COL_COST_PER_KM, costPerKm);
        values.put(COL_TRAVEL_DAYS, travelDays);
        values.put(COL_DAILY_COST, dailyCost);
        values.put(COL_MONTHLY_COST, monthlyCost);
        values.put(
                COL_MONTHLY_DISTANCE,
                monthlyDistance
        );
        values.put(COL_CATEGORY, category);
        values.put(
                COL_RECOMMENDATION,
                recommendation
        );

        long result =
                db.insert(
                        TABLE_REPORTS,
                        null,
                        values
                );

        return result != -1;
    }


    // Get all saved reports
    public Cursor getAllReports() {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " +
                        TABLE_REPORTS +
                        " ORDER BY " +
                        COL_ID +
                        " DESC",
                null
        );
    }
}