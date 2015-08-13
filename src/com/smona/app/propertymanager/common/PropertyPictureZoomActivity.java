package com.smona.app.propertymanager.common;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
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
import com.smona.app.propertymanager.util.PropertyCommonHelper;

public abstract class PropertyPictureZoomActivity extends PropertyBaseActivity {

    protected ViewGroup mPictureContainer;

    protected boolean isPictureMaxCount() {
        int count = mPictureContainer.getChildCount();
        return count >= 3;
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
        image.setTag(url);
        image.setOnClickListener(mOnClick);
        if (isDelete()) {
            image.setOnLongClickListener(mOnLongClickListener);
        }
        mPictureContainer.addView(image);
    }

    protected abstract boolean isDelete();

    protected OnLongClickListener mOnLongClickListener = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            showDeleteDialog(v);
            return true;
        }

    };

    protected OnClickListener mOnClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            gotoPreview(v);
        }
    };

    private void gotoPreview(View v) {
        String url = (String) v.getTag();
        Intent intent = new Intent();
        intent.putExtra("url", url);
        intent.setClass(PropertyPictureZoomActivity.this,
                PropertyPreviewActivity.class);
        startActivity(intent);
    }

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
