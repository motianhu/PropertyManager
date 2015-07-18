package com.smona.app.propertymanager.baoxiu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.text.TextUtils;

public class PropertyWuyebaoxiuMessageProcess {

    public String getWuyebaoxiuContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiu.txt");
    }
    
    public String getWuyebaoxiudanContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiudan.txt");
    }
    
    public String getWuyebaoxiudan_detailContent(Context context) {
        return geFileFromAssets(context, "wuyebaoxiudan_detail.txt");
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
