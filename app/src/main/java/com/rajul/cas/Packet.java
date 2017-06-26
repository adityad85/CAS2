package com.rajul.cas;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aditya on 6/26/2017.
 */

public class Packet extends Activity {
    public static String branch, lecture, sem, sec, idt, date, sub;

    public Packet() {

    }


    public String getBranch() {
        return branch;
    }

    public String getLecture() {
        return lecture;
    }

    public String getSem() {
        return sem;
    }

    public String getSec() {
        return sec;
    }

    public String getIdt() {
        return idt;
    }

    public String getDate() {
        return date;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public void setSem(String Sem) {
        this.sem = Sem;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }


    public void setSubject(String Sub) {
        this.sub = Sub;

    }

    public String getSubject() {
        return sub;
    }
}
