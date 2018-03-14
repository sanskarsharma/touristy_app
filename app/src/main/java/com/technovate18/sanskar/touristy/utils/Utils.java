package com.technovate18.sanskar.touristy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;

import com.technovate18.sanskar.touristy.Touristy;

/**
 * Created by hadoop on 15/3/18.
 */

public class Utils {



    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    // for in-app browser
    public static boolean isSameDomain(String url, String url1) {
        return getRootDomainUrl(url.toLowerCase()).equals(getRootDomainUrl(url1.toLowerCase()));
    }

    // for in-app browser
    private static String getRootDomainUrl(String url) {
        String[] domainKeys = url.split("/")[2].split("\\.");
        int length = domainKeys.length;
        int dummy = domainKeys[0].equals("www") ? 1 : 0;
        if (length - dummy == 2)
            return domainKeys[length - 2] + "." + domainKeys[length - 1];
        else {
            if (domainKeys[length - 1].length() == 2) {
                return domainKeys[length - 3] + "." + domainKeys[length - 2] + "." + domainKeys[length - 1];
            } else {
                return domainKeys[length - 2] + "." + domainKeys[length - 1];
            }
        }
    }

    // for in-app browser bookmark button
    public static void tintMenuIcon(Context context, MenuItem item, int color) {
        Drawable drawable = item.getIcon();
        if (drawable != null) {
            // If we don't mutate the drawable, then all drawable's with this id will have a color
            // filter applied to it.
            drawable.mutate();
            drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void bookmarkUrl(Context context, String url) {


    }

    public static boolean isBookmarked(Context context, String url) {
//        SharedPreferences pref = context.getSharedPreferences("androidhive", 0);
        return false;
    }


    public static void saveData(String id, String value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putString(id, value).apply();

        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, long value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putLong(id, value).apply();

            Log.i("email", "Save Data : " + id + " : " + value);
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, int value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putInt(id, value).apply();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static void saveData(String id, boolean value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putBoolean(id, value).apply();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static boolean getData(String id, boolean value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getBoolean(id, value);
        }
        return value;
    }

    public static int getData(String id, int value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getInt(id, value);
        }
        return value;
    }

    public static long getData(String id, long value) {
        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getLong(id, value);
        }
        return value;
    }

    public static String getData(String id, String value) {
        if (id != null && id.trim().length() > 0) {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getString(id, value);
        }
        Log.i("email", "get data : " + id + " : " + value);
        return value;
    }
    public static SharedPreferences getPreferencesInstance(SharedPreferences preferences) {

        if (preferences == null) {

            return PreferenceManager.getDefaultSharedPreferences(Touristy.getContext());
        }

        return preferences;
    }



}
