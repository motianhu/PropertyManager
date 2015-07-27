package com.smona.app.propertymanager;

import android.app.AlertDialog;
import android.content.Context;

public class PropertySelectedDialog extends AlertDialog {

    protected PropertySelectedDialog(Context context, boolean cancelable,
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
