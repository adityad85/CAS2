package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.joda.time.LocalDate;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class StudentDashboard extends AppCompatActivity {
    public Packet packet = new Packet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    ParseObject ob;
    String ii;
    public void jumptoViewAttendance(View v){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("students");
        query.whereContains("id", ParseUser.getCurrentUser().getUsername());
        Log.i("vjh", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    Log.i("gg", "jj");
                    ii = objects.get(0).get("student_id").toString();
                    packet.setIds(ii);
                    //  ii=ob.getString("student_id");
                    Log.i("Asd", ii);
                }
                if (e != null)
                    e.printStackTrace();
            }
        });


        switch (v.getId()) {
            case R.id.dailybutton: {
                LocalDate date = new LocalDate();
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date", date.toString());
                startActivity(intent);
            }
            case R.id.selectdatebutton: {
                LocalDate date = new LocalDate();
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date", date.toString());
                startActivity(intent);
            }
            case R.id.overallbutton: {
                LocalDate date = new LocalDate();
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date", date.toString());
                startActivity(intent);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
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
            Intent intent = new Intent(getApplicationContext(),StudentDashboard.class);
            startActivity(intent);
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(getApplicationContext(),StudentProfile.class);
            startActivity(intent);
        }
        if(id == R.id.action_logout){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
