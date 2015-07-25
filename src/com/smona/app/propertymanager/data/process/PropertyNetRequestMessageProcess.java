package com.smona.app.propertymanager.data.process;

import com.google.gson.Gson;
import com.jasonwang.informationhuimin.https.DoHttp;
import com.jasonwang.informationhuimin.json.resp.JSONAuthenticationMessage;
import com.jasonwang.informationhuimin.utils.ConfigsInfo;
import com.smona.app.propertymanager.util.LogUtil;

import android.content.Context;

public class PropertyNetRequestMessageProcess extends PropertyMessageProcess {
    private static final String TAG = "PropertyNetMessageProcess";

    // wuyebaoxiu
    private static final String MSG_WUYEBAOXIU_SHENGQING = "3200";
    private static final String MSG_WUYEBAOXIU_SUBMIT = "3300";
    private static final String MSG_WUYEBAOXIU_DAN = "3400";
    private static final String MSG_WUYEBAOXIU_DAN_DETAIL = "3500";

    public void requestWuyebaoxiuSubmit(final IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_SUBMIT, callback);
    }

    public void requestWuyebaoxiuDan(final IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_DAN, callback);
    }

    public void requestWuyebaoxiuDanDetail(final IQuestCallback callback) {
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

    @Override
    public void requestWuyebaoxiu(Context context, IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_SHENGQING, callback);
    }

    @Override
    public void requestWuyebaoxiudan(Context context, IQuestCallback callback) {
        requestCommon(MSG_WUYEBAOXIU_DAN, callback);
    }

    @Override
    public void requestWuyebaoxiudanDetail(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestTousujianyi(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestTousujianyidan(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestTousujianyidanDetail(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestWuyetongzhi(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestWuyetongzhiDetail(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestFangwuzulin(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestFangwuzulinDetail(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestFangwuzulinType(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestFangwuzulinMine(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestErshouwupin(Context context, IQuestCallback callback) {

    }

    @Override
    public void requestErshouwupinDetail(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestErshouwupinWupinType(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestErshouwupinPinpaiType(Context context,
            IQuestCallback callback) {

    }

    @Override
    public void requestErshouwupinMine(Context context, IQuestCallback callback) {

    }

}
