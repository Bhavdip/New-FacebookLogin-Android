package com.edu.martin.sample.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

public class TypeFaceSingleTone {

    /**
     * List of catch the Type face so later on we pick up from catch instead of create new type face
     */
    private LruCache<String, Typeface> sTypefaceCache = new LruCache<>(3);

    private static TypeFaceSingleTone singleInstance = new TypeFaceSingleTone();

    private TypeFaceSingleTone() {
    }


    public static TypeFaceSingleTone getSingleInstance() {
        return singleInstance;
    }

    public Typeface getCatchTypeFace(Context aContext, String typefaceName) {
        // make absolute path of custom fonts directory
        StringBuilder custFontPath = new StringBuilder("fonts/");
        custFontPath.append(typefaceName);
        // first try to pickup typeface form catch list
        Typeface tmpTypeface = sTypefaceCache.get(typefaceName);
        // if could not available in catch list the we need to create it
        // the added in to the catch list for later on reused
        if (tmpTypeface == null) {
            // create new Type face from asset directory
            tmpTypeface = Typeface.createFromAsset(aContext.getAssets(), custFontPath.toString());
            if (tmpTypeface != null)
                sTypefaceCache.put(typefaceName, tmpTypeface);
        }
        return tmpTypeface;
    }
}