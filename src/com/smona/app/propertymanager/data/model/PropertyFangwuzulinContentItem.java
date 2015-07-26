package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyFangwuzulinContentItem extends PropertyContentItem {
    public String publishid;
    public String choosetype;
    public String housetype;
    public String housearea;
    public String housedesc;
    public String houseaddress;
    public String username;
    public String userphone;
    public String publishtime;
    public ArrayList<String> picurl;

    public PropertyFangwuzulinContentItem() {

    }

    public PropertyFangwuzulinContentItem(Parcel in) {
        publishid = in.readString();
        choosetype = in.readString();
        housetype = in.readString();
        housearea = in.readString();
        housedesc = in.readString();
        houseaddress = in.readString();
        username = in.readString();
        userphone = in.readString();
        publishtime = in.readString();

        picurl = new ArrayList<String>();
        in.readList(picurl, ClassLoader.getSystemClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publishid);
        dest.writeString(choosetype);
        dest.writeString(housetype);
        dest.writeString(housearea);
        dest.writeString(housedesc);
        dest.writeString(houseaddress);
        dest.writeString(username);
        dest.writeString(userphone);
        dest.writeString(publishtime);

        dest.writeList(picurl);
    }

    public String toString() {
        return "PropertyFangwuzulinContentItem[" + "publishid: " + publishid
                + ", userphone: " + userphone + ", username: " + username + "]";
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
