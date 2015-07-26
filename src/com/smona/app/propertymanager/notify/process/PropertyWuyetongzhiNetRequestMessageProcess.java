package com.smona.app.propertymanager.notify.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Context;

public class PropertyWuyetongzhiNetRequestMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyWuyetongzhiNetRequestMessageProcess";

    // wuyebaoxiu
    private static final String MSG_WUYETONGZHI = "5300";
    private static final String MSG_WUYETONGZHI_DETAIL = "5400";

    private void requestCommon(final String MSG_CODE,
            final PropertyRequestInfo request, final IQuestCallback callback) {
        new Thread() {
            public void run() {
                request.sessionid = ConfigsInfo.sesssionId;
                request.loginname = ConfigsInfo.username;
                String msg = new Gson().toJson(request);
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

    public void requestWuyetongzhiDetail(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_WUYETONGZHI_DETAIL, request, callback);
    }

    public void requestWuyetongzhi(Context context,
            PropertyRequestInfo request, IQuestCallback callback) {
        requestCommon(MSG_WUYETONGZHI, request, callback);
    }
}
