package com.smona.app.propertymanager.baoxiu.process;

import android.content.Context;

import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyWuyebaoxiuNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String MSG_WUYEBAOXIU_SUBMIT = "3300";
    private static final String MSG_WUYEBAOXIU_PINGJIA_SUBMIT = "3600";

    public void submitWuyebaoxiudan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYEBAOXIU_SUBMIT, request, callback);
    }

    public void submitWuyebaoxiudanPingjia(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        submitRequest(MSG_WUYEBAOXIU_PINGJIA_SUBMIT, request, callback);
    }
}
