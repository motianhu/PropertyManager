package com.smona.app.propertymanager.data.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyWuyebaoxiudanContentItem extends PropertyContentItem {
    public String repairid;
    public String repairname;
    public String requesttime;
    public String repairstatus;
    public String starttime;
    public String repairresult;
    public String repairfare;
    public ArrayList<String> repairpicture;
    public String repairdesc;
    public String workbegintime;
    public String worker;
    public String workendtime;
    public ArrayList<PropertyWuyebaoxiudanEvaluteContentItem> evalute;

    public PropertyWuyebaoxiudanContentItem() {

    }

    public String toString() {
        return "PropertyWuyebaoxiudanContentItem[repairid: " + repairid
                + ", repairname: " + repairname + ", requesttime: "
                + requesttime + ", repairstatus: " + repairstatus
                + ", repairdesc: " + repairdesc + ", workbegintime: "
                + workbegintime + ", worker: " + worker + ", workendtime: "
                + workendtime + "]";
    }

    public PropertyWuyebaoxiudanContentItem(Parcel in) {
        repairid = in.readString();
        repairname = in.readString();
        requesttime = in.readString();
        repairstatus = in.readString();
        starttime = in.readString();
        repairresult = in.readString();
        repairfare = in.readString();

        repairpicture = new ArrayList<String>();
        in.readList(repairpicture, ClassLoader.getSystemClassLoader());

        repairdesc = in.readString();
        workbegintime = in.readString();
        worker = in.readString();
        workendtime = in.readString();

        evalute = new ArrayList<PropertyWuyebaoxiudanEvaluteContentItem>();
        in.readList(evalute, ClassLoader.getSystemClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repairid);
        dest.writeString(repairname);
        dest.writeString(requesttime);
        dest.writeString(repairstatus);
        dest.writeString(starttime);
        dest.writeString(repairresult);
        dest.writeString(repairfare);

        dest.writeList(repairpicture);

        dest.writeString(repairdesc);
        dest.writeString(workbegintime);
        dest.writeString(worker);
        dest.writeString(workendtime);

        dest.writeList(evalute);
    }

    public static final Parcelable.Creator<PropertyWuyebaoxiudanContentItem> CREATOR = new Creator<PropertyWuyebaoxiudanContentItem>() {
        @Override
        public PropertyWuyebaoxiudanContentItem[] newArray(int size) {
            return new PropertyWuyebaoxiudanContentItem[size];
        }

        @Override
        public PropertyWuyebaoxiudanContentItem createFromParcel(Parcel in) {
            return new PropertyWuyebaoxiudanContentItem(in);
        }
    };
}
