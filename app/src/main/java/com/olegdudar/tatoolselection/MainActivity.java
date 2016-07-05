package com.olegdudar.tatoolselection;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private CheckBox recordPlayback;
    private CheckBox supportWeb;
    private CheckBox supportMobile;
    private CheckBox supportWebServices;
    private CheckBox supportDesktop;

    private TextView resultCaption;
    private TextView resultValue;

    private Button createDB;
    private Button deleteDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordPlayback = (CheckBox)findViewById(R.id.cb_recordplayback);
        supportDesktop = (CheckBox)findViewById(R.id.cb_desktop);
        supportMobile = (CheckBox)findViewById(R.id.cb_mobile);
        supportWeb = (CheckBox)findViewById(R.id.cb_web);
        supportWebServices = (CheckBox)findViewById(R.id.cb_webservices);

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

        Tool tool = new Tool("Test Complete", "Smart Bear", 1000);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(1);
        tool.setSupportMobile(1);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(1);
        mDBHelper.addTool(tool);

        tool.setName("Appium");
        tool.setVendor("Appium");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        mDBHelper.addTool(tool);

        tool.setName("Selenium WebDriver");
        tool.setVendor("Selenium");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        mDBHelper.addTool(tool);

        tool.setName("Selenium IDE");
        tool.setVendor("Selenium");
        tool.setPrice(0);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        mDBHelper.addTool(tool);

        tool.setName("Ranorex");
        tool.setVendor("Ranorex");
        tool.setPrice(1000);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(1);
        tool.setSupportMobile(1);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(1);
        mDBHelper.addTool(tool);
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
        toolForSearch.setSupportDesktop(supportDesktop.isChecked() ? 1 : 0);
        toolForSearch.setSupportWebServices(supportWebServices.isChecked() ? 1 : 0);
        toolForSearch.setSupportWeb(supportWeb.isChecked() ? 1 : 0);
        toolForSearch.setSupportMobile(supportMobile.isChecked() ? 1 : 0);

        //set labels visible
        resultCaption.setVisibility(View.VISIBLE);
        resultValue.setVisibility(View.VISIBLE);

        StringBuilder tools = new StringBuilder();

        List<Tool> returnedTool = mDBHelper.selectTools(toolForSearch);
        if(returnedTool.size() != 0){
            for (Tool tool:returnedTool
                 ) {
                tools.append(tool.getName() + ", ");
            }
            resultValue.setText(tools.toString().replace(",","\n"));
        }
        else{
            resultValue.setText("Can't find any tool matching your request");
        }



        //mDBHelper.closeDB();
    }

    public void clearDB(View view){
        mDBHelper.recreateTable();
    }

    public void fillDB(View view){
        fillDBData();
    }

}
