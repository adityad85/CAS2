package com.rajul.cas;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by RAJUL on 6/23/2017.
 */

public class ListAdapter extends ArrayAdapter<ViewAttendanceRow> {
    public ListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
