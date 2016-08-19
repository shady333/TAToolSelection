package com.olegdudar.tatoolselection;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Tool{
    String name;
    String vendor;
    String description;
    int id;
    int recordPlayback;
    float price;
    int supportWeb;
    int supportDesktop;
    int supportMobile;
    int supportWebServices;
    ArrayList<String> supportedLanguages;

    String webLink;

    int supportCI;
    int reports;
    int documentation;

    Tool(){
        this.supportedLanguages = new ArrayList<>();
        }

    public Tool(String name, String vendor, float price){
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.supportedLanguages = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getSupportCI() {
        return supportCI;
    }



    public void setSupportCI(int supportCI) {
        this.supportCI = supportCI;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getDocumentation() {
        return documentation;
    }

    public void setDocumentation(int documentation) {
        this.documentation = documentation;
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

    public void setSupportedLanguages(ArrayList<String> languages) {
        this.supportedLanguages = languages;
    }

    public void setWebLink(String webLink){
        this.webLink = webLink;
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

    public ArrayList<String> getSupportedLanguages() {
        return this.supportedLanguages;
    }

    public String getWebLink(){
        return this.webLink;
    }
}
