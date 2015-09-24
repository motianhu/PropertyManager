package com.smona.app.propertymanager.zulin.process;

import com.smona.app.propertymanager.data.process.PropertyLocalSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

import android.content.Context;

public class PropertyFangwuzulinMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyLocalSubmitMessageProcess mLocalJson;
    private PropertyFangwuzulinNetRequestMessageProcess mNetRequestJson;
    private PropertyFangwuzulinNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyFangwuzulinMessageProcessProxy() {
        mLocalJson = new PropertyLocalSubmitMessageProcess();
        mNetRequestJson = new PropertyFangwuzulinNetRequestMessageProcess();
        mNetSubmitJson = new PropertyFangwuzulinNetSubmitMessageProcess();
    }

    public void requestFangwuzulin(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulin(context, request, callback);
        } else {
            mNetRequestJson.requestFangwuzulin(context, request, callback);
        }
    }

    public void requestFangwuzulinType(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulinType(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulinType(context, callback);
        }
    }

    public void requestFangwuzulinMine(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulinMine(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulinMine(context, request, callback);
        }
    }

    public void submitFangwuzulindan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitFangwuzulindan(context, request, callback);
    }

    public void submitFangwuzulinCancelPublish(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitFangwuzulinCancelPublish(context, request,
                callback);
    }
}
