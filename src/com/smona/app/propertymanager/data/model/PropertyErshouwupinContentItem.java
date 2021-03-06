package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyErshouwupinContentItem extends PropertyContentItem {
    public String publishid;
    public String brand;
    public String brandcode;
    public String classname;
    public String classcode;
    public String goodsname;
    public String goodsdesc;
    public String goodscode;
    public String goosstatus;
    public ArrayList<String> picurl;
    public String username;
    public String userphone;
    public String publishtime;
    public String publishstatus;
    public String customerid;

    public PropertyErshouwupinContentItem() {

    }

    public PropertyErshouwupinContentItem(Parcel in) {
        publishid = in.readString();
        brand = in.readString();
        brandcode = in.readString();
        classname = in.readString();
        classcode = in.readString();
        goodsname = in.readString();
        goodsdesc = in.readString();
        goodscode = in.readString();
        goosstatus = in.readString();
        username = in.readString();
        userphone = in.readString();
        publishtime = in.readString();
        publishstatus = in.readString();
        customerid = in.readString();

        picurl = new ArrayList<String>();
        in.readList(picurl, ClassLoader.getSystemClassLoader());
    }

    public void cloneData(PropertyErshouwupinContentItem modifyItem) {
        brand = ((PropertyErshouwupinContentItem) modifyItem).brand;
        brandcode = ((PropertyErshouwupinContentItem) modifyItem).brandcode;
        classname = ((PropertyErshouwupinContentItem) modifyItem).classname;
        classcode = ((PropertyErshouwupinContentItem) modifyItem).classcode;
        goodsname = ((PropertyErshouwupinContentItem) modifyItem).goodsname;
        goodsdesc = ((PropertyErshouwupinContentItem) modifyItem).goodsdesc;
        goodscode = ((PropertyErshouwupinContentItem) modifyItem).goodscode;
        goosstatus = ((PropertyErshouwupinContentItem) modifyItem).goosstatus;
        username = ((PropertyErshouwupinContentItem) modifyItem).username;
        userphone = ((PropertyErshouwupinContentItem) modifyItem).userphone;
        publishtime = ((PropertyErshouwupinContentItem) modifyItem).publishtime;
        publishstatus = ((PropertyErshouwupinContentItem) modifyItem).publishstatus;

        picurl.clear();
        picurl.addAll(((PropertyErshouwupinContentItem) modifyItem).picurl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publishid);
        dest.writeString(brand);
        dest.writeString(brandcode);
        dest.writeString(classname);
        dest.writeString(classcode);
        dest.writeString(goodsname);
        dest.writeString(goodsdesc);
        dest.writeString(goodscode);
        dest.writeString(goosstatus);
        dest.writeString(username);
        dest.writeString(userphone);
        dest.writeString(publishtime);
        dest.writeString(publishstatus);
        dest.writeString(customerid);

        dest.writeList(picurl);
    }

    public static final Parcelable.Creator<PropertyErshouwupinContentItem> CREATOR = new Creator<PropertyErshouwupinContentItem>() {
        @Override
        public PropertyErshouwupinContentItem[] newArray(int size) {
            return new PropertyErshouwupinContentItem[size];
        }

        @Override
        public PropertyErshouwupinContentItem createFromParcel(Parcel in) {
            return new PropertyErshouwupinContentItem(in);
        }
    };

    public String toString() {
        return "PropertyErshouwupinContentItem[publishid: " + publishid
                + ",brand: " + brand + ",brandcode: " + brandcode
                + ",classname: " + classname + ",classcode: " + classcode
                + ", goodscode: " + goodscode + ", goosstatus: " + goosstatus
                + ",username: " + username + ",goodsname: " + goodsname
                + ", goodsdesc: " + goodsdesc + ", userphone: " + userphone
                + ",publishtime: " + publishtime + ", publishstatus: "
                + publishstatus + "]";
    }
}