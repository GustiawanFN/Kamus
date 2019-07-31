package com.gustiawandicoding.submissionkamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static android.provider.BaseColumns._ID;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.KamusColumns.DESCRIPTION;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.KamusColumns.WORD;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.TABLE_ENG;
import static com.gustiawandicoding.submissionkamus.db.DatabaseHelper.DatabaseContract.TABLE_INA;

/**
 * Created by Gustiawan on 9/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static class DatabaseContract {

        static String TABLE_INA = "table_ina";
        static String TABLE_ENG = "table_eng";

        static final class KamusColumns implements BaseColumns {
            static String WORD = "word";
            static String DESCRIPTION = "description";
        }

    }

    private static String DATABASE_NAME = "dbkamus";
    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_ENGLISH = "create table " + TABLE_ENG +
            " (" + _ID + " integer primary key autoincrement, "
            + WORD + " text not null, "
            + DESCRIPTION + " text not null);";

    private static String CREATE_TABLE_INDONESIA = "create table " + TABLE_INA +
            " (" + _ID + " integer primary key autoincrement, "
            + WORD + " text not null, "
            + DESCRIPTION + " text not null);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        db.execSQL(CREATE_TABLE_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_ENG);
        db.execSQL("DROP TABLE IF EXIST "+TABLE_INA);
        onCreate(db);

    }
}