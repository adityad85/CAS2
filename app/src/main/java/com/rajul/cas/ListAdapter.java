package com.rajul.cas;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RAJUL on 6/23/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<com.rajul.cas.ListAdapter.ViewHolder> {

    List<ViewAttendanceRow> info;
    private Context context;

    public ListAdapter(List<ViewAttendanceRow> info, Context context) {
        this.info = info;
        this.context = context;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.view_attendance_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    ViewAttendanceRow infoitem;

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        infoitem = info.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name1, lec, pre, abs;

        public ViewHolder(View itemView) {
            super(itemView);
            name1 = (TextView) itemView.findViewById(R.id.)
        }
    }
}
