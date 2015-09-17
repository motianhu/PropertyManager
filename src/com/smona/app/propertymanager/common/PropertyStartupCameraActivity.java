package com.smona.app.propertymanager.common;

import java.lang.ref.WeakReference;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
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
                String fileName = getRealPathFromURI(this, url);
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

    @SuppressLint("NewApi")
    public String getRealPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= 19;
        if(!isKitKat) {
            return this.getRealPathFromUriLessKitkat(uri);
        } else
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
            String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
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
    
    private String getRealPathFromUriLessKitkat(Uri contentUri) {
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
}
