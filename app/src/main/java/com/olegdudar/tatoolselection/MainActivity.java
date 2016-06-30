package com.olegdudar.tatoolselection;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recreateDB(View view){
        mDBHelper = new DBHelper(this, "mydatabase.db", null, 1);
        SQLiteDatabase sdb;
        sdb = mDBHelper.getReadableDatabase();
    }
}
