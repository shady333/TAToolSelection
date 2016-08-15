package com.olegdudar.tatoolselection;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    TableLayout itemsLayout;

    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        itemsLayout = (TableLayout)findViewById(R.id.itemsLayout);

        Intent intent = getIntent();

        ArrayList<Integer> returnedTool = intent.getExtras().getIntegerArrayList("results");

        mDBHelper = new DBHelper(getApplicationContext());

        String name = "";
        String description = "";

        if(returnedTool.size() != 0){
            for(int a : returnedTool){
                name = mDBHelper.getToolNameById(a);
                description = mDBHelper.getToolDescriptionById(a);
                addCaption(name);
                addDescription(description, a);
            }
        }
    }

    public void add_Item(View view){

        //addCaption("CAPTION");
        //addDescription("asdlasdlkas dkAKJDASS JDSAFKJ K FGKJFD GHKAJH LKDFH DLDLA LALJDSHF  FSD K 874398573498573498 E JFJDJKF HDSJKF ", 1);
    }

    private void addDescription(String text, int imageId) {
        TableRow row = new TableRow(this);

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        int leftMargin=0;
        int topMargin=2;
        int rightMargin=0;
        int bottomMargin=8;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        row.setLayoutParams(tableRowParams);

        LinearLayout linear = new LinearLayout(this);
        linear.setLayoutParams(new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linear.setOrientation(LinearLayout.HORIZONTAL);

        ImageView image = new ImageView(this);
        image.setImageDrawable(getImageById(imageId));

        TextView textView = new TextView(this);
        textView.setText(text);

        linear.addView(image);
        linear.addView(textView);

        row.addView(linear);

        itemsLayout.addView(row);
    }

    private void addCaption(String text) {
        TableRow row = new TableRow(this);

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        int leftMargin=0;
        int topMargin=2;
        int rightMargin=0;
        int bottomMargin=8;

        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        row.setLayoutParams(tableRowParams);

        TextView textView = new TextView(this);
        textView.setText(text);

        row.addView(textView);

        itemsLayout.addView(row);
    }

    private Drawable getImageById(int id){
        switch (id){
            case 1: return getResources().getDrawable(R.drawable.testcomplete);
            case 2: return getResources().getDrawable(R.drawable.qtp);
            case 3: return getResources().getDrawable(R.drawable.jmeter);
            case 4: return getResources().getDrawable(R.drawable.seleniumrc);
            case 5: return getResources().getDrawable(R.drawable.seleniumwebdriver);
            case 6: return getResources().getDrawable(R.drawable.autoit);
            case 7: return getResources().getDrawable(R.drawable.soapui);
            case 8: return getResources().getDrawable(R.drawable.codedui);
            case 9: return getResources().getDrawable(R.drawable.robotium);
            case 10: return getResources().getDrawable(R.drawable.uiautomator);
            case 11: return getResources().getDrawable(R.drawable.espresso);
            case 12: return getResources().getDrawable(R.drawable.appium);
            case 13: return getResources().getDrawable(R.drawable.calabash);
            default:
                break;
        }
        return null;
    }
}
