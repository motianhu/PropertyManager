package com.smona.app.propertymanager.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyWuyetongzhiContentItem extends PropertyContentItem {

    public String publishid;
    public String title;
    public String notice;
    public String publishtime;
    public String noticestatus;

    public String toString() {
        return "PropertyWuyetongzhiContentItem[publishid: " + publishid
                + ",title: " + title + ",notice: " + notice + ",publishtime: "
                + publishtime + ",noticestatus: " + noticestatus + "]";
    }

    public PropertyWuyetongzhiContentItem() {

    }

    public PropertyWuyetongzhiContentItem(Parcel in) {
        publishid = in.readString();
        title = in.readString();
        notice = in.readString();
        publishtime = in.readString();
        noticestatus = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publishid);
        dest.writeString(title);
        dest.writeString(notice);
        dest.writeString(publishtime);
        dest.writeString(noticestatus);
    }

    public static final Parcelable.Creator<PropertyWuyetongzhiContentItem> CREATOR = new Creator<PropertyWuyetongzhiContentItem>() {
        @Override
        public PropertyWuyetongzhiContentItem[] newArray(int size) {
            return new PropertyWuyetongzhiContentItem[size];
        }

        @Override
        public PropertyWuyetongzhiContentItem createFromParcel(Parcel in) {
            return new PropertyWuyetongzhiContentItem(in);
        }
    };
}
