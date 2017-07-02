package com.rajul.cas;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * Created by Aditya on 6/26/2017.
 */

public class Packet extends Activity {
    public static String branch, lecture, sem, sec, idt, sub, year, ids;
    public static LocalDate date;
    public Packet() {

    }

    public String getIds() {
        return ids;
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

    public LocalDate getDate() {
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

    public void setDate(LocalDate a) {
        this.date = a;
    }

    public void setSubject(String Sub) {
        this.sub = Sub;

    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    public String getYear() {
        if (sem == "1" || sem == "2") {
            return "1";
        } else if (sem == "3" || sem == "4") {
            return "2";
        } else if (sem == "5" || sem == "6") {
            return "3";
        } else if (sem == "7" || sem == "8") {
            return "4";
        }
        return "1";
    }
    public String getSubject() {
        return sub;
    }
}
