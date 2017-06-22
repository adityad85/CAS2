package com.rajul.cas;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;




/**
 * Created by Aditya on 6/14/2017.
 */

public class Students implements Parcelable {
    private String name, branch, section, contact, rollno, year;
    boolean attendState;
    protected Students(){

    }
    protected Students(Parcel in){

        name=in.readString();
        branch=in.readString();
        section=in.readString();
        contact=in.readString();
        rollno=in.readString();
        year=in.readString();
        attendState = Boolean.parseBoolean(in.readString());

    }
    public String getName(){
        return name;
    }
    public String getBranch(){
        return branch;
    }
    public String getSection()
    {
        return section;
    }

    public String getContact() {
        return contact;
    }

    public String getRollno() {
        return rollno;
    }

    public String getYear() {
        return year;
    }

    public void setName() {
        this.name = name;
    }

    public void setBranch() {
        this.branch = branch;
    }

    public void setAttendState(boolean attendState) {
        this.attendState = attendState;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public static final Creator<Students> CREATOR = new Creator<Students>() {
        @Override
        public Students createFromParcel(Parcel in) {
            return new Students(in);
        }

        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(branch);
        dest.writeString(section);
        dest.writeString(contact);
        dest.writeString(rollno);
        dest.writeString(year);
        dest.writeString(String.valueOf(attendState));
    }

    public boolean getAttendState() {
        return attendState;
    }


}