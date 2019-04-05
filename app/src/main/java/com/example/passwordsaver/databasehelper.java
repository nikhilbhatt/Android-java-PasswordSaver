package com.example.passwordsaver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.passwordsaver.emailcontract.*;

public class databasehelper extends SQLiteOpenHelper {
    private final static String DATABAE_NAME="database";
    private static final int DATABASE_VERSION=1;

    public databasehelper(Context context) {
        super(context, DATABAE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String ENTRY_TO_TABLE="CREATE TABLE "+
                emailpasswordentry.TABLE_NAME+" (" +
                emailpasswordentry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                emailpasswordentry.COLOUMN_1+ " TEXT NOT NULL, "+
                emailpasswordentry.COLOUMN_2 + " TEXT NOT NULL, "+
                emailpasswordentry.COLOUMN_3 + " TEXT NOT NULL" +
                ");";
          db.execSQL(ENTRY_TO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+emailpasswordentry.TABLE_NAME);
        onCreate(db);
    }
}
