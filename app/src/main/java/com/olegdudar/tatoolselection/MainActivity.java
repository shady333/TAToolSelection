package com.olegdudar.tatoolselection;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DBHelper(this, "mydatabase.db", null, 1);
        mSQLiteDatabase = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        //create values for each columns
        values.put(DBHelper.TOOL_NAME_COLUMN, "TestComplete");
        values.put(DBHelper.VENDOR_COLUMN, "Smart Bear");
        values.put(DBHelper.PRICE_COLUMN, 1000.00d);
        //insert values into db
        mSQLiteDatabase.insert("tools", null, values);

    }

    public void recreateDB(View view){
        mDBHelper = new DBHelper(this, "mydatabase.db", null, 1);
        SQLiteDatabase sdb;
        sdb = mDBHelper.getReadableDatabase();
    }

    public void readDataFromDB(View view){
        Cursor cursor = mSQLiteDatabase.query("tools", new String[]{DBHelper.TOOL_NAME_COLUMN,
                        DBHelper.VENDOR_COLUMN, DBHelper.PRICE_COLUMN},
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String toolName = cursor.getString(cursor.getColumnIndex(DBHelper.TOOL_NAME_COLUMN));
        String toolVendor = cursor.getString(cursor.getColumnIndex(DBHelper.VENDOR_COLUMN));
        float toolPrice = cursor.getFloat(cursor.getColumnIndex(DBHelper.PRICE_COLUMN));

        TextView infoTextView = (TextView)findViewById(R.id.results);
        infoTextView.setText("Tool " + toolName + " from " + toolVendor + " with " +
                toolPrice + " price");

        // не забываем закрывать курсор
        cursor.close();
    }
}
