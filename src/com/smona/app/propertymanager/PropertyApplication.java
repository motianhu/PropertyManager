package com.smona.app.propertymanager;

import java.lang.ref.WeakReference;

import com.smona.app.propertymanager.data.db.PropertyProvider;
import com.smona.app.propertymanager.imageload.ImageLoaderManager;
import com.smona.app.propertymanager.util.PropertyCommonHelper;
import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;

public class PropertyApplication extends Application {
    private WeakReference<PropertyProvider> mProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(this, "900009186", false);
        ImageLoaderManager.getInstance().initImageLoader(this);
        PropertyCommonHelper.initParams(this);
    }

    public void setPropertyProvider(PropertyProvider provider) {
        mProvider = new WeakReference<PropertyProvider>(provider);
    }

    public PropertyProvider getPropertyProvider() {
        return mProvider.get();
    }
}
