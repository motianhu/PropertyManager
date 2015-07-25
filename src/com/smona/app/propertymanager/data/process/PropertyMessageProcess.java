package com.smona.app.propertymanager.data.process;

import android.content.Context;

public abstract class PropertyMessageProcess {

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
