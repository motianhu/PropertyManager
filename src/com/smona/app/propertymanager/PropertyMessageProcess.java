package com.smona.app.propertymanager;

import android.content.Context;

import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.util.LogUtil;

public abstract class PropertyMessageProcess {
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
                    LogUtil.d(TAG, "login result: " + result
                            + ", ConfigsInfo: " + ConfigsInfo.sesssionId);
                }
            }
        }.start();

    }

    public interface IQuestCallback {
        void onResult(boolean success, String content);
    }

    public abstract void requestWuyebaoxiu(Context context,
            IQuestCallback callback);

    public abstract void requestWuyebaoxiudan(Context context,
            IQuestCallback callback);

    public abstract void requestWuyebaoxiudanDetail(Context context,
            IQuestCallback callback);

    // tousujianyi
    public abstract void requestTousujianyi(Context context,
            IQuestCallback callback);

    public abstract void requestTousujianyidan(Context context,
            IQuestCallback callback);

    public abstract void requestTousujianyidanDetail(Context context,
            IQuestCallback callback);

    // wuyetongzhi
    public abstract void requestWuyetongzhi(Context context,
            IQuestCallback callback);

    public abstract void requestWuyetongzhiDetail(Context context,
            IQuestCallback callback);

    // fangwuzulin
    public abstract void requestFangwuzulin(Context context,
            IQuestCallback callback);

    public abstract void requestFangwuzulinDetail(Context context,
            IQuestCallback callback);

    public abstract void requestFangwuzulinType(Context context,
            IQuestCallback callback);
    
    public abstract void requestFangwuzulinMine(Context context,
            IQuestCallback callback);

    // ershouwupin
    public abstract void requestErshouwupin(Context context,
            IQuestCallback callback);

    public abstract void requestErshouwupinDetail(Context context,
            IQuestCallback callback);

    public abstract void requestErshouwupinWupinType(Context context,
            IQuestCallback callback);

    public abstract void requestErshouwupinPinpaiType(Context context,
            IQuestCallback callback);
    
    public abstract void requestErshouwupinMine(Context context,
            IQuestCallback callback);

}
