package com.rajul.cas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class StudentProfileEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText e1 = (EditText)findViewById(R.id.univrollno);
        EditText e2 = (EditText)findViewById(R.id.sec);
        EditText e3 = (EditText)findViewById(R.id.sem);
        EditText e4 = (EditText)findViewById(R.id.branchinput);
        e1.setAlpha(.75f);
        e2.setAlpha(.75f);
        e3.setAlpha(.75f);
        e4.setAlpha(.75f);

    }
    public void jumptoStudentDashboard(View v){
        Intent intent = new Intent(getApplicationContext(),StudentDashboard.class);
        startActivity(intent);
    }




}
