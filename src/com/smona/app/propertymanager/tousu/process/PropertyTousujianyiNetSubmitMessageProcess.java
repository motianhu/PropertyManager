package com.smona.app.propertymanager.tousu.process;

import android.content.Context;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyTousujianyiNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    private static final String MSG_WUYEBAOXIU_SUBMIT = "3300";

    public void requestWuyebaoxiuSubmit(final IQuestCallback callback) {
        requestCommonPage(MSG_WUYEBAOXIU_SUBMIT, callback);
    }

    private void requestCommonPage(final String MSG_CODE,
            final IQuestCallback callback) {
        new Thread() {
            public void run() {
                JSONAuthenticationMessage message = new JSONAuthenticationMessage();
                message.setIccode(MSG_CODE);
                message.setSessionid(ConfigsInfo.sesssionId);
                message.setLoginname(ConfigsInfo.username);
                String msg = new Gson().toJson(message);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestWuyebaoxiu result " + result);
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

    public void submitTousujianyi(Context context, IQuestCallback callback) {
    }
}
