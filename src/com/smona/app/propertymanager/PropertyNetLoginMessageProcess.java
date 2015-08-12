package com.smona.app.propertymanager;

import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.data.process.PropertyNetRequestMessageProcess;
import com.smona.app.propertymanager.util.LogUtil;
import com.smona.app.propertymanager.util.PropertyConstants;

public class PropertyNetLoginMessageProcess extends
        PropertyNetRequestMessageProcess {
    private static final String TAG = "PropertyNetLoginMessageProcess";

    public void login(final IQuestCallback callback, String ip, String port,
            final String username, final String passwd) {

        PropertyConstants.HTTP_IP_PORT = PropertyConstants.HTTP + ip + ":"
                + port;

        ConfigsInfo.FWURL = PropertyConstants.HTTP_IP_PORT
                + PropertyConstants.URL_SUFFIX;
        new Thread() {
            public void run() {
                boolean flag = new DoHttp().authentication();
                LogUtil.d(TAG, "login flag: " + flag + ", username: "
                        + username + ", passwd: " + passwd);
                if (flag) {
                    String result = new DoHttp().doLogin(username, passwd);
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
