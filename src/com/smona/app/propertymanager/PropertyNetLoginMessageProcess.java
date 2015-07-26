package com.smona.app.propertymanager;

import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public class PropertyNetLoginMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetLoginMessageProcess";

    public void login(final IQuestCallback callback) {

        ConfigsInfo.FWURL = PropertyConstants.URL;
        // ConfigsInfo.FWURL =
        // "http://zhyj189.eicp.net:8080/CommDataSyncService/AppCommDataHandler.ashx";
        new Thread() {
            public void run() {
                boolean flag = new DoHttp().authentication();
                LogUtil.d(TAG, "login flag: " + flag);
                if (flag) {
                    String result = new DoHttp().doLogin(
                            PropertyConstants.USERNAMRE,
                            PropertyConstants.PASSWORD);
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
