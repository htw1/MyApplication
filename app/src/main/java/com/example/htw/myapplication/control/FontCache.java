package com.example.htw.myapplication.control;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.HashMap;

public class FontCache {

    private static HashMap<String, Typeface> _fontCache = new HashMap<String, Typeface>();

    public static Typeface createFromAsset(AssetManager mgr, String path)
    {
        if (!_fontCache.containsKey(path)) {
            _fontCache.put(path, Typeface.createFromAsset(mgr, path));
        }

        return _fontCache.get(path);
    }

}
