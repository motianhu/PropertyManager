package com.smona.app.propertymanager.zulin.process;

import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;

import android.content.Context;

public class PropertyFangwuzulinMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyFangwuzulinNetRequestMessageProcess mLocalJson;
    private PropertyFangwuzulinNetRequestMessageProcess mNetRequestJson;
    private PropertyFangwuzulinNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyFangwuzulinMessageProcessProxy() {
        mLocalJson = new PropertyFangwuzulinNetRequestMessageProcess();
        mNetRequestJson = new PropertyFangwuzulinNetRequestMessageProcess();
        mNetSubmitJson = new PropertyFangwuzulinNetSubmitMessageProcess();
    }

    public void requestFangwuzulin(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulin(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulin(context, callback);
        }
    }

    public void requestFangwuzulinDetail(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulinDetail(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulinDetail(context, callback);
        }
    }

    public void requestFangwuzulinType(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulinType(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulinType(context, callback);
        }
    }
    

    public void requestFangwuzulinMine(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestFangwuzulinMine(context, callback);
        } else {
            mNetRequestJson.requestFangwuzulinMine(context, callback);
        }
    }

    public void submitFangwuzulindan(Context context, IQuestCallback callback) {
        mNetSubmitJson.submitFangwuzulindan(context, callback);
    }
}
