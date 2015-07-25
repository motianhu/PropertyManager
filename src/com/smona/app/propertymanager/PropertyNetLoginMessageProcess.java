package com.smona.app.propertymanager;

import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyNetLoginMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetLoginMessageProcess";

    public void login(final IQuestCallback callback) {
        String IP = "171.8.38.225";
        ConfigsInfo.FWURL = "http://" + IP + ":3715/AppCommDataHandler.ashx";
        // ConfigsInfo.FWURL =
        // "http://zhyj189.eicp.net:8080/CommDataSyncService/AppCommDataHandler.ashx";
        new Thread() {
            public void run() {
                boolean flag = new DoHttp().authentication();
                LogUtil.d(TAG, "login flag: " + flag);
                if (flag) {
                    String username = "18937113913";
                    String password = "123456";
                    String result = new DoHttp().doLogin(username, password);
                    LogUtil.d(TAG, "login result: " + result
                            + ", ConfigsInfo: " + ConfigsInfo.sesssionId);
                    callback.onResult("1".equals(result), "");
                    return;
                }
                callback.onResult(false, "failed");
            }
        }.start();

    }
}
