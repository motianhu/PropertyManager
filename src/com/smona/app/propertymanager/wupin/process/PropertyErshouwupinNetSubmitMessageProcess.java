package com.smona.app.propertymanager.wupin.process;

import android.content.Context;

import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyErshouwupinNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String MSG_ERSHOUWUPIN_SUBMIT = "5000";
    private static final String MSG_ERSHOUWUPIN_CANCEL_PUBLISH_SUBMIT = "5100";

    public void submitErshouwupindan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_ERSHOUWUPIN_SUBMIT, request, callback);
    }

    public void submitErshouwupinCancelPublish(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_ERSHOUWUPIN_CANCEL_PUBLISH_SUBMIT, request, callback);
    }
}
