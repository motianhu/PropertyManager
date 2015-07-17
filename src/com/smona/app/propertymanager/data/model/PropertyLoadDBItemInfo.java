package com.smona.app.propertymanager.data.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class PropertyLoadDBItemInfo extends PropertyItemInfo {
    private QueryParams mQueryParam = new QueryParams();
    private LoadCallback mCallback = null;

    public int executeQuery(Context context) {
        if (mCallback == null) {
            return -1;
        }
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(mQueryParam.uri, mQueryParam.prjectons,
                mQueryParam.selction, mQueryParam.selectionArgs,
                mQueryParam.orderBy);
        if (cursor != null) {
            try {
                mCallback.processCursor(cursor);
                return cursor.getCount();
            } finally {
                cursor.close();
            }
        }
        return -1;
    }

    public void processCursor(Cursor cursor) {

    }

    public void setQueryParams(Uri uri, String[] prjectons, String selction,
            String[] selectionArgs, String orderBy) {
        mQueryParam.uri = uri;
        mQueryParam.prjectons = prjectons;
        mQueryParam.selction = selction;
        mQueryParam.selectionArgs = selectionArgs;
        mQueryParam.orderBy = orderBy;
    }

    public void setLoadCallback(LoadCallback callback) {
        mCallback = callback;
    }

    public class QueryParams {
        Uri uri;
        String[] prjectons;
        String selction;
        String[] selectionArgs;
        String orderBy;
    }

    public interface LoadCallback {
        void processCursor(Cursor cursor);
    }
}
