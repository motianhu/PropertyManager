package com.smona.app.propertymanager.zulin.process;

import android.content.Context;

import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyFangwuzulinNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String MSG_FANGWUZULIN_SUBMIT = "4400";
    private static final String MSG_WUYEBAOXIU_CANCEL_SUBMIT = "4600";

    public void submitFangwuzulindan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_FANGWUZULIN_SUBMIT, request, callback);
    }

    public void submitFangwuzulinCancelPublish(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYEBAOXIU_CANCEL_SUBMIT, request, callback);
    }
}
