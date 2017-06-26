package com.rajul.cas;

/**
 * Created by Aditya on 6/26/2017.
 */

public class DataHolder {
    private Packet data = new Packet();

    public Packet getData() {
        return data;
    }

    public void setData(Packet data) {
        this.data = data;
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}
