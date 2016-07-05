package com.olegdudar.tatoolselection;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private CheckBox recordPlayback;

    private TextView resultCaption;
    private TextView resultValue;

    private Button createDB;
    private Button deleteDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordPlayback = (CheckBox)findViewById(R.id.cb_recordplayback);

        resultCaption = (TextView)findViewById(R.id.tv_resultCaption);
        resultValue = (TextView)findViewById(R.id.tv_resultValue);

        createDB = (Button)findViewById(R.id.b_create);
        deleteDB = (Button)findViewById(R.id.b_delete);

        //hide not needed labels
        resultCaption.setVisibility(View.INVISIBLE);
        resultValue.setVisibility(View.INVISIBLE);

        mDBHelper = new DBHelper(getApplicationContext());

        //fillDBData();



//        mDBHelper = new DBHelper(this, "mydatabase.db", null, 1);
//        mSQLiteDatabase = mDBHelper.getWritableDatabase();

//        ContentValues values = new ContentValues();
        //create values for each columns
//        values.put(DBHelper.TOOL_NAME_COLUMN, "TestComplete");
//        values.put(DBHelper.VENDOR_COLUMN, "Smart Bear");
//        values.put(DBHelper.PRICE_COLUMN, 1000.00d);
        //insert values into db
//        mSQLiteDatabase.insert("tools", null, values);

    }

    private void fillDBData() {

        if(mDBHelper == null){
            mDBHelper = new DBHelper(getApplicationContext());
        }

        Tool tool1 = new Tool("Test1", "Word1", 1);
        Tool tool2 = new Tool("Test2", "Word2", 2);

        tool1.setRecordPlayback(1);

        mDBHelper.addTool(tool1);
        mDBHelper.addTool(tool2);
    }

//    public void recreateDB(View view){
//        mDBHelper = new DBHelper(this, "mydatabase.db", null, 1);
//        SQLiteDatabase sdb;
//        sdb = mDBHelper.getReadableDatabase();
//    }

    public void readDataFromDB(View view){
//        Cursor cursor = mSQLiteDatabase.query("tools", new String[]{DBHelper.TOOL_NAME_COLUMN,
//                        DBHelper.VENDOR_COLUMN, DBHelper.PRICE_COLUMN},
//                null, null,
//                null, null, null) ;
//
//        cursor.moveToFirst();
//
//        String toolName = cursor.getString(cursor.getColumnIndex(DBHelper.TOOL_NAME_COLUMN));
//        String toolVendor = cursor.getString(cursor.getColumnIndex(DBHelper.VENDOR_COLUMN));
//        float toolPrice = cursor.getFloat(cursor.getColumnIndex(DBHelper.PRICE_COLUMN));
//
//        TextView infoTextView = (TextView)findViewById(R.id.results);
//        infoTextView.setText("Tool " + toolName + " from " + toolVendor + " with " +
//                toolPrice + " price");
//
//        // не забываем закрывать курсор
//        cursor.close();
    }

    public void search(View view){
        Tool toolForSearch = new Tool();

        toolForSearch.setRecordPlayback(recordPlayback.isChecked() ? 1 : 0);

        //set labels visible
        resultCaption.setVisibility(View.VISIBLE);
        resultValue.setVisibility(View.VISIBLE);

        Tool returnedTool = mDBHelper.selectTool(toolForSearch);
        if(returnedTool != null){
            resultValue.setText(returnedTool.getName());
        }
        else{
            resultValue.setText("Can't find any tool matching your request");
        }



        //mDBHelper.closeDB();
    }

    public void deleteDB(View view){
        mDBHelper.recreateTable();
    }

    public void createDB(View view){
        fillDBData();
    }

}
