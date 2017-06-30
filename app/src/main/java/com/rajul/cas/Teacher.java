package com.rajul.cas;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Aditya on 6/28/2017.
 */

public class Teacher implements Parcelable {
    String Sem2, Bra2, Sec2, Sub2;

    public Teacher() {

    }

    public Teacher(Parcel in) {

        Sem2 = in.readString();
        Bra2 = in.readString();
        Sec2 = in.readString();
        Sub2 = in.readString();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public String getSem() {
        return Sem2;
    }

    public String getBra() {
        return Bra2;
    }

    public String getSec() {
        return Sec2;
    }

    public String getSub() {
        return Sub2;
    }

    public void setSec(String sec) {
        this.Sec2 = sec;
    }

    public void setSem(String sem) {
        this.Sem2 = sem;
    }

    public void setSub(String sub) {
        this.Sub2 = sub;
    }

    public void setBra(String bra) {
        this.Bra2 = bra;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Sem2);
        dest.writeString(Bra2);
        dest.writeString(Sec2);
        dest.writeString(Sub2);
    }
}
