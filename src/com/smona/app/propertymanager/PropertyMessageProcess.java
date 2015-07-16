package com.smona.app.propertymanager;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.util.LogUtil;

public class PropertyMessageProcess {
    private static final String TAG = "Login";

    public void login() {
        ConfigsInfo.FWURL = "http://123.149.84.133:3715/AppCommDataHandler.ashx";
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
                    LogUtil.d(TAG, "login result: " + result + ", ConfigsInfo: " + ConfigsInfo.sesssionId);
                }
            }
        }.start();

    }

    private boolean authentication() {
        JSONAuthenticationMessage message = new JSONAuthenticationMessage();
        message.setIccode("0000");
        message.setLoginname(ConfigsInfo.JQM);
        message.setSessionid("");
        message.setAnswercode("");
        message.setCurrappver(ConfigsInfo.VERSION);
        String msg = new Gson().toJson(message);
        String result = new DoHttp().sendMsg("0000", msg);
        LogUtil.d(TAG, "authentication result " + result);
        if (result.equals("0") || result.equals("1") || result.equals("2")
                || result.equals("3") || result.equals("4")
                || result.equals("5") || result.equals("6")
                || result.equals("7")) {
            return false;
        } else {
            JSONAuthenticationMessage json = new Gson().fromJson(result,
                    JSONAuthenticationMessage.class);
            if (json == null) {
                return false;
            }
            String answercode = json.getAnswercode().toString();
            LogUtil.d(TAG, "authentication answercode " + answercode);
            if (answercode.equals("00")) {
                String key = json.getWorkkey();
                ConfigsInfo.updateKey(key);
                ConfigsInfo.sesssionId = json.getSessionid();
                ConfigsInfo.username = json.getLoginname();
                return true;
            }
        }
        return false;
    }

    public void requestWuyebaoxiu() {
        new Thread() {
            public void run() {
                JSONAuthenticationMessage message = new JSONAuthenticationMessage();
                message.setIccode("3200");
                message.setSessionid(ConfigsInfo.sesssionId);
                message.setLoginname(ConfigsInfo.username);
                String msg = new Gson().toJson(message);
                String result = new DoHttp().sendMsg("3200", msg);
                LogUtil.d(TAG, "requestWuyebaoxiu result " + result);
                if (result.equals("0") || result.equals("1")
                        || result.equals("2") || result.equals("3")
                        || result.equals("4") || result.equals("5")
                        || result.equals("6") || result.equals("7")) {
                } else {
                    JSONAuthenticationMessage json = new Gson().fromJson(
                            result, JSONAuthenticationMessage.class);
                    if (json == null) {
                        return;
                    }
                    LogUtil.d(TAG, "json: " + json);
                    String answercode = json.getAnswercode().toString();
                    LogUtil.d(TAG, "answercode: " + answercode);
                }
            }
        }.start();
    }
}
