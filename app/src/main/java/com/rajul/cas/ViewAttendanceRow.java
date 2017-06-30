package com.rajul.cas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RAJUL on 6/23/2017.
 */

public class ViewAttendanceRow implements Parcelable {
    String name, lec, pre, abs;

    public ViewAttendanceRow() {

    }

    public ViewAttendanceRow(Parcel in) {

        name = in.readString();
        lec = in.readString();
        pre = in.readString();
        abs = in.readString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLec(String lec) {
        this.lec = lec;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getName() {
        return name;
    }

    public String getLec() {
        return lec;
    }

    public String getPre() {
        return pre;
    }

    public String getAbs() {
        return abs;
    }

    public static final Creator<ViewAttendanceRow> CREATOR = new Creator<ViewAttendanceRow>() {
        @Override
        public ViewAttendanceRow createFromParcel(Parcel in) {
            return new ViewAttendanceRow(in);
        }

        @Override
        public ViewAttendanceRow[] newArray(int size) {
            return new ViewAttendanceRow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lec);
        dest.writeString(pre);
        dest.writeString(abs);
    }

}
