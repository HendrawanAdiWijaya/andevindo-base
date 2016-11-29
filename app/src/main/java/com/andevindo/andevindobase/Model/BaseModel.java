package com.andevindo.andevindobase.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by heendher on 11/2/2016.
 */

public class BaseModel {

    public String getStringJSON(JSONObject jsonObject, String key){
        try{
            return jsonObject.getString(key);
        }catch (JSONException e){
            e.printStackTrace();
            return "";
        }
    }

    public int getIntJSON(JSONObject jsonObject, String key){
        try{
            return jsonObject.getInt(key);
        }catch (JSONException e){
            e.printStackTrace();
            return 0;
        }
    }

    public boolean getBooleanJSON(JSONObject jsonObject, String key){
        try{
            return jsonObject.getBoolean(key);
        }catch (JSONException e){
            e.printStackTrace();
            return false;
        }
    }

}
