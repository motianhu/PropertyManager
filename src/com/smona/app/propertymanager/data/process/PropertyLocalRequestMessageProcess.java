package com.smona.app.propertymanager.data.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.text.TextUtils;

public class PropertyLocalRequestMessageProcess extends PropertyMessageProcess {

    // wuyebaoxiu
    public void requestWuyebaoxiu(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "wuyebaoxiu.txt"));
    }

    public void requestWuyebaoxiudan(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "wuyebaoxiudan.txt"));
    }

    public void requestWuyebaoxiudanDetail(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "wuyebaoxiudan_detail.txt"));
    }

    // tousujianyi
    public void requestTousujianyi(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "tousujianyi.txt"));
    }

    public void requestTousujianyidan(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "tousujianyidan.txt"));
    }

    public void requestTousujianyidanDetail(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "tousujianyidan_detail.txt"));
    }

    // wuyetongzhi
    public void requestWuyetongzhi(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "wuyetongzhi.txt"));
    }

    public void requestWuyetongzhiDetail(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "wuyetongzhi_detail.txt"));
    }

    // fangwuzulin
    public void requestFangwuzulin(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "fangwuzulin.txt"));
    }

    public void requestFangwuzulinDetail(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "fangwuzulin_detail.txt"));
    }

    public void requestFangwuzulinType(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "fangwuzulin_type.txt"));
    }

    public void requestFangwuzulinMine(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "fangwuzulin_mine.txt"));
    }

    // ershouwupin
    public void requestErshouwupin(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true, geFileFromAssets(context, "ershouwupin.txt"));
    }

    public void requestErshouwupinDetail(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "ershouwupin_detail.txt"));
    }

    public void requestErshouwupinWupinType(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "ershouwupin_wupin_type.txt"));
    }

    public void requestErshouwupinPinpaiType(Context context,
            IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "ershouwupin_pinpai_type.txt"));
    }

    public void requestErshouwupinMine(Context context, IQuestCallback callback) {
        if (callback == null) {
            return;
        }
        callback.onResult(true,
                geFileFromAssets(context, "ershouwupin_mine.txt"));
    }

    public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources()
                    .getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
