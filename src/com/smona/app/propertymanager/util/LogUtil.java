package com.smona.app.propertymanager.util;

import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.util.Log;

public class LogUtil {
    public static final boolean DEBUG = true;

    private static final String MODULE_NAME = "Moth";
    private static final LogUtil INSTANCE = new LogUtil();

    private LogUtil() {
    }

    public static LogUtil getInstance() {
        return INSTANCE;
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(MODULE_NAME, tag + ", " + msg);
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (DEBUG) {
            Log.e(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(MODULE_NAME, tag + ", " + msg);
        }
    }

    public static void w(String tag, String msg, Throwable t) {
        if (DEBUG) {
            Log.w(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(MODULE_NAME, tag + ", " + msg);
        }
    }

    public static void i(String tag, String msg, Throwable t) {
        if (DEBUG) {
            Log.i(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.e(MODULE_NAME, tag + ", " + msg);
        }
    }

    public static void d(String tag, String msg, Throwable t) {
        if (DEBUG) {
            Log.d(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(MODULE_NAME, tag + ", " + msg);
        }
    }

    public static void v(String tag, String msg, Throwable t) {
        if (DEBUG) {
            Log.v(MODULE_NAME, tag + ", " + msg, t);
        }
    }

    public static void printTrace(String tag) {
        if (DEBUG) {
            Log.v(MODULE_NAME, tag + ", Trace start");
            Thread.dumpStack();
            Log.v(MODULE_NAME, tag + ", Trace end");
        }
    }

    public static void saveBitmap(Bitmap bitmap, String path) {
        if (DEBUG) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(path);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void printData(String tag, Object obj) {
        if (DEBUG) {
            Log.v(MODULE_NAME, tag + ": PrintData-----------" + obj);
        }
    }
}
