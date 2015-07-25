package com.smona.app.propertymanager.baoxiu.process;

import com.smona.app.propertymanager.data.process.PropertyMessageProcessProxy;

import android.content.Context;

public class PropertyWuyebaoxiuMessageProcessProxy extends
        PropertyMessageProcessProxy {
    private PropertyWuyebaoxiuNetRequestMessageProcess mLocalJson;
    private PropertyWuyebaoxiuNetRequestMessageProcess mNetRequestJson;
    private PropertyWuyebaoxiuNetSubmitMessageProcess mNetSubmitJson;

    private static final boolean DEBUG = false;

    public PropertyWuyebaoxiuMessageProcessProxy() {
        mLocalJson = new PropertyWuyebaoxiuNetRequestMessageProcess();
        mNetRequestJson = new PropertyWuyebaoxiuNetRequestMessageProcess();
        mNetSubmitJson = new PropertyWuyebaoxiuNetSubmitMessageProcess();
    }

    @Override
    public void requestWuyebaoxiu(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestWuyebaoxiu(context, callback);
        } else {
            mNetRequestJson.requestWuyebaoxiu(context, callback);
        }
    }

    @Override
    public void requestWuyebaoxiudan(Context context, IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestWuyebaoxiudan(context, callback);
        } else {
            mNetRequestJson.requestWuyebaoxiudan(context, callback);
        }
    }

    @Override
    public void requestWuyebaoxiudanDetail(Context context,
            IQuestCallback callback) {
        if (DEBUG) {
            mLocalJson.requestWuyebaoxiudanDetail(context, callback);
        } else {
            mNetRequestJson.requestWuyebaoxiudanDetail(context, callback);
        }
    }

    public void submitWuyebaoxiudan(Context context, IQuestCallback callback) {
        mNetSubmitJson.submitWuyebaoxiudan(context, callback);
    }
}
