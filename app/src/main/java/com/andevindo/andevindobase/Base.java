package com.andevindo.andevindobase;

/**
 * Created by heendher on 11/3/2016.
 */
public class Base {

    private static String sFOLDER_NAME = "Andevindo";

    private static Base ourInstance = new Base();

    public static Base setBaseConfiguration(BaseConfiguration baseConfiguration){
        sFOLDER_NAME = baseConfiguration.getFolderName();
        return ourInstance;
    }

    public static Base getConfig(){
        return ourInstance;
    }

    private Base() {
    }

    public String getFolderName(){
        return sFOLDER_NAME;
    }

}
