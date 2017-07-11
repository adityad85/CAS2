package com.rajul.cas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.rajul.cas.R;
import com.rajul.cas.Students;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Aditya on 6/19/2017.
 */

public class AttendanceUploadAdapter extends RecyclerView.Adapter<com.rajul.cas.AttendanceUploadAdapter.ViewHolder> {
    List<Students> students;
    private Context context;
    public Packet packet = new Packet();
    String id;


    public AttendanceUploadAdapter(List<Students> students, Context context) {
        super();

        this.students = students;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.attendance_row_upload, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        Log.i("haha", "yeag");

        return viewHolder;
    }

    public String qw;
    Students students1;
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        students1 = students.get(position);
        holder.textViewRollNo.setText(students1.getRollno());
        id = students1.getRollno();
        Log.i("roooo",students1.getRollno());
        holder.toggleButtonAttend.setSelected(students1.getAttendState());
        Log.i("got", "in");
        ParseQuery<ParseObject> a = new ParseQuery<ParseObject>("attendance_" + packet.getYear());
        a.whereContains("Lecture_id", packet.getLecture());
        a.whereContains("subject", packet.getSubject());
        a.whereContains("section", packet.getSec());
        a.whereContains("branch", packet.getBranch());
        a.whereEqualTo("date", packet.getDate().toString());
        a.whereEqualTo("student_id", id);
        try {
            if (a.find().size() > 0) {
                Log.i("cec", "qwqw");
                Log.i("output", String.valueOf(a.find().get(0).getBoolean("present")));
                holder.toggleButtonAttend.setChecked(a.find().get(0).getBoolean("present"));
            } else {
                Log.i("asd", "asd");
                obj = new ParseObject("attendance_" + packet.getYear());
                obj.put("student_id", id);
                obj.put("present", holder.toggleButtonAttend.isChecked());
                obj.put("Lecture_id", packet.getLecture());
                obj.put("subject", packet.getSubject());
                obj.put("section", packet.getSec());
                obj.put("branch", packet.getBranch());
                obj.put("date", packet.getDate().toString());
                obj.put("sem",packet.getSem());
                obj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.i("ee", "dds");
                            e.printStackTrace();
                        }

                    }
                });

            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        /*a.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    Log.i("cec", "qwqw");

                    Log.i("output", String.valueOf(objects.get(0).getBoolean("present")));
                    holder.toggleButtonAttend.setChecked(objects.get(0).getBoolean("present"));
                } else {
                    Log.i("asd", "asd");
                    obj = new ParseObject("attendance_" + packet.getYear());
                    obj.put("student_id", id);
                    obj.put("present", holder.toggleButtonAttend.isChecked());
                    obj.put("Lecture_id", packet.getLecture());
                    obj.put("subject", packet.getSubject());
                    obj.put("section", packet.getSec());
                    obj.put("branch", packet.getBranch());
                    obj.put("date", packet.getDate().toString());
                    obj.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.i("ee", "dds");
                                e.printStackTrace();
                            }

                        }
                    });

                }
            }
        });
*/


    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public ParseObject obj;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRollNo;
        public ToggleButton toggleButtonAttend;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRollNo = (TextView) itemView.findViewById(R.id.rollno);
            toggleButtonAttend = (ToggleButton) itemView.findViewById(R.id.attend);
            toggleButtonAttend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("attendance_" + packet.getYear());
                    query.whereEqualTo("student_id", textViewRollNo.getText().toString());
                    query.whereContains("Lecture_id", packet.getLecture());
                    query.whereContains("subject", packet.getSubject());
                    query.whereContains("section", packet.getSec());
                    query.whereContains("branch", packet.getBranch());
                    query.whereContains("sem",packet.getSem());
                    query.whereGreaterThanOrEqualTo("date", packet.getDate().toString());
                    //Log.i("yeesss",packet.getLecture());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e != null) {
                                e.printStackTrace();
                            } else {

                                if (objects.size() > 0)
                                obj = objects.get(0);
                            else
                                    obj = new ParseObject("attendance_" + packet.getYear());
                            obj.put("student_id", textViewRollNo.getText().toString());
                            obj.put("present", buttonView.isChecked());
                            obj.put("Lecture_id", packet.getLecture());
                            obj.put("subject", packet.getSubject());
                            obj.put("section", packet.getSec());
                            obj.put("branch", packet.getBranch());
                                obj.put("date", packet.getDate().toString());
                                obj.put("sem",packet.getSem());
                            obj.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e != null) {
                                        Log.i("ee", "dds");
                                        e.printStackTrace();
                                    }

                                }
                            });
                            }
                        }
                    });
                }
            });

        }
    }
}
