package com.andevindo.andevindobase.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.andevindo.andevindobase.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on   : 2/20/2018
 * Developed by : Hendrawan Adi Wijaya
 * Github       : https://github.com/andevindo
 * Website      : http://www.andevindo.com
 */

public class BaseCamera {

    private Activity mActivity;
    private String mCurrentPhotoPath;
    private String mApplicationId;

    public BaseCamera(Activity activity, String applicationId) {
        mActivity = activity;
        mApplicationId = applicationId;
    }


    public void show(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            Uri outPutUri = FileProvider.getUriForFile(mActivity, mApplicationId + ".provider", createImageFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
            mActivity.startActivityForResult(intent, requestCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }

    public Bitmap getCurrentPhotoBitmap(){
        return BitmapFactory.decodeFile(mCurrentPhotoPath);
    }
}
