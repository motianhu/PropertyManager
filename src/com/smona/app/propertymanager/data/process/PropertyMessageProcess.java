package com.smona.app.propertymanager.data.process;

public abstract class PropertyMessageProcess {

    public interface IQuestCallback {
        void onResult(boolean success, String content);
    }
}
