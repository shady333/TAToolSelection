package com.olegdudar.tatoolselection;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "tatools.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "tools";
    public static final String TOOL_NAME_COLUMN = "tool_name";
    public static final String VENDOR_COLUMN = "vendor";
    public static final String PRICE_COLUMN = "price";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + TOOL_NAME_COLUMN
            + " text not null, " + VENDOR_COLUMN + " text, " + PRICE_COLUMN
            + " double);";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // write to journal
        Log.w("SQLite", "Updating from version " + oldVersion + " to version " + newVersion);

        // remove old table and create new one
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // create new table
        onCreate(db);
    }
}
