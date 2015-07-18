package com.smona.app.propertymanager.data.model;

import java.util.List;

public class PropertyWuyebaoxiudanHomeContentItem extends PropertyContentItem {
    public String pageno;
    public String pagesize;
    public String totalrecord;
    public List<PropertyWuyebaoxiudanContentItem> icobjct;

    public String toString() {
        return "PropertyWuyebaoxiudanHomeContentItem[pageno: " + pageno
                + "pagesize: " + pagesize + ", totalrecord: " + totalrecord
                + ", icobjct: " + icobjct + "]";
    }
}
