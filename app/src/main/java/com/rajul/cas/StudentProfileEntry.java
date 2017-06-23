package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StudentProfileEntry extends AppCompatActivity {
     Spinner branch,section,semester;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         e1 = (EditText)findViewById(R.id.univrollno);
        e1.setAlpha(.75f);
        branch = (Spinner)findViewById(R.id.branchinput);
        section=(Spinner)findViewById(R.id.sec);
        semester=(Spinner)findViewById(R.id.sem);
    }
String branch1,section1,semester1,roll;
    public void jumptoStudentDashboard(View v){
        branch1=branch.getSelectedItem().toString();
        section1=section.getSelectedItem().toString();
        semester1=semester.getSelectedItem().toString();
        ParseUser user= ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("students");
        query.whereContains("id",user.getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null) {
                    ParseObject obj = objects.get(0);
                    roll=e1.getText().toString();
                    obj.put("section",section1);
                    obj.put("branch",branch1);
                    obj.put("student_id",roll);
                    obj.put("sem",semester1);
                    obj.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e==null)
                            {
                                Intent intent = new Intent(getApplicationContext(),StudentDashboard.class);
                                startActivity(intent);
                            }
                        }
                    });

                }}
        });

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
