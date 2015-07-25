package com.smona.app.propertymanager.wupin.process;

import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;

import android.content.Context;

public class PropertyErshouwupinMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyErshouwupinNetRequestMessageProcess mLocalJson;
    private PropertyErshouwupinNetRequestMessageProcess mNetRequestJson;
    private PropertyErshouwupinNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyErshouwupinMessageProcessProxy() {
        mLocalJson = new PropertyErshouwupinNetRequestMessageProcess();
        mNetRequestJson = new PropertyErshouwupinNetRequestMessageProcess();
        mNetSubmitJson = new PropertyErshouwupinNetSubmitMessageProcess();
    }

    @Override
    public void requestErshouwupin(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestErshouwupin(context, callback);
        } else {
            mNetRequestJson.requestErshouwupin(context, callback);
        }
    }

    @Override
    public void requestErshouwupinDetail(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestErshouwupinDetail(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinDetail(context, callback);
        }
    }

    @Override
    public void requestErshouwupinWupinType(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestErshouwupinWupinType(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinWupinType(context, callback);
        }
    }
    

    @Override
    public void requestErshouwupinPinpaiType(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestErshouwupinPinpaiType(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinPinpaiType(context, callback);
        }
    }
    
    @Override
    public void requestErshouwupinMine(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestErshouwupinMine(context, callback);
        } else {
            mNetRequestJson.requestErshouwupinMine(context, callback);
        }
    }
    
    public void submitErshouwupindan(Context context, IQuestCallback callback) {
        mNetSubmitJson.submitErshouwupindan(context, callback);
    }
}
