package com.smona.app.propertymanager.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.smona.app.propertymanager.util.LogUtil;

public class HttpUploadFile {
    private static final String TAG = "HttpUploadFile";

    public void SubmitPost(String url, ArrayList<String> files) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder multi = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            int size = files.size();
            for (int i = 0; i < size; i++) {
                multi.addPart("files", new FileBody(new File(files.get(i))));
            }

            HttpEntity req = multi.build();
            httppost.setEntity(req);
            response = httpclient.execute(httppost);

            HttpEntity re = response.getEntity();
            LogUtil.d(TAG, "getStatusLine: " + response.getStatusLine());
            LogUtil.d(TAG, "HttpEntity re: " + re);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "ClientProtocolException error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "IOException error: " + e);
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
