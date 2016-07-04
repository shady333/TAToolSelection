package com.olegdudar.tatoolselection;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    String name;
    String vendor;
    int recordPlayback;
    float price;
    int supportWeb;
    int supportDesktop;
    int supportMobile;
    int supportWebServices;

    Tool(){
        this.recordPlayback = 0;
        this.supportDesktop = 0;
        this.supportMobile = 0;
        this.supportWeb = 0;
        this.supportWebServices = 0;
    }

    public Tool(String name, String vendor, float price){
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.recordPlayback = 0;
        this.supportDesktop = 0;
        this.supportMobile = 0;
        this.supportWeb = 0;
        this.supportWebServices = 0;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setVendor(String vendor){
        this.vendor = vendor;
    }

    public void setRecordPlayback(int recordPlayback){
        this.recordPlayback = recordPlayback;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setSupportWeb(int supportWeb){
        this.supportWeb = supportWeb;
    }

    public void setSupportDesktop(int supportDesktop){
        this.supportDesktop = supportDesktop;
    }

    public void setSupportMobile(int supportMobile){
        this.supportMobile = supportMobile;
    }

    public void setSupportWebServices(int supportWebServices){
        this.supportWebServices = supportWebServices;
    }

    //getters
    public String getName(){
        return this.name;
    }

    public String getVendor(){
        return this.vendor;
    }

    public int getRecordPlayback(){
        return this.recordPlayback;
    }

    public float getPrice(){
        return this.price;
    }

    public int getSupportWeb(){
        return this.supportWeb;
    }

    public int getSupportDesktop(){
        return this.supportDesktop;
    }

    public int getSupportMobile(){
        return this.supportMobile;
    }

    public int getSupportWebServices(){
        return this.supportWebServices;
    }


}
