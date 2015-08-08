package com.smona.app.propertymanager.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class PropertyCommonHelper {

    private static ScreenInfo sScreenInfo;

    public static void initParams(Context context) {
        initScreenInfo(context);
    }

    private static void initScreenInfo(Context context) {
        sScreenInfo = new ScreenInfo(context);
    }

    public static ScreenInfo getSceenInfo() {
        return sScreenInfo;
    }

    public static class ScreenInfo {
        public int mScreenWidth;
        public int mScreenHeight;
        public int mScreenDpi;
        public float mScreenScale;

        public ScreenInfo(Context context) {
            initScreenInfo(context);
        }

        private void initScreenInfo(Context context) {
            DisplayMetrics display = context.getResources().getDisplayMetrics();
            mScreenWidth = display.widthPixels;
            mScreenHeight = display.heightPixels;
            mScreenDpi = display.densityDpi;
            mScreenScale = display.scaledDensity;
        }
    }

}
