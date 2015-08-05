package com.smona.app.propertymanager.util;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;

import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpUploadFile {
    private static final String TAG = "HttpUploadFile";

    public static void submitPost(String url, String filePath,
            WeakReference<View> view) {
        RequestParams params = new RequestParams();
        params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
        uploadMethod(params, url, view);
    }

    public static void uploadMethod(final RequestParams params,
            final String uploadHost, final WeakReference<View> view) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, uploadHost, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onLoading(long total, long current,
                            boolean isUploading) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        LogUtil.d(TAG, "responseInfo: " + responseInfo.result);
                        String content = responseInfo.result;
                        Type type = new TypeToken<UploadInfo>() {
                        }.getType();
                        UploadInfo bean = JsonUtils.parseJson(content, type);
                        if ("SUCCESS".equalsIgnoreCase(bean.status)
                                && view.get() != null) {
                            view.get().setTag(bean.bigfilename);
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }
}
