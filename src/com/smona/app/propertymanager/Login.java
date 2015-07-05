package com.smona.app.propertymanager;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.app.Activity;
import android.os.Bundle;

public class Login extends Activity {
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigsInfo.FWURL = "http://zhyj189.eicp.net:3710/CommDataSyncService/AppCommDataHandler.ashx";
        new Thread() {
            public void run() {

                boolean flag = new DoHttp().authentication();
                LogUtil.d(TAG,  "flag: " + flag);
                if (flag) {
                    String username = "020148888";
                    String password = "888888";
                    String result = new DoHttp().doLogin(username, password);
                    LogUtil.d(TAG,  "result: " + result);
                    authentication();
                }
            }
        }.start();

    }
    
    public boolean authentication() {
        boolean flag = false;
        JSONAuthenticationMessage message = new JSONAuthenticationMessage();

        message.setIccode("0000");
        message.setLoginname(ConfigsInfo.JQM);
        message.setSessionid("");
        message.setAnswercode("");
        message.setCurrappver(ConfigsInfo.VERSION);

        String msg = new Gson().toJson(message);

        String result = new DoHttp().sendMsg("0000", msg);

        System.out.println("result " + result);

        if (result.equals("0") || result.equals("1") || result.equals("2")
                || result.equals("3") || result.equals("4")
                || result.equals("5") || result.equals("6")
                || result.equals("7")) {

        } else {
            JSONAuthenticationMessage json = new Gson().fromJson(result,
                    JSONAuthenticationMessage.class);

            if (json != null) {
                String answercode = json.getAnswercode().toString();
                System.out.println("answercode " + answercode);
                if (answercode.equals("00")) {

                    String key = json.getWorkkey();
                    ConfigsInfo.updateKey(key);
                    ConfigsInfo.sesssionId = json.getSessionid();

                    flag = true;

                } else {

                }
            } else {

            }
        }
        return flag;
    }
}
