package com.smona.app.propertymanager;

import com.smona.app.propertymanager.common.LoadingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Gravity;

public class PropertyDialogActivity extends Activity {

    protected LoadingDialog mDialog;

    final void showCustomProgrssDialog(String msg) {
        if (null == mDialog) {
            mDialog = new LoadingDialog(this, R.style.Property_Progress_Dialog);
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
    }

    public void hideCustomProgressDialog() {
        if (null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        showCustomProgrssDialog("");
        return mDialog;
    }
}
