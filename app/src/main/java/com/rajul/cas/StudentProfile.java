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

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StudentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
