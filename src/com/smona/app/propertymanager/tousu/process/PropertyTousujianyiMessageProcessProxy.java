package com.smona.app.propertymanager.tousu.process;

import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;

import android.content.Context;

public class PropertyTousujianyiMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyTousujianyiNetRequestMessageProcess mLocalJson;
    private PropertyTousujianyiNetRequestMessageProcess mNetRequestJson;
    private PropertyTousujianyiNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyTousujianyiMessageProcessProxy() {
        mLocalJson = new PropertyTousujianyiNetRequestMessageProcess();
        mNetRequestJson = new PropertyTousujianyiNetRequestMessageProcess();
        mNetSubmitJson = new PropertyTousujianyiNetSubmitMessageProcess();
    }

    public void requestTousujianyi(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestTousujianyi(context, callback);
        } else {
            mNetRequestJson.requestTousujianyi(context, callback);
        }
    }

    public void requestTousujianyidan(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestTousujianyidan(context, callback);
        } else {
            mNetRequestJson.requestTousujianyidan(context, callback);
        }
    }

    public void requestTousujianyidanDetail(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestTousujianyidanDetail(context, callback);
        } else {
            mNetRequestJson.requestTousujianyidanDetail(context, callback);
        }
    }

    public void submitTousujianyi(Context context, IQuestCallback callback) {
        mNetSubmitJson.submitTousujianyi(context, callback);
    }
}
