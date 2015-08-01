package com.smona.app.propertymanager.common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;

import com.smona.app.propertymanager.PropertyBaseActivity;
import com.smona.app.propertymanager.util.LogUtil;

public abstract class PropertyStartupCameraActivity extends
        PropertyBaseActivity {

    protected static final int ACTION_CAMERA = 1;
    protected ViewGroup mPictureContainer;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (ACTION_CAMERA == requestCode) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                String fileName = Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/"
                        + System.currentTimeMillis()
                        + ".png";
                LogUtil.saveBitmap(bitmap, fileName);
                onCameraCallback(bitmap, fileName);
            }
        }
    }

    protected abstract void onCameraCallback(Bitmap bitmap, String fileName);
}
