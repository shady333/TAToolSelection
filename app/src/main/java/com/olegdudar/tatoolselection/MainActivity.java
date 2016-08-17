package com.olegdudar.tatoolselection;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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

    private CheckBox supportLanguages;

    private TextView resultCaption;
    private TextView resultValue;

    private Button createDB;
    private Button deleteDB;

    TableLayout resultsLayout;

    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listLanguages);
        listView.setVisibility(View.INVISIBLE);
        //button = (Button) findViewById(R.id.testbutton);

        recordPlayback = (CheckBox)findViewById(R.id.cb_recordplayback);
        supportDesktop = (CheckBox)findViewById(R.id.cb_desktop);
        supportMobile = (CheckBox)findViewById(R.id.cb_mobile);
        supportWeb = (CheckBox)findViewById(R.id.cb_web);
        supportWebServices = (CheckBox)findViewById(R.id.cb_webservices);

        supportDocumentation = (CheckBox)findViewById(R.id.cb_documentation);
        supportCI = (CheckBox)findViewById(R.id.cb_cisupport);
        supportReports = (CheckBox)findViewById(R.id.cb_reports);

        supportLanguages = (CheckBox)findViewById(R.id.cb_language);

        resultCaption = (TextView)findViewById(R.id.tv_resultCaption);
        resultValue = (TextView)findViewById(R.id.tv_resultValue);

//        createDB = (Button)findViewById(R.id.b_create);
//        deleteDB = (Button)findViewById(R.id.b_delete);

        //hide not needed labels
        resultCaption.setVisibility(View.INVISIBLE);
        resultValue.setVisibility(View.INVISIBLE);

        resultsLayout = (TableLayout)findViewById(R.id.resulTableLayout);

        mDBHelper = new DBHelper(getApplicationContext());

        mDBHelper.recreateTable();
        fillDBData();

        String[] sports = getResources().getStringArray(R.array.languages_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, sports);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

    }

    private void fillDBData() {

        if(mDBHelper == null){
            mDBHelper = new DBHelper(getApplicationContext());
        }

        ArrayList<String> asd = new ArrayList<>();

        Tool tool = new Tool("Test Complete", "Smart Bear", 1000);
        tool.setRecordPlayback(1);
        tool.setSupportDesktop(1);
        tool.setSupportMobile(1);
        tool.setSupportWeb(1);
        tool.setSupportWebServices(1);
        tool.setSupportCI(1);
        tool.setDocumentation(1);
        tool.setReports(1);
        tool.setId(1);
        asd.clear();
        asd.add("Python");
        asd.add("VBScript");
        asd.add("Jscript");
        asd.add("DelphiScript");
        asd.add("C++Script");
        asd.add("C#Script");
        tool.setSupportedLanguages(asd);
        tool.setDescription("TestComplete is a functional automated testing platform developed by SmartBear Software. TestComplete gives testers the ability to create automated tests for Microsoft Windows, Web, Android (operating system), and iOS applications. Tests can be recorded, scripted or manually created with keyword driven operations and used for automated playback and error logging.");
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
        tool.setId(2);
        asd.clear();
        asd.add("VBScript");
        asd.add("JavaScript");
        asd.add("VC++");
        tool.setSupportedLanguages(asd);
        tool.setDescription("HPE Unified Functional Testing (UFT) software, formerly known as HP QuickTest Professional (QTP),[1] provides functional and regression test automation for software applications and environments.[2] HPE Unified Functional Testing can be used for enterprise quality assurance");
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
        tool.setId(3);
        asd.clear();
        asd.add("JavaScript");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Apache JMeter is an Apache project that can be used as a load testing tool for analyzing and measuring the performance of a variety of services, with a focus on web applications.");
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
        tool.setId(4);
        asd.clear();
        asd.add("C#");
        asd.add("JavaScript");
        asd.add("Objective-C");
        asd.add("Perl");
        asd.add("PHP");
        asd.add("Python");
        asd.add("Ruby");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Selenium Remote Control (RC) is a test tool that allows you to write automated web application UI tests in any programming language against any HTTP website using any mainstream JavaScript-enabled browser.");
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
        tool.setId(5);
        asd.clear();
        asd.add("C#");
        asd.add("JavaScript");
        asd.add("Objective-C");
        asd.add("Perl");
        asd.add("PHP");
        asd.add("Python");
        asd.add("Ruby");
        tool.setSupportedLanguages(asd);
        tool.setDescription("WebDriver is designed to provide a simpler, more concise programming interface in addition to addressing some limitations in the Selenium-RC API. Selenium-WebDriver was developed to better support dynamic web pages where elements of a page may change without the page itself being reloaded. WebDriver’s goal is to supply a well-designed object-oriented API that provides improved support for modern advanced web-app testing problems.");
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
        tool.setId(6);
        asd.clear();
        asd.add("Own BASIC-like syntax");
        tool.setSupportedLanguages(asd);
        tool.setDescription("AutoIt is a freeware automation language for Microsoft Windows. In its earliest release, the software was primarily intended to create automation scripts (sometimes called macros) for Microsoft Windows programs[3] but has since grown to include enhancements in both programming language design and overall functionality.");
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
        tool.setId(7);
        asd.clear();
        asd.add("Groovy");
        tool.setSupportedLanguages(asd);
        tool.setDescription("SoapUI is an open-source web service testing application for service-oriented architectures (SOA) and representational state transfers (REST). Its functionality covers web service inspection, invoking, development, simulation and mocking, functional testing, load and compliance testing. A commercial version, SoapUI Pro, which mainly focuses on features designed to enhance productivity, was also developed by Eviware software. In 2011, SmartBear Software acquired Eviware.");
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
        tool.setId(8);
        asd.clear();
        asd.add("VB Script");
        asd.add("C#");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Automated tests that drive your application through its user interface (UI) are known as coded UI tests (CUITs). These tests include functional testing of the UI controls. They let you verify that the whole application, including its user interface, is functioning correctly. Coded UI Tests are particularly useful where there is validation or other logic in the user interface, for example in a web page. They are also frequently used to automate an existing manual test.");
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
        tool.setId(9);
        asd.clear();
        asd.add("Java");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Robotium is an open-source test framework for writing automatic gray box testing cases for Android applications. With the support of Robotium, test case developers can write function, system and acceptance test scenarios, spanning multiple Android activities. Robotium can be used both for testing applications where the source code is available and applications where only the APK file is available and the implementation details are not known.");
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
        tool.setId(10);
        asd.clear();
        asd.add("Java");
        tool.setSupportedLanguages(asd);
        tool.setDescription("UI Automator is a UI testing framework suitable for cross-app functional UI testing across system and installed apps.");
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
        tool.setId(11);
        asd.clear();
        asd.add("Java");
        tool.setSupportedLanguages(asd);
        tool.setDescription("The Espresso API encourages test authors to think in terms of what a user might do while interacting with the application - locating UI elements and interacting with them. At the same time, the framework prevents direct access to activities and views of the application because holding on to these objects and operating on them off the UI thread is a major source of test flakiness. Thus, you will not see methods like getView and getCurrentActivity in the Espresso API. You can still safely operate on views by implementing your own ViewActions and ViewAssertions.");
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
        tool.setId(12);
        asd.clear();
        asd.add("Java");
        asd.add("Ruby");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Appium is an open-source tool for automating native, mobile web, and hybrid applications on iOS and Android platforms. Native apps are those written using the iOS or Android SDKs. Mobile web apps are web apps accessed using a mobile browser (Appium supports Safari on iOS and Chrome or the built-in 'Browser' app on Android). Hybrid apps have a wrapper around a \"webview\" -- a native control that enables interaction with web content. Projects like Phonegap, make it easy to build apps using web technologies that are then bundled into a native wrapper, creating a hybrid app.");
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
        tool.setId(13);
        asd.clear();
        asd.add("Ruby");
        tool.setSupportedLanguages(asd);
        tool.setDescription("Calabash is a framework that enables Automated UI Acceptance Tests written in Cucumber to be run on iOS and Android applications. While Calabash integrates tightly with Xamarin.iOS and Xamarin.Android project, it can also be used with iOS and Android projects written in the indigenous languages of Objective-C and Java.");
        mDBHelper.addTool(tool);
    }

    public void searchIds(View view){
        Tool toolForSearch = new Tool();

        toolForSearch = generateToolForSearch();



        StringBuilder tools = new StringBuilder();

        List<Integer> returnedTool = mDBHelper.selectToolIds(toolForSearch);

        if(supportLanguages.isChecked() && getSelectedItems().isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "Please select at least one programming language";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if(returnedTool.size() != 0){
            resultCaption.setVisibility(View.INVISIBLE);
            resultValue.setVisibility(View.INVISIBLE);

            Intent intent = new Intent(this, ResultsActivity.class);

            intent.putIntegerArrayListExtra("results", (ArrayList<Integer>) returnedTool);

            startActivity(intent);
        }
        else{
            //set labels visible
//            resultCaption.setVisibility(View.VISIBLE);
//            resultValue.setVisibility(View.VISIBLE);
//
//            resultValue.setText("Can't find any tool matching your request");

            Context context = getApplicationContext();
            CharSequence text = "Can't find any tool matching your request";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

//        mDBHelper.closeDB();


    }

    private Tool generateToolForSearch() {

        Tool toolForSearch = new Tool();

        toolForSearch.setRecordPlayback(recordPlayback.isChecked() ? 1 : 0);
        toolForSearch.setSupportDesktop(supportDesktop.isChecked() ? 1 : 0);
        toolForSearch.setSupportWebServices(supportWebServices.isChecked() ? 1 : 0);
        toolForSearch.setSupportWeb(supportWeb.isChecked() ? 1 : 0);
        toolForSearch.setSupportMobile(supportMobile.isChecked() ? 1 : 0);

        toolForSearch.setDocumentation(supportDocumentation.isChecked() ? 1 : 0);
        toolForSearch.setSupportCI(supportCI.isChecked() ? 1 : 0);
        toolForSearch.setReports(supportReports.isChecked() ? 1 : 0);
        if(supportLanguages.isChecked()) {
            toolForSearch.setSupportedLanguages(getSelectedItems());
        }
        else{
            toolForSearch.setSupportedLanguages(null);
        }
        return toolForSearch;
    }

    public ArrayList<String> getSelectedItems() {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        return selectedItems;

//        Intent intent = new Intent(getApplicationContext(),
//                ResultActivity.class);
//
//        // Create a bundle object
//        Bundle b = new Bundle();
//        b.putStringArray("selectedItems", outputStrArr);
//
//        // Add the bundle to the intent.
//        intent.putExtras(b);
//
//        // start the ResultActivity
//        startActivity(intent);
    }

    public void activateLanguageSelection(View view){
        ViewGroup.LayoutParams params = listView.getLayoutParams();


        if(supportLanguages.isChecked()){

            int itemscount = listView.getAdapter().getCount();

            params.height = 300;//itemscount * 75;

            listView.setLayoutParams(params);

            listView.setVisibility(View.VISIBLE);
        }
        else{

            params.height = 0;

            listView.setLayoutParams(params);

            listView.setVisibility(View.INVISIBLE);

        }
    }

}
