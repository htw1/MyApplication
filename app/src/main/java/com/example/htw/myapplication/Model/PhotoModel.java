package com.example.htw.myapplication.Model;

import android.support.annotation.Keep;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

public class PhotoModel {

    @Exclude private String desc;
    @Exclude private String image;
    @Exclude private String  title;

    public PhotoModel(String desc, String image, String title) {
        this.desc = desc;
        this.image = image;
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
    public String getImage() {
        return image;
    }
    @Keep
    public void setImage(String image) {
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
