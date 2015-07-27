package com.smona.app.propertymanager.notify.process;

import android.content.Context;

import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyWuyetongzhiSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {

    private static final String MSG_WUYETONGZHI_READ_SUBMIT = "5500";

    public void submitWuyetongzhiRead(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYETONGZHI_READ_SUBMIT, request, callback);
    }
}
