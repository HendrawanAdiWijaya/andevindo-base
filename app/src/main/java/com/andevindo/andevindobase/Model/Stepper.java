package com.andevindo.andevindobase.Model;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by heendher on 11/23/2016.
 */

public class Stepper {

    private String mTitle;
    private boolean mIsComplete;
    private boolean mIsReadyToComplete;
    private boolean mIsVisited;
    private View mView;
    private boolean mIsShowUp;

    public boolean isVisited() {
        return mIsVisited;
    }

    public void setVisited(boolean visited) {
        mIsVisited = visited;
    }

    public boolean isReadyToComplete() {
        return mIsReadyToComplete;
    }

    public void setReadyToComplete(boolean readyToComplete) {
        mIsReadyToComplete = readyToComplete;
    }

    public boolean isShowUp() {
        return mIsShowUp;
    }

    public void setShowUp(boolean showUp) {
        mIsShowUp = showUp;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }
}
