package com.rajul.cas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class StudentprofileEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText e1 = (EditText)findViewById(R.id.univrollno);
        EditText e2 = (EditText)findViewById(R.id.sec);
        EditText e3 = (EditText)findViewById(R.id.sem);
        EditText e4 = (EditText)findViewById(R.id.contactno);
        e1.setAlpha(.75f);
        e2.setAlpha(.75f);
        e3.setAlpha(.75f);
        e4.setAlpha(.75f);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
