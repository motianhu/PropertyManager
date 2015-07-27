package com.smona.app.propertymanager.notify.process;

import com.smona.app.propertymanager.data.process.PropertyLocalSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

import android.content.Context;

public class PropertyWuyetongzhiMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyLocalSubmitMessageProcess mLocalJson;
    private PropertyWuyetongzhiNetRequestMessageProcess mNetRequestJson;
    private PropertyWuyetongzhiSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyWuyetongzhiMessageProcessProxy() {
        mLocalJson = new PropertyLocalSubmitMessageProcess();
        mNetRequestJson = new PropertyWuyetongzhiNetRequestMessageProcess();
        mNetSubmitJson = new PropertyWuyetongzhiSubmitMessageProcess();
    }

    public void requestWuyetongzhi(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestWuyetongzhi(context, callback);
        } else {
            mNetRequestJson.requestWuyetongzhi(context, request, callback);
        }
    }

    // no use
    public void requestWuyetongzhiDetail(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestWuyetongzhiDetail(context, callback);
        } else {
            mNetRequestJson
                    .requestWuyetongzhiDetail(context, request, callback);
        }
    }

    public void submitWuyetongzhiRead(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitWuyetongzhiRead(context, request, callback);
    }
}
