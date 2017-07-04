package com.rajul.cas;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RAJUL on 6/23/2017.
 */

public class ViewAttendanceRow implements Parcelable {
    String name;
    Integer lec, pre, abs, per;

    public ViewAttendanceRow() {

    }

    public ViewAttendanceRow(Parcel in) {

        name = in.readString();
        lec = in.readInt();
        pre = in.readInt();
        abs = in.readInt();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPer() {
        this.per = ((pre * 100) / lec);
    }

    public void setLec() {
        this.lec = abs + pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    public void setAbs(Integer abs) {
        this.abs = abs;
    }

    public String getName() {
        return name;
    }

    public Integer getLec() {
        return lec;
    }

    public Integer getPre() {
        return pre;
    }

    public Integer getAbs() {
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

    public Integer getPer() {
        return per;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(lec);
        dest.writeInt(pre);
        dest.writeInt(abs);
    }

}
