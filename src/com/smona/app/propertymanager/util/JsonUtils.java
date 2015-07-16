package com.smona.app.propertymanager.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
    private static final String TAG = "JsonUtil";
    private static Gson sGson = new Gson();

    private JsonUtils() {
        throw new AssertionError();
    }

    public static <X> X parseJson(String jsonData, Type type) {
        X result = null;
        try {
            result = sGson.fromJson(jsonData, type);
        } catch (JsonSyntaxException e) {
            LogUtil.d(TAG, "Gson parseJson: " + e.toString());
            e.printStackTrace();
        }
        return result;
    }
}
