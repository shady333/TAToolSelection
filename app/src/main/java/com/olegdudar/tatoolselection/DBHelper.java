package com.olegdudar.tatoolselection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private static final String DATABASE_NAME = "tatools";
    private static final int DATABASE_VERSION = 1;

    private static final String TOOLS_TABLE = "tools";
    private static final String TOOL_NAME_COLUMN = "tool_name";
    private static final String VENDOR_COLUMN = "tool_vendor";
    private static final String PRICE_COLUMN = "tool_price";
    private static final String RECORDPLAYBACK_COLUMN = "tool_record_playback";
    private static final String SUPPORT_WEB_COLUMN = "tool_support_web";
    private static final String SUPPORT_MOBILE_COLUMN = "tool_support_mobile";
    private static final String SUPPORT_DESKTOP_COLUMN = "tool_support_desktop";
    private static final String SUPPORT_WEBSERVICES_COLUMN = "tool_support_webservices";
    private static final String ADDED_DATE = "tool_added_date";

    private static final String CREATE_TOOLS_TABLE = "CREATE TABLE "
            + TOOLS_TABLE + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TOOL_NAME_COLUMN + " TEXT NOT NULL, "
            + VENDOR_COLUMN + " TEXT, "

            + RECORDPLAYBACK_COLUMN + " INTEGER, "
            + SUPPORT_WEB_COLUMN + " INTEGER, "
            + SUPPORT_MOBILE_COLUMN + " INTEGER, "
            + SUPPORT_DESKTOP_COLUMN + " INTEGER, "
            + SUPPORT_WEBSERVICES_COLUMN + " INTEGER, "
            + ADDED_DATE + " DATETIME, "

            + PRICE_COLUMN + " DOUBLE);";

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

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TOOLS_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // write to journal
//        Log.w("SQLite", "Updating from version " + oldVersion + " to version " + newVersion);
//
//        // remove old table and create new one
//        db.execSQL("DROP TABLE IF IT EXISTS " + TOOLS_TABLE);
//        // create new table
//        onCreate(db);
//    }
//
//    public void clearTable(SQLiteDatabase db, String tableName){
//        db.execSQL("DROP TABLE IF EXISTS " + tableName);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOOLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older table
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TOOLS_TABLE);

        // create new tables
        onCreate(db);
    }

    public void addTool(Tool tool){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOOL_NAME_COLUMN, tool.getName());
        values.put(PRICE_COLUMN, tool.getPrice());
        values.put(RECORDPLAYBACK_COLUMN, tool.getRecordPlayback());
        values.put(SUPPORT_DESKTOP_COLUMN, tool.getSupportDesktop());
        values.put(SUPPORT_MOBILE_COLUMN, tool.getSupportMobile());
        values.put(SUPPORT_WEB_COLUMN, tool.getSupportWeb());
        values.put(SUPPORT_WEBSERVICES_COLUMN, tool.getSupportWebServices());
        values.put(VENDOR_COLUMN, tool.getVendor());

        db.insert(TOOLS_TABLE, null, values);

    }

    public Tool selectTool(Tool tool){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TOOLS_TABLE + " WHERE "
                + PRICE_COLUMN + " = " + tool.getPrice() + " AND "
                + RECORDPLAYBACK_COLUMN + " = " + tool.getRecordPlayback() + " AND "
                + SUPPORT_DESKTOP_COLUMN + " = " + tool.getSupportDesktop() + " AND "
                + SUPPORT_MOBILE_COLUMN + " = " + tool.getSupportMobile() + " AND "
                + SUPPORT_WEB_COLUMN + " = " + tool.getSupportWeb() + " AND "
                + SUPPORT_WEBSERVICES_COLUMN + " = " + tool.getSupportWebServices() + ";";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Tool tool1 = new Tool();

        tool1.setName(c.getString(c.getColumnIndex(TOOL_NAME_COLUMN)));
        tool1.setPrice(c.getFloat(c.getColumnIndex(PRICE_COLUMN)));

        return tool1;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
