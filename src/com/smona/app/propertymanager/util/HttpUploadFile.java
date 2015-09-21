package com.smona.app.propertymanager.util;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpUploadFile {
    public static void submitPost(String url, String filePath,
            RequestCallBack<?> callback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
        uploadMethod(params, url, callback);
    }

    public static void uploadMethod(RequestParams params, String uploadHost,
            RequestCallBack<?> callback) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, uploadHost, params, callback);
    }
}
