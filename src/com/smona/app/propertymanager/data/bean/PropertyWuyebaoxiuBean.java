package com.smona.app.propertymanager.data.bean;

import java.util.List;

import com.smona.app.propertymanager.data.bean.BeanInterfaceSet.IPersistence;

import android.content.ContentValues;
import android.content.Context;

public class PropertyWuyebaoxiuBean implements IPersistence {
    public Customer customer;
    public String msg;
    public List<IcObject> icobject;

    public String iccode;
    public String loginname;
    public String sessionid;
    public String answercode;

    public String toString() {
        return "PropertyWuyebaoxiuBean[customer" + customer + ", msg: " + msg
                + ", icobject: " + icobject + ", iccode: " + iccode
                + ", loginname: " + loginname + ", sessionid: " + sessionid
                + ", answercode: " + answercode + "]";
    }

    @Override
    public void saveDataToDB(Context context) {
        ContentValues values = new ContentValues();
        
    }
}
