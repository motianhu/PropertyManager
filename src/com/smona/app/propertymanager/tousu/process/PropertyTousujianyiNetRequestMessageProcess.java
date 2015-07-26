package com.smona.app.propertymanager.tousu.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Context;

public class PropertyTousujianyiNetRequestMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    // wuyebaoxiu
    private static final String MSG_TOUSUJINAYI = "3700";
    private static final String MSG_TOUSUJINAYI_DAN = "3900";
    private static final String MSG_TOUSUJINAYI_DAN_DETAIL = "4000";

    private void requestCommon(final String MSG_CODE,
            final IQuestCallback callback) {
        new Thread() {
            public void run() {
                JSONAuthenticationMessage message = new JSONAuthenticationMessage();
                message.setIccode(MSG_CODE);
                message.setSessionid(ConfigsInfo.sesssionId);
                message.setLoginname(ConfigsInfo.username);
                String msg = new Gson().toJson(message);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestTousujianyi result " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    callback.onResult(false, null);
                } else {
                    callback.onResult(true, result);
                }
            }
        }.start();
    }

    private void requestCommon(final String MSG_CODE,
            final PropertyRequestInfo request, final IQuestCallback callback) {
        new Thread() {
            public void run() {
                request.iccode = MSG_CODE;
                request.sessionid = ConfigsInfo.sesssionId;
                request.loginname = ConfigsInfo.username;

                String msg = new Gson().toJson(request);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestTousujianyi result " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    callback.onResult(false, null);
                } else {
                    callback.onResult(true, result);
                }
            }
        }.start();
    }

    public void requestTousujianyi(Context context, IQuestCallback callback) {
        requestCommon(MSG_TOUSUJINAYI, callback);
    }

    public void requestTousujianyidan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_TOUSUJINAYI_DAN, request, callback);
    }

    public void requestTousujianyidanDetail(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_TOUSUJINAYI_DAN_DETAIL, request, callback);
    }
}
