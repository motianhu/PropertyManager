package com.smona.app.propertymanager.common;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.HttpUploadFile;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyCommonHelper;
import com.smona.app.propertymanager.util.PropertyConstants;

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
                        + ".jpg";
                View image = onCameraCallback(bitmap);
                if (image == null) {
                    return;
                }
                LogUtil.saveBitmap(bitmap, fileName);
                HttpUploadFile.submitPost(PropertyConstants.UPLOAD, fileName,
                        new WeakReference<View>(image));

            }
        }
    }
    
    protected boolean isPictureMaxCount() {
        int count = mPictureContainer.getChildCount();
        return count >= 3;
    }

    private View onCameraCallback(Bitmap bitmap) {
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
        image.setOnLongClickListener(mOnLongClickListener);
        image.setImageBitmap(bitmap);
        return image;
    }

    protected void addImageView(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
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
        ImageLoaderManager.getInstance().loadImage(url, image);
        image.setOnLongClickListener(mOnLongClickListener);
    }

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            showDeleteDialog(v);
            return true;
        }

    };

    @SuppressLint({ "InflateParams", "NewApi" })
    private void showDeleteDialog(final View source) {
        final AlertDialog dlg = new AlertDialog.Builder(this,
                R.style.Property_DialogStyle).create();
        dlg.show();
        View delUi = LayoutInflater.from(this).inflate(
                R.layout.property_common_ui_delete, null);

        delUi.findViewById(R.id.delYes).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ViewGroup parent = (ViewGroup) source.getParent();
                        parent.removeView(source);
                        dlg.dismiss();
                    }

                });

        delUi.findViewById(R.id.delNo).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

        Window window = dlg.getWindow();
        window.setContentView(delUi);
        android.view.WindowManager.LayoutParams windowParams = window
                .getAttributes();
        window.setGravity(Gravity.BOTTOM);
        windowParams.width = PropertyCommonHelper.getSceenInfo().mScreenWidth;
        windowParams.x = 0;
        window.setAttributes(windowParams);
    }
}
