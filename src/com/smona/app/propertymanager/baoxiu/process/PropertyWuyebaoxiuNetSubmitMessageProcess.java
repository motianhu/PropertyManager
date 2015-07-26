package com.smona.app.propertymanager.baoxiu.process;

import android.content.Context;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetSubmitMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyWuyebaoxiuNetSubmitMessageProcess extends
        PropertyNetSubmitMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    private static final String MSG_WUYEBAOXIU_SUBMIT = "3300";

    private void requestCommonPage(final String MSG_CODE,
            final PropertyRequestInfo request, final IQuestCallback callback) {
        new Thread() {
            public void run() {
                request.iccode = MSG_CODE;
                request.sessionid = ConfigsInfo.sesssionId;
                request.loginname = ConfigsInfo.username;
                String msg = new Gson().toJson(request);
                String result = new DoHttp().sendMsg(MSG_CODE, msg);
                LogUtil.d(TAG, "requestWuyebaoxiu result " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                    if (callback != null) {
                        callback.onResult(false, null);
                    }
                } else {
                    if (callback != null) {
                        callback.onResult(true, result);
                    }
                }
            }
        }.start();
    }

    public void submitWuyebaoxiudan(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommonPage(MSG_WUYEBAOXIU_SUBMIT, request, callback);
    }
}
