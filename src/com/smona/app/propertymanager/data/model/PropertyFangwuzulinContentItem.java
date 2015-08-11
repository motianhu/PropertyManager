package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyFangwuzulinContentItem extends PropertyContentItem {
    public String publishid;
    public String choosetype;
    public String housecode;
    public String housename;
    public String areacode;
    public String areaname;
    public String housedesc;
    public String houseaddress;
    public String username;
    public String userphone;
    public String publishtime;
    public String customerid;
    public ArrayList<String> icobject;

    public PropertyFangwuzulinContentItem() {

    }

    public PropertyFangwuzulinContentItem(Parcel in) {
        publishid = in.readString();
        choosetype = in.readString();
        housecode = in.readString();
        housename = in.readString();
        areacode = in.readString();
        areaname = in.readString();
        housedesc = in.readString();
        houseaddress = in.readString();
        username = in.readString();
        userphone = in.readString();
        publishtime = in.readString();
        customerid = in.readString();

        icobject = new ArrayList<String>();
        in.readList(icobject, ClassLoader.getSystemClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publishid);
        dest.writeString(choosetype);
        dest.writeString(housecode);
        dest.writeString(housename);
        dest.writeString(areacode);
        dest.writeString(areaname);
        dest.writeString(housedesc);
        dest.writeString(houseaddress);
        dest.writeString(username);
        dest.writeString(userphone);
        dest.writeString(publishtime);
        dest.writeString(customerid);

        dest.writeList(icobject);
    }

    public String toString() {
        return "PropertyFangwuzulinContentItem[" + "publishid: " + publishid
                + ", userphone: " + userphone + ", username: " + username
                + ", housecode: " + housecode + ", areacode: " + areacode
                + ", icobject: " + icobject + "]";
    }

    public static final Parcelable.Creator<PropertyFangwuzulinContentItem> CREATOR = new Creator<PropertyFangwuzulinContentItem>() {
        @Override
        public PropertyFangwuzulinContentItem[] newArray(int size) {
            return new PropertyFangwuzulinContentItem[size];
        }

        @Override
        public PropertyFangwuzulinContentItem createFromParcel(Parcel in) {
            return new PropertyFangwuzulinContentItem(in);
        }
    };
}
