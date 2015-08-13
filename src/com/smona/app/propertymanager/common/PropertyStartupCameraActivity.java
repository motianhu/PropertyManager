package com.smona.app.propertymanager.common;

import java.lang.ref.WeakReference;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smona.app.propertymanager.R;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.HttpUploadFile;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public abstract class PropertyStartupCameraActivity extends
        PropertyPictureZoomActivity {

    protected static final int ACTION_CAMERA = 1;
    protected static final int ACTION_GALLERY = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mRoot.findViewById(R.id.action_select).setVisibility(View.GONE);
        mRoot.findViewById(R.id.start_select).setVisibility(View.VISIBLE);
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
                LogUtil.d("onActivityResult", "ACTION_CAMERA fileName: " + fileName);
                if (image == null) {
                    return;
                }
                LogUtil.saveBitmap(bitmap, fileName);
                HttpUploadFile.submitPost(PropertyConstants.HTTP_IP_PORT
                        + PropertyConstants.URL_UPLOAD, fileName,
                        new WeakReference<View>(image));

            } else if (ACTION_GALLERY == requestCode) {
                Uri url = data.getData();
                String fileName = getRealPathFromURI(url);
                LogUtil.d("onActivityResult", "ACTION_GALLERY fileName: " + fileName + ", url: " + url.toString());
                View image = onCameraCallback(url.toString());
                if (image == null) {
                    return;
                }
                HttpUploadFile.submitPost(PropertyConstants.HTTP_IP_PORT
                        + PropertyConstants.URL_UPLOAD, fileName,
                        new WeakReference<View>(image));
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null,
                null, null);
        if (cursor.moveToFirst()) {
            ;
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void initBody() {
        initView(R.id.start_select);
        initView(R.id.action_camera);
        initView(R.id.action_gallery);
    }

    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.start_select:
            actionSelect();
            break;
        case R.id.action_camera:
            actionCamera();
            break;
        case R.id.action_gallery:
            actionGallery();
            break;
        }
    }

    private void actionSelect() {
        if (isPictureMaxCount()) {
            return;
        }
        mRoot.findViewById(R.id.start_select).setVisibility(View.GONE);
        mRoot.findViewById(R.id.action_select).setVisibility(View.VISIBLE);
    }

    private void actionGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); // 4.4推荐用此方式，4.4以下的API需要再兼容
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, ACTION_GALLERY);
    }

    private void actionCamera() {
        if (isPictureMaxCount()) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        startActivityForResult(intent, ACTION_CAMERA);
    }

    private View onCameraCallback(String fileName) {
        if(TextUtils.isEmpty(fileName)) {
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
        image.setTag(fileName);
        image.setOnLongClickListener(mOnLongClickListener);
        ImageLoaderManager.getInstance().loadImage(fileName, image);
        return image;
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
