package com.smona.app.propertymanager.wupin.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Context;

public class PropertyErshouwupinNetRequestMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    // wuyebaoxiu
    private static final String MSG_ERSHOUWUPIN = "4700";
    private static final String MSG_ERSHOUWUPIN_XINJIU = "4800";
    private static final String MSG_ERSHOUWUPIN_PINPAI = "4900";
    private static final String MSG_ERSHOUWUPIN_MINE = "5200";

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
                LogUtil.d(TAG, "1requestCommon result " + result + ", msg: "
                        + msg);
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
                request.loginname = ConfigsInfo.username;
                request.sessionid = ConfigsInfo.sesssionId;
                String msg = new Gson().toJson(request);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestCommon result " + result + ", msg: "
                        + msg);
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

    public void requestErshouwupin(Context context,
            PropertyRequestInfo request, final IQuestCallback callback) {
        requestCommon(MSG_ERSHOUWUPIN, request, callback);
    }

    public void requestErshouwupinXinjiuType(Context context,
            IQuestCallback callback) {
        requestCommon(MSG_ERSHOUWUPIN_XINJIU, callback);
    }

    public void requestErshouwupinPinpaiType(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_ERSHOUWUPIN_PINPAI, request, callback);
    }

    public void requestErshouwupinMine(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_ERSHOUWUPIN_MINE, request, callback);
    }

}
