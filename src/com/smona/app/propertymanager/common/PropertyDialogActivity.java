package com.smona.app.propertymanager.common;

import com.smona.app.propertymanager.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.Gravity;

public class PropertyDialogActivity extends Activity {

    protected PropertyLoadingDialog mDialog;
    
    protected void showCustomProgrssDialog() {
        showCustomProgrssDialog("");
    }

    protected void showCustomProgrssDialog(String msg) {
        hideCustomProgressDialog();
        mDialog = new PropertyLoadingDialog(this, R.style.Property_Progress_Dialog);
        mDialog.setContentView(R.layout.property_common_loading);
        mDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        mDialog.startAnim();
        mDialog.setMessage(msg);
        mDialog.show();
        mDialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                finish();
            }
        });
    }

    public void hideCustomProgressDialog() {
        if (null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
