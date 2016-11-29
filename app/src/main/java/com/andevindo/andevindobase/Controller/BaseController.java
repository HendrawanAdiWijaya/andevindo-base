package com.andevindo.andevindobase.Controller;

import android.content.Context;

/**
 * Created by heendher on 11/11/2016.
 */

public abstract class BaseController {

    private Context mContext;

    public BaseController(Context context) {
        mContext = context;
    }

    public Context getContext(){
        return mContext;
    }

}
