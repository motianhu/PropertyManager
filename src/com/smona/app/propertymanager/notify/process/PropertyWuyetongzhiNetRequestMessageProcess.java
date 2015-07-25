package com.smona.app.propertymanager.notify.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Context;

public class PropertyWuyetongzhiNetRequestMessageProcess extends PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    // wuyebaoxiu
    private static final String MSG_WUYEBAOXIU_SHENGQING = "3200";
    private static final String MSG_WUYEBAOXIU_DAN = "3400";
    private static final String MSG_WUYEBAOXIU_DAN_DETAIL = "3500";

    public void requestWuyebaoxiuDan(final IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_DAN, callback);
    }

    public void requestFangwuzulin(final IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_DAN_DETAIL, callback);
    }

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
    
    @Override
    public void requestFangwuzulinDetail(Context context, IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_SHENGQING, callback);
    }

    @Override
    public void requestFangwuzulinType(Context context, IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_DAN, callback);
    }

    @Override
    public void requestFangwuzulinMine(Context context,
            IQuestCallback callback) {

    }
}
