package com.example.htw.myapplication.control;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontCacheButton {

    private static Hashtable<String, Typeface> fontCacheButton = new Hashtable<String, Typeface>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCacheButton.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCacheButton.put(name, tf);
        }
        return tf;
    }

}
