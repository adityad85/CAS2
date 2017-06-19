package com.rajul.cas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Teacherprofileentry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherprofileentry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText assignedsections = (EditText)findViewById(R.id.edittextsec);
        assignedsections.setAlpha(.75f);

    }
    public void jumpToTeacherDashboard(View v){
        Intent intent = new Intent(getApplicationContext(), HomeTeacher.class);
        startActivity(intent);

    }


}
