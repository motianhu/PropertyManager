package com.smona.app.propertymanager.util;

import java.io.File;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpUploadFile {
    private static final String TAG = "HttpUploadFile";
    
    public static void submitPost(String url, String filePath) {
        RequestParams params = new RequestParams();
        params.addBodyParameter(filePath.replace("/", ""), new File(filePath));
        uploadMethod(params, url);
    }

    public static void uploadMethod(final RequestParams params,
            final String uploadHost) {
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
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    public interface UploadCallback {
        
    }
}
