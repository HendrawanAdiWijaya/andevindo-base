package com.andevindo.andevindobase.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by heendher on 11/18/2016.
 */

public class BaseUsefullMethods {

    public static void shareIt(Activity activity, String subject, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        activity.startActivity(Intent.createChooser(intent, "Share via"));
    }

}
