package com.olegdudar.tatoolselection;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
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

    private CheckBox supportDocumentation;
    private CheckBox supportCI;
    private CheckBox supportReports;

    private TextView resultCaption;
    private TextView resultValue;

    private Button createDB;
    private Button deleteDB;

    TableLayout resultsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordPlayback = (CheckBox)findViewById(R.id.cb_recordplayback);
        supportDesktop = (CheckBox)findViewById(R.id.cb_desktop);
        supportMobile = (CheckBox)findViewById(R.id.cb_mobile);
        supportWeb = (CheckBox)findViewById(R.id.cb_web);
        supportWebServices = (CheckBox)findViewById(R.id.cb_webservices);

        supportDocumentation = (CheckBox)findViewById(R.id.cb_documentation);
        supportCI = (CheckBox)findViewById(R.id.cb_cisupport);
        supportReports = (CheckBox)findViewById(R.id.cb_reports);

        resultCaption = (TextView)findViewById(R.id.tv_resultCaption);
        resultValue = (TextView)findViewById(R.id.tv_resultValue);

        createDB = (Button)findViewById(R.id.b_create);
        deleteDB = (Button)findViewById(R.id.b_delete);

        //hide not needed labels
        resultCaption.setVisibility(View.INVISIBLE);
        resultValue.setVisibility(View.INVISIBLE);

        resultsLayout = (TableLayout)findViewById(R.id.resulTableLayout);

        mDBHelper = new DBHelper(getApplicationContext());



    }

    public void test(View view){
        Button btnNew = new Button(this);
        btnNew.setText("TEST BUTTON");
        resultsLayout.addView(btnNew);

        ImageView image = new ImageView(this);
        image.setImageDrawable(getResources().getDrawable(R.drawable.appium));
        resultsLayout.addView(image);
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
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("QTP");
        tool.setVendor("HP");
        tool.setPrice(1000);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("Jmeter");
        tool.setVendor("Apache");
        tool.setPrice(0);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(1);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);


        tool.setName("Selenium -RC");
        tool.setVendor("Selenium");
        tool.setPrice(0);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);

        tool.setName("Selenium WebDriver");
        tool.setVendor("Selenium");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);

        tool.setName("AutoIT");
        tool.setVendor("AutoIT");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(1);
        tool.setSupportMobile(0);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);

        tool.setName("SoapUI");
        tool.setVendor("SmartBear");
        tool.setPrice(0);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(0);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(1);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("Coded UI");
        tool.setVendor("Microsoft");
        tool.setPrice(1000);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(1);
        tool.setSupportMobile(0);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("Robotium");
        tool.setVendor("Robotium");
        tool.setPrice(0);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);

        tool.setName("UIAutomator");
        tool.setVendor("UIAutomator");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("Espresso");
        tool.setVendor("Espresso");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);

        tool.setName("Appium");
        tool.setVendor("Appium");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        mDBHelper.addTool(tool);

        tool.setName("Calabash");
        tool.setVendor("Calabash");
        tool.setPrice(0);
        tool.setRecordPlayback(0);
        tool.setSupportDesktop(0);
        tool.setSupportMobile(1);
        tool.setSupportWeb(0);
        tool.setSupportWebServices(0);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(0);
        mDBHelper.addTool(tool);
    }

    public void search(View view){
        Tool toolForSearch = new Tool();

        toolForSearch.setRecordPlayback(recordPlayback.isChecked() ? 1 : 0);
        toolForSearch.setSupportDesktop(supportDesktop.isChecked() ? 1 : 0);
        toolForSearch.setSupportWebServices(supportWebServices.isChecked() ? 1 : 0);
        toolForSearch.setSupportWeb(supportWeb.isChecked() ? 1 : 0);
        toolForSearch.setSupportMobile(supportMobile.isChecked() ? 1 : 0);

        toolForSearch.setDocumentation(supportDocumentation.isChecked() ? 1 : 0);
        toolForSearch.setSupportCI(supportCI.isChecked() ? 1 : 0);
        toolForSearch.setReports(supportReports.isChecked() ? 1 : 0);

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

//        mDBHelper.closeDB();
    }

    public void clearDB(View view){
        mDBHelper.recreateTable();
    }

    public void fillDB(View view){
        fillDBData();
    }

}
