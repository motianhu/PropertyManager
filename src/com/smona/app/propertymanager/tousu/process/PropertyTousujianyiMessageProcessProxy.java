package com.smona.app.propertymanager.tousu.process;

import com.smona.app.propertymanager.data.process.PropertyLocalSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

import android.content.Context;

public class PropertyTousujianyiMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyLocalSubmitMessageProcess mLocalJson;
    private PropertyTousujianyiNetRequestMessageProcess mNetRequestJson;
    private PropertyTousujianyiNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyTousujianyiMessageProcessProxy() {
        mLocalJson = new PropertyLocalSubmitMessageProcess();
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

    public void requestTousujianyidan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestTousujianyidan(context, callback);
        } else {
            mNetRequestJson.requestTousujianyidan(context, request, callback);
        }
    }

    public void requestTousujianyidanDetail(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestTousujianyidanDetail(context, callback);
        } else {
            mNetRequestJson.requestTousujianyidanDetail(context, request,
                    callback);
        }
    }

    public void submitTousujianyidan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitTousujianyidan(context, request, callback);
    }
    
    public void submitTousujianyidanPingjia(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitTousujianyidanPingjia(context, request, callback);
    }
}
