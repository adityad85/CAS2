package com.rajul.cas;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RAJUL on 6/23/2017.
 */

class ListAdapterStudent extends RecyclerView.Adapter<com.rajul.cas.ListAdapterStudent.ViewHolder> {

    List<ViewAttendanceRow> info;
    private Context context;

    public ListAdapterStudent(List<ViewAttendanceRow> info, Context context) {
        super();
        this.info = info;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.view_attendance_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    ViewAttendanceRow infoitem;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        infoitem = info.get(position);
        holder.name1.setText(infoitem.getName());
        holder.abs.setText(infoitem.getAbs().toString());
        holder.pre.setText(infoitem.getPre().toString());
        holder.lec.setText(infoitem.getLec().toString());
        holder.per.setText(infoitem.getPer().toString() + '%');
        Log.i("kk", infoitem.getPer().toString());

    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name1, lec, pre, abs, per;

        public ViewHolder(View itemView) {
            super(itemView);
            name1 = (TextView) itemView.findViewById(R.id.sname);
            lec = (TextView) itemView.findViewById(R.id.lectureCountValue);
            pre = (TextView) itemView.findViewById(R.id.presentCountValue);
            abs = (TextView) itemView.findViewById(R.id.absentCountValue);
            per = (TextView) itemView.findViewById(R.id.percentHere);
        }
    }
}
