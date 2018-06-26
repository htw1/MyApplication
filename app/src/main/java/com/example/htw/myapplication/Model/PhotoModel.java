package com.example.htw.myapplication.Model;

import android.support.annotation.Keep;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

public class PhotoModel {

    @Exclude private String desc;
    @Exclude private String url_link;
    @Exclude private String  title;

    public PhotoModel(String desc, String url_link, String title) {
        this.desc = desc;
        this.url_link = url_link;
        this.title = title;
    }
    //firebase
    public PhotoModel (){

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Keep
    public String getUrl_link() {
        return url_link;
    }
    @Keep
    public void setUrl_link(String image) {
        this.url_link = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
