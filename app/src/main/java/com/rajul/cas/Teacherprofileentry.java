package com.rajul.cas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.R.attr.value;

/**
 * Created by RAJUL on 6/20/2017.
 */

   public class Teacherprofileentry extends AppCompatActivity {
       @Override
         protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_teacherprofileentry);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
       }

    public void jumpToTeacherDashboard(View v){
        Intent intent = new Intent(getApplicationContext(),HomeTeacher.class);
        startActivity(intent);

    }
    public void jumpToTeacherProfileEntry(View v){
        Intent intent = new Intent(getApplicationContext(),Teacherprofileentry.class);
        startActivity(intent);

    }



}