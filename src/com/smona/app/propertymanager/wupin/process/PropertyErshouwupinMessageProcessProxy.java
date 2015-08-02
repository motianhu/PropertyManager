package com.smona.app.propertymanager.wupin.process;

import com.smona.app.propertymanager.data.process.PropertyLocalSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

import android.content.Context;

public class PropertyErshouwupinMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyLocalSubmitMessageProcess mLocalJson;
    private PropertyErshouwupinNetRequestMessageProcess mNetRequestJson;
    private PropertyErshouwupinNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = true;

    public PropertyErshouwupinMessageProcessProxy() {
        mLocalJson = new PropertyLocalSubmitMessageProcess();
        mNetRequestJson = new PropertyErshouwupinNetRequestMessageProcess();
        mNetSubmitJson = new PropertyErshouwupinNetSubmitMessageProcess();
    }

    public void requestErshouwupin(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestErshouwupin(context, callback);
        } else {
            mNetRequestJson.requestErshouwupin(context, request, callback);
        }
    }

    public void requestErshouwupinXinjiuType(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestErshouwupinWupinType(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinXinjiuType(context, callback);
        }
    }

    public void requestErshouwupinPinpaiType(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestErshouwupinPinpaiType(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinPinpaiType(context, request,
                    callback);
        }
    }

    public void requestErshouwupinMine(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        if (DEBUG) {
             mLocalJson.requestErshouwupinMine(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinMine(context, request, callback);
        }
    }

    public void submitErshouwupindan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitErshouwupindan(context, request, callback);
    }
    
    public void submitErshouwupinCancelPublish(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        mNetSubmitJson.submitErshouwupinCancelPublish(context, request,
                callback);
    }
}
