package com.andevindo.andevindobase.Helper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by heendher on 11/2/2016.
 */

public class BaseJSONConverter {

    public static String getStringJSON(JSONObject jsonObject, String key) {
        try {
            if (jsonObject.isNull(key))
                return "";
            else
                return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getIntJSON(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double getDoubleJSON(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getBooleanJSON(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkCode(JSONObject jsonObject, int codePattern) throws JSONException {
        int code = 0;
        code = jsonObject.getInt("kode");
        Log.d("kode", code + "");
        if (code == codePattern)
            return true;
        else
            return false;
    }


}
