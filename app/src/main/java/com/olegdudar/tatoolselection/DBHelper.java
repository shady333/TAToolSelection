package com.olegdudar.tatoolselection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private static final String DATABASE_NAME = "tatools";
    private static final int DATABASE_VERSION = 3;

    private static final String TOOLS_TABLE = "tools";
    private static final String TOOL_NAME_COLUMN = "tool_name";
    private static final String VENDOR_COLUMN = "tool_vendor";
    private static final String PRICE_COLUMN = "tool_price";
    private static final String RECORDPLAYBACK_COLUMN = "tool_record_playback";
    private static final String SUPPORT_WEB_COLUMN = "tool_support_web";
    private static final String SUPPORT_MOBILE_COLUMN = "tool_support_mobile";
    private static final String SUPPORT_DESKTOP_COLUMN = "tool_support_desktop";
    private static final String SUPPORT_WEBSERVICES_COLUMN = "tool_support_webservices";
    private static final String SUPPORT_CI = "tool_support_ci";
    private static final String SUPPORT_DOCUMENTATION = "tool_documentation";
    private static final String SUPPORT_REPORTS = "tool_support_reports";
    private static final String ADDED_DATE = "tool_added_date";
    private static final String TOOL_ID_COLUMN = "tool_id";
    private static final String TOOL_DESCRIPTION_COLUMN = "tool_description";

    private static final String TOOL_SUPPORTED_LANGUAGES = "tool_supported_languages";


    private static final String CREATE_TOOLS_TABLE = "CREATE TABLE "
            + TOOLS_TABLE + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TOOL_NAME_COLUMN + " TEXT NOT NULL, "
            + VENDOR_COLUMN + " TEXT, "

            + TOOL_DESCRIPTION_COLUMN + " TEXT, "

            + RECORDPLAYBACK_COLUMN + " INTEGER, "
            + SUPPORT_WEB_COLUMN + " INTEGER, "
            + SUPPORT_MOBILE_COLUMN + " INTEGER, "
            + SUPPORT_DESKTOP_COLUMN + " INTEGER, "
            + SUPPORT_WEBSERVICES_COLUMN + " INTEGER, "

            + SUPPORT_CI + " INTEGER, "
            + SUPPORT_DOCUMENTATION + " INTEGER, "
            + SUPPORT_REPORTS + " INTEGER, "

            + TOOL_ID_COLUMN + " INTEGER, "

            + TOOL_SUPPORTED_LANGUAGES + " TEXT, "

            + ADDED_DATE + " DATETIME, "

            + PRICE_COLUMN + " DOUBLE);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void recreateTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TOOLS_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TOOLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older table
        db.execSQL("DROP TABLE IF EXISTS " + TOOLS_TABLE);

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
        values.put(ADDED_DATE, getDateTime());
        values.put(VENDOR_COLUMN, tool.getVendor());

        values.put(SUPPORT_CI, tool.getSupportCI());
        values.put(SUPPORT_DOCUMENTATION, tool.getDocumentation());
        values.put(SUPPORT_REPORTS, tool.getReports());

        values.put(TOOL_ID_COLUMN, tool.getId());
        values.put(TOOL_DESCRIPTION_COLUMN, tool.getDescription());

        values.put(TOOL_SUPPORTED_LANGUAGES, TextUtils.join(";", tool.getSupportedLanguages()));



        db.insert(TOOLS_TABLE, null, values);

    }

    public List<Tool> selectTools(Tool tool){

        List<Tool> tools = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TOOLS_TABLE + " WHERE "
//                + PRICE_COLUMN + " = " + tool.getPrice()
//                + " AND "
                + RECORDPLAYBACK_COLUMN + " IN " + (tool.getRecordPlayback() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_DESKTOP_COLUMN + " IN " + (tool.getSupportDesktop() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_MOBILE_COLUMN + " IN " + (tool.getSupportMobile() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_WEB_COLUMN + " IN " + (tool.getSupportWeb() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_WEBSERVICES_COLUMN + " IN " + (tool.getSupportWebServices() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_DOCUMENTATION + " IN " + (tool.getDocumentation() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_REPORTS + " IN " + (tool.getReports() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_CI + " IN " + (tool.getSupportCI() == 1 ? "(1)" : "(0,1)")

                + ";";

//        StringBuilder request = new StringBuilder();
//        request.append("SELECT * FROM " + TOOLS_TABLE + " WHERE ");
//        if(tool.getRecordPlayback() == 1)
//            request.append(

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Tool tempTool;

        if (c != null && c.getCount() > 0) {

            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                do {
                    tempTool = new Tool();

                    tempTool.setName(c.getString(c.getColumnIndex(TOOL_NAME_COLUMN)));
                    tempTool.setPrice(c.getFloat(c.getColumnIndex(PRICE_COLUMN)));

                    tools.add(tempTool);

                } while (c.moveToNext());
            }

            c.close();
        }
        return tools;
    }

    public List<Integer> selectToolIds(Tool tool){

        List<Integer> toolIds = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TOOLS_TABLE + " WHERE "
//                + PRICE_COLUMN + " = " + tool.getPrice()
//                + " AND "
                + RECORDPLAYBACK_COLUMN + " IN " + (tool.getRecordPlayback() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_DESKTOP_COLUMN + " IN " + (tool.getSupportDesktop() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_MOBILE_COLUMN + " IN " + (tool.getSupportMobile() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_WEB_COLUMN + " IN " + (tool.getSupportWeb() == 1 ? "(1)" : "(0,1)")
                + " AND "
                + SUPPORT_WEBSERVICES_COLUMN + " IN " + (tool.getSupportWebServices() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_DOCUMENTATION + " IN " + (tool.getDocumentation() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_REPORTS + " IN " + (tool.getReports() == 1 ? "(1)" : "(0,1)")

                + " AND "
                + SUPPORT_CI + " IN " + (tool.getSupportCI() == 1 ? "(1)" : "(0,1)")

                + ";";

//        StringBuilder request = new StringBuilder();
//        request.append("SELECT * FROM " + TOOLS_TABLE + " WHERE ");
//        if(tool.getRecordPlayback() == 1)
//            request.append(

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Tool tempTool;

        boolean marker = false;

        if (c != null && c.getCount() > 0) {

            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                do {
                    if(tool.getSupportedLanguages() != null){
                        for(String lang : tool.getSupportedLanguages()){
                            if(c.getString(c.getColumnIndex(TOOL_SUPPORTED_LANGUAGES)).contains(lang)){
                                marker = true;
                                continue;
                            }
                        }
                    }
                    else{
                        marker = true;
                    }
                    if(marker)
                        toolIds.add(c.getInt(c.getColumnIndex(TOOL_ID_COLUMN)));
                    marker = false;

                } while (c.moveToNext());
            }

            c.close();
        }
        return toolIds;
    }

    public String getToolNameById(int id){

        String toolName = "VALUE NOT FOUND";

        List<Integer> toolIds = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TOOLS_TABLE + " WHERE "
//                + PRICE_COLUMN + " = " + tool.getPrice()
//                + " AND "
                + TOOL_ID_COLUMN + " IS " + id + ";";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Tool tempTool;

        if (c != null && c.getCount() > 0) {

            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                toolName = c.getString(c.getColumnIndex(TOOL_NAME_COLUMN));
            }

            c.close();
        }
        return toolName;
    }

    public String getToolDescriptionById(int id){

        String toolDescription = "VALUE NOT FOUND";

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TOOLS_TABLE + " WHERE "
//                + PRICE_COLUMN + " = " + tool.getPrice()
//                + " AND "
                + TOOL_ID_COLUMN + " IS " + id + ";";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Tool tempTool;

        if (c != null && c.getCount() > 0) {

            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                toolDescription = c.getString(c.getColumnIndex(TOOL_DESCRIPTION_COLUMN));
            }

            c.close();
        }
        return toolDescription;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void deleteTool(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TOOLS_TABLE, TOOL_NAME_COLUMN + " = ?",
                new String[] { name });
    }
}
