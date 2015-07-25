package com.smona.app.propertymanager.notify.process;

import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;

import android.content.Context;

public class PropertyWuyetongzhiMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyWuyetongzhiNetRequestMessageProcess mLocalJson;
    private PropertyWuyetongzhiNetRequestMessageProcess mNetRequestJson;
    private PropertyWuyetongzhiSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyWuyetongzhiMessageProcessProxy() {
        mLocalJson = new PropertyWuyetongzhiNetRequestMessageProcess();
        mNetRequestJson = new PropertyWuyetongzhiNetRequestMessageProcess();
        mNetSubmitJson = new PropertyWuyetongzhiSubmitMessageProcess();
    }

    @Override
    public void requestWuyetongzhi(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestWuyetongzhi(context, callback);
        } else {
            mNetRequestJson.requestWuyetongzhi(context, callback);
        }
    }

    @Override
    public void requestWuyetongzhiDetail(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestWuyetongzhiDetail(context, callback);
        } else {
            mNetRequestJson.requestWuyetongzhiDetail(context, callback);
        }
    }

    public void submitWuyetongzhiRead(Context context, IQuestCallback callback) {
        mNetSubmitJson.submitWuyetongzhiRead(context, callback);
    }
}
