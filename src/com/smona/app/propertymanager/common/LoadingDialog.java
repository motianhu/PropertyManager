package com.smona.app.propertymanager.common;

import com.smona.app.propertymanager.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public void startAnim() {
        ImageView loadingImageView = (ImageView) findViewById(R.id.sf_iv_progress_dialog_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) loadingImageView
                .getBackground();
        animationDrawable.start();
    }

    public void setMessage(String msg) {
        TextView loadingTextView = (TextView) findViewById(R.id.sf_tv_progress_dialog_loading);
        if (!TextUtils.isEmpty(msg)) {
            loadingTextView.setText(msg);
        } else {
            // loadingTextView.setText(R.string.sf_progress_dialog_image_loading);
        }
    }
}
