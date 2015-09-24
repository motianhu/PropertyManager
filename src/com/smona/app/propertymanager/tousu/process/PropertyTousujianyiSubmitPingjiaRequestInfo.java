package com.smona.app.propertymanager.tousu.process;

import java.util.ArrayList;

import com.smona.app.propertymanager.baoxiu.process.PropertyPingjiaRequestInfo;
import com.smona.app.propertymanager.data.process.PropertyRequestInfo;

public class PropertyTousujianyiSubmitPingjiaRequestInfo extends
        PropertyRequestInfo {
    public String complaintid;
    public ArrayList<PropertyPingjiaRequestInfo> eval = new ArrayList<PropertyPingjiaRequestInfo>();

    public void add(String evlCode, int num) {
        PropertyPingjiaRequestInfo info = new PropertyPingjiaRequestInfo();
        info.evalcode = evlCode;
        info.evalvalue = num + "";
        eval.add(info);
    }
}
