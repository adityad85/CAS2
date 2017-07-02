package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.value;

/**
 * Created by RAJUL on 6/20/2017.
 */

public class Teacherprofileentry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner sem, branch, sec, sub;
    String subject;

    @Override
         protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_teacherprofileentry);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
        branch = (Spinner) findViewById(R.id.adddetailsSpinnerbr);
        sem = (Spinner) findViewById(R.id.adddetailsSpinnerSem);
        sec = (Spinner) findViewById(R.id.adddetailsSpinnersec);
        sub = (Spinner) findViewById(R.id.adddetailsSpinnersub);
        branch.setOnItemSelectedListener(this);
        finalSub.add("Select Subject");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSub);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sub.setAdapter(adapter);

       }

    public void jumpToTeacherDashboard(View v){
        String branch1, sections, sems;
        subject = sub.getSelectedItem().toString();
        branch1 = branch.getSelectedItem().toString();
        sections = sec.getSelectedItem().toString();
        sems = sem.getSelectedItem().toString();
        Log.i("hey", branch1 + sections + sems);
        if (!(branch1.contains("Select Branch") && sections.contains("Select Section") && sems.contains("Select Sem"))) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Teacher_sub");
            query.whereEqualTo("id", ParseUser.getCurrentUser().getUsername());
            query.whereEqualTo("subject", subject);
            query.whereEqualTo("branch", branch.getSelectedItem().toString());
            query.whereEqualTo("section", sec.getSelectedItem().toString());
            query.whereEqualTo("sem", sem.getSelectedItem().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (objects.size() > 0) {
                        Toast.makeText(getApplicationContext(), "Already Entered", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeTeacher.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                    } else {
                        ParseObject ob = new ParseObject("Teacher_sub");
                        ob.put("id", ParseUser.getCurrentUser().getUsername());
                        ob.put("subject", sub.getSelectedItem().toString());
                        ob.put("branch", branch.getSelectedItem().toString());
                        ob.put("section", sec.getSelectedItem().toString());
                        ob.put("sem", sem.getSelectedItem().toString());
                        ob.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    Intent intent = new Intent(getApplicationContext(), HomeTeacher.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                                } else
                                    e.printStackTrace();
                            }
                        });
                    }
                }
            });

        } else
            Toast.makeText(getApplicationContext(), "Please select properly,baar baar ni kahenge,bc", Toast.LENGTH_LONG).show();
    }
    public void jumpToTeacherProfileEntry(View v){

        ParseObject ob = new ParseObject("Teacher_sub");
        ob.put("id", ParseUser.getCurrentUser().getUsername());
        ob.put("subject", sub.getSelectedItem().toString());
        ob.put("branch", branch.getSelectedItem().toString());
        ob.put("section", sec.getSelectedItem().toString());
        ob.put("sem", sem.getSelectedItem().toString());
        ob.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(getApplicationContext(), Teacherprofileentry.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                }
            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ArrayAdapter<String> adapter;
    ArrayList<String> finalSub = new ArrayList<String>();

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String br = branch.getSelectedItem().toString();
        Log.i("hhh", br);
        String se = sem.getSelectedItem().toString();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Subjects");
        //query.whereEqualTo("branch",br);
        query.whereContains("branch", br);
        query.whereEqualTo("Sem", se);
        ArrayAdapter<String> adapter;

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject ob : objects) {
                        finalSub.add(ob.get("code").toString());
                        Log.i("hey", ob.get("code").toString());

                    }
                } else
                    e.printStackTrace();
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSub);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sub.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}