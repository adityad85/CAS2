package com.rajul.cas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StudentProfile extends AppCompatActivity {
    TextView fName, lNamme, cursem, curbra, cursec, roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fName = (TextView) findViewById(R.id.firstnameprofile);
        lNamme = (TextView) findViewById(R.id.lastnameprofile);
        cursem = (TextView) findViewById(R.id.currentsem);
        curbra = (TextView) findViewById(R.id.currentbranch);
        cursec = (TextView) findViewById(R.id.currentsec);
        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("students");
        query.whereEqualTo("id", user.getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    ParseObject ob = objects.get(0);
                    fName.setText(ob.get("fName").toString());
                    lNamme.setText(ob.get("lName").toString());
                    cursem.setText(ob.get("sem").toString());
                    curbra.setText(ob.get("branch").toString());
                    cursec.setText(ob.get("section").toString());
                } else
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void jumptoChangePasswordDialog(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.change_password_dialog);
        dialog.setTitle(R.string.dialog_changepassword);
        dialog.show();
    }
    public void jumptostudentprofileFromDialog(View v){
        Intent intent = new Intent(getApplicationContext(),StudentProfile.class);
        startActivity(intent);
    }
    public void jumptoEditProfile(View v){
        Intent intent = new Intent(getApplicationContext(), EditProfile.class);
        startActivity(intent);
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
