package com.andevindo.andevindobase.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.andevindo.andevindobase.Base;

/**
 * Created by heendher on 3/1/2017.
 */

public class BaseSharedPreferences {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    public BaseSharedPreferences(Context context){
        mSharedPreferences = context.getSharedPreferences(Base.getConfig().getSharedPreferencesName(), Context.MODE_PRIVATE);
    }

    public void edit(){
        mEditor = mSharedPreferences.edit();
    }

    public void commit(){
        mEditor.commit();
        mEditor = null;
    }

    public void clear(){
        mEditor.clear();
    }

    public void put(String key, String value){
        mEditor.putString(key, value);
    }

    public void put(String key, int value){
        mEditor.putInt(key, value);
    }

    public void put(String key, boolean value){
        mEditor.putBoolean(key, value);
    }

    public void put(String key, float value){
        mEditor.putFloat(key, value);
    }

    public void put(String key, Long value){
        mEditor.putLong(key, value);
    }

    public String getString(String key){
        return mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue){
        return mSharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key){
        return mSharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue){
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key){
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue){
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public float getFloat(String key){
        return mSharedPreferences.getFloat(key, 0);
    }

    public float getFloat(String key, float defaultValue){
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public long getLong(String key){
        return mSharedPreferences.getLong(key, 0);
    }

    public long getLong(String key, Long defaultValue){
        return mSharedPreferences.getLong(key, defaultValue);
    }

}
