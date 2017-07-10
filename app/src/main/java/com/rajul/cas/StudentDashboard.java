package com.rajul.cas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
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
    Boolean ans=false;
    EditText daate1;
    String dateis1;
    DatePickerDialog datePickerDialog;
    public void jumptoViewAttendanceDialog(View v){
        final Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.select_date_dialog_box);
        daate1 = (EditText) dialog1.findViewById(R.id.dateSelectEdittext);
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



        daate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(StudentDashboard.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                if (monthOfYear <= 8)
                                daate1.setText(/*dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + */year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                else
                                    daate1.setText(/*dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + */year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                dateis1 = daate1.getText().toString();
                             ans=true;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dialog1.show();

    }

    ParseObject ob;
    String ii;
    public void jumptoViewAttendance(final View v){
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
                    transit(v);
                    Log.i("Asd", ii);
                }
                if (e != null)
                    e.printStackTrace();
            }
        });


        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
    }
public void transit(View v){
    Log.i("aaqa", String.valueOf(v.getId()));
    String i = "0";
    switch (v.getId()) {
        case R.id.overallbutton:
            i = "1";
        case R.id.dailybutton: {
            LocalDate date = new LocalDate();
            Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
            intent.putExtra("date", date.toString());
            intent.putExtra("ch", i);
            startActivity(intent);
            break;
        }
        case R.id.viewDate: {
            if(ans){
            Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
            intent.putExtra("date", dateis1);
            intent.putExtra("ch", i);
            Log.i("zx", ii);
            startActivity(intent);
            break;}
        }
            /*case R.id.overallbutton: {
                //LocalDate date = new LocalDate();
                Log.i("fyfjf","dry1");
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date","11");
                startActivity(intent);
                break;
            }*/
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
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(getApplicationContext(),StudentProfile.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }
        if(id == R.id.action_logout){
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    Intent intent = new Intent(getApplicationContext(),AskLogin.class);
                    startActivity(intent);

                }
            });
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }

        if(id== R.id.action_about){
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
