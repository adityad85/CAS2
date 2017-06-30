package com.rajul.cas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 6/28/2017.
 */

public class TeacherProfileAdapter extends RecyclerView.Adapter<com.rajul.cas.TeacherProfileAdapter.ViewHolder> {
    List<Teacher> teacherList;
    private Context context;

    public TeacherProfileAdapter(List<Teacher> teacherList, Context context) {
        super();
        this.teacherList = teacherList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.assigned_classes_listview_teacher, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    Teacher teacher = new Teacher();

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("asd1", "dsa1");
        teacher = teacherList.get(position);
        holder.sec1.setText(teacher.getSec());
        holder.sem1.setText(teacher.getSem());
        holder.sub1.setText(teacher.getSub());
        holder.bra1.setText(teacher.getBra());
        //holder.name.setText(teacher.getSub());
        Log.i("asd", "dsa");

    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sem1, sec1, sub1, bra1, name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("12qw", "123qw");
            //name=(TextView)itemView.findViewById(R.id.rollno);
            sem1 = (TextView) itemView.findViewById(R.id.sem1);
            sec1 = (TextView) itemView.findViewById(R.id.sec1);
            sub1 = (TextView) itemView.findViewById(R.id.sub1);
            bra1 = (TextView) itemView.findViewById(R.id.bra1);

        }
    }
}
