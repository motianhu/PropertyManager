package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyTousujianyidanContentItem extends PropertyContentItem {
    public String complaint;
    public String complaintid;
    public String complaintcode;
    public String complaintname;
    public ArrayList<String> complaintpicture;
    public String complaintdesc;
    public String requesttime;
    public String feedback;
    public String feedbacktime;
    public ArrayList<PropertyWuyebaoxiudanEvaluteContentItem> evalute;

    public PropertyTousujianyidanContentItem() {

    }

    public PropertyTousujianyidanContentItem(Parcel in) {
        complaint = in.readString();
        complaintid = in.readString();
        complaintcode = in.readString();
        complaintname = in.readString();

        complaintpicture = new ArrayList<String>();
        in.readList(complaintpicture, ClassLoader.getSystemClassLoader());

        complaintdesc = in.readString();
        requesttime = in.readString();
        feedback = in.readString();
        feedbacktime = in.readString();

        evalute = new ArrayList<PropertyWuyebaoxiudanEvaluteContentItem>();
        in.readList(evalute, ClassLoader.getSystemClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(complaint);
        dest.writeString(complaintid);
        dest.writeString(complaintcode);
        dest.writeString(complaintname);

        dest.writeList(complaintpicture);

        dest.writeString(complaintdesc);
        dest.writeString(requesttime);
        dest.writeString(feedback);
        dest.writeString(feedbacktime);

        dest.writeList(evalute);
    }

    public static final Parcelable.Creator<PropertyTousujianyidanContentItem> CREATOR = new Creator<PropertyTousujianyidanContentItem>() {
        @Override
        public PropertyTousujianyidanContentItem[] newArray(int size) {
            return new PropertyTousujianyidanContentItem[size];
        }

        @Override
        public PropertyTousujianyidanContentItem createFromParcel(Parcel in) {
            return new PropertyTousujianyidanContentItem(in);
        }
    };
}
