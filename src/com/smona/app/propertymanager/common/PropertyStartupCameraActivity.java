package com.smona.app.propertymanager.common;

import java.lang.ref.WeakReference;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.util.HttpUploadFile;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public abstract class PropertyStartupCameraActivity extends
        PropertyPictureZoomActivity {

    protected static final int ACTION_CAMERA = 1;

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
                        + ".jpg";
                View image = onCameraCallback(bitmap, fileName);
                if (image == null) {
                    return;
                }
                LogUtil.saveBitmap(bitmap, fileName);
                HttpUploadFile.submitPost(PropertyConstants.HTTP_IP_PORT
                        + PropertyConstants.URL_UPLOAD, fileName,
                        new WeakReference<View>(image));

            }
        }
    }

    private View onCameraCallback(Bitmap bitmap, String fileName) {
        if (bitmap == null) {
            return null;
        }
        ImageView image = new ImageView(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                getResources()
                        .getDimensionPixelSize(
                                R.dimen.property_common_paishezhaoping_container_height),
                getResources()
                        .getDimensionPixelSize(
                                R.dimen.property_common_paishezhaoping_container_height));
        param.leftMargin = 10;
        mPictureContainer.addView(image, 0, param);
        image.setOnClickListener(mOnClick);
        image.setTag("file//:" + fileName);
        image.setOnLongClickListener(mOnLongClickListener);
        image.setImageBitmap(bitmap);
        return image;
    }

    @Override
    protected boolean isDelete() {
        return true;
    }
}
