package com.smona.app.propertymanager.tousu.process;

import android.content.Context;

import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyTousujianyiNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String MSG_TOUSUJIANYI_SUBMIT = "3800";

    public void submitTousujianyidan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_TOUSUJIANYI_SUBMIT, request, callback);
    }
}
