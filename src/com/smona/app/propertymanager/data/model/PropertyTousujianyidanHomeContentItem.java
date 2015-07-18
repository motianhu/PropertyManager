package com.smona.app.propertymanager.data.model;

import java.util.List;

public class PropertyTousujianyidanHomeContentItem extends PropertyContentItem {
    public String pageno;
    public String pagesize;
    public String totalrecord;
    public List<PropertyTousujianyidanContentItem> icobjct;

    public String toString() {
        return "PropertyTousujianyidanHomeContentItem[pageno: " + pageno
                + "pagesize: " + pagesize + ", totalrecord: " + totalrecord
                + ", icobjct: " + icobjct + "]";
    }
}
