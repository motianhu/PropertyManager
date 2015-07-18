package com.smona.app.propertymanager.baoxiu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.text.TextUtils;

public class PropertyWuyebaoxiuMessageProcess {

    // wuyebaoxiu
    public String getWuyebaoxiuContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiu.txt");
    }

    public String getWuyebaoxiudanContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiudan.txt");
    }

    public String getWuyebaoxiudan_detailContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiudan_detail.txt");
    }

    // tousujianyi
    public String getTousujianyi(Context context) {
        return geFileFromAssets(context, "tousujianyi.txt");
    }

    public String getTousujianyidanContent(Context context) {
        return geFileFromAssets(context, "tousujianyidan.txt");
    }

    public String getTousujianyidan_detailContent(Context context) {
        return geFileFromAssets(context, "tousujianyidan_detail.txt");
    }
    
    // wuyetongzhi
    public String getWuyetongzhiContent(Context context) {
        return geFileFromAssets(context, "wuyetongzhi.txt");
    }
    
    public String getWuyetongzhi_detailContent(Context context) {
        return geFileFromAssets(context, "wuyetongzhi_detail.txt");
    }
    
    // fangwuzulin
    public String getFangwuzulinContent(Context context) {
        return geFileFromAssets(context, "fangwuzulin.txt");
    }
    
    public String getFangwuzulin_detailContent(Context context) {
        return geFileFromAssets(context, "fangwuzulin_detail.txt");
    }
    
    public String getFangwuzulin_typeContent(Context context) {
        return geFileFromAssets(context, "fangwuzulin_type.txt");
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
            return null;
        }
    }
}
