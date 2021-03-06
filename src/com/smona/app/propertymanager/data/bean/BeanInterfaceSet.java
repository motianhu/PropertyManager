package com.smona.app.propertymanager.data.bean;

import android.content.ContentValues;
import android.content.Context;

public class BeanInterfaceSet {
    public interface IPersistence {
        void saveDataToDB(Context context);
    }

    public interface ICreateDBValues {
        ContentValues onCreateDBValues();
    }
}
