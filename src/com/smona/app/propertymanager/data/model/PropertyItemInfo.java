package com.smona.app.propertymanager.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyItemInfo implements Parcelable {
    public String iccode;
    public String loginname;
    public String answercode;

    public PropertyItemInfo() {
    }

    public PropertyItemInfo(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<PropertyItemInfo> CREATOR = new Creator<PropertyItemInfo>() {
        @Override
        public PropertyItemInfo[] newArray(int size) {
            return new PropertyItemInfo[size];
        }

        @Override
        public PropertyItemInfo createFromParcel(Parcel in) {
            return new PropertyItemInfo(in);
        }
    };

    public String toString() {
        return "PropertyItemInfo[iccode: " + iccode + ", answercode: "
                + answercode + "]";
    }
}
