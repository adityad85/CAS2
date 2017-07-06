package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditProfile extends AppCompatActivity {
    EditText e1,e2,e3;
    Spinner branch,section,semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        e1 = (EditText)findViewById(R.id.enterFirstName);
        e2 = (EditText)findViewById(R.id.enterLastName);
        e3 = (EditText)findViewById(R.id.enterUnivRollNo);
        branch=(Spinner)findViewById(R.id.enterBranch);
        section=(Spinner)findViewById(R.id.enterSecEditProfile);
        semester=(Spinner)findViewById(R.id.enterSemEditProfile);
    }
ParseObject ob;
    public void jumptoStudentProfile(View v){
        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("students");
        query.whereEqualTo("id", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0){
                    Log.i("kaka","lala");
                    ob=objects.get(0);
                    ob.put("fName",e1.getText().toString());
                    ob.put("lName",e2.getText().toString());
                    ob.put("branch",branch.getSelectedItem());
                    ob.put("section",section.getSelectedItem());
                    ob.put("sem",semester.getSelectedItem());
                    ob.put("student_id",e3.getText().toString());
                    ob.saveInBackground();
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
       Intent intent = new Intent(getApplicationContext(), StudentDashboard.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

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
            Intent intent = new Intent(getApplicationContext(),Login.class);
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
