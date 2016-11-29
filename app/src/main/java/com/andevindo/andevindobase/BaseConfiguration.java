package com.andevindo.andevindobase;

/**
 * Created by heendher on 11/3/2016.
 */

public class BaseConfiguration {

    private String mFolderName;

    private BaseConfiguration(Builder builder) {
        mFolderName = builder.mFolderName;
    }

    public String getFolderName() {
        return mFolderName;
    }

    public void setFolderName(String folderName) {
        mFolderName = folderName;
    }

    public static class Builder{

        private String mFolderName = "Andevindo";

        public Builder() {
        }

        public Builder setFolderName(String folderName){
            mFolderName = folderName;
            return this;
        }

        public BaseConfiguration build(){
            return new BaseConfiguration(this);
        }
    }
}
