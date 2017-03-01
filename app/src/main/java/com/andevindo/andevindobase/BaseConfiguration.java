package com.andevindo.andevindobase;

/**
 * Created by heendher on 11/3/2016.
 */

public class BaseConfiguration {

    private String mFolderName;
    private String mSharedPreferencesName;

    private BaseConfiguration(Builder builder) {
        mFolderName = builder.mFolderName;
        mSharedPreferencesName = builder.mSharedPreferencesName;
    }

    public String getFolderName() {
        return mFolderName;
    }

    public String getSharedPreferencesName(){
        return mSharedPreferencesName;
    }

    public void setFolderName(String folderName) {
        mFolderName = folderName;
    }

    public static class Builder{

        private String mFolderName = "Andevindo";
        private String mSharedPreferencesName = "Andevindo";

        public Builder() {
        }

        public Builder setFolderName(String folderName){
            mFolderName = folderName;
            return this;
        }

        public Builder setSharedPreferencesName(String sharedPreferencesName){
            mSharedPreferencesName = sharedPreferencesName;
            return this;
        }

        public BaseConfiguration build(){
            return new BaseConfiguration(this);
        }
    }
}
