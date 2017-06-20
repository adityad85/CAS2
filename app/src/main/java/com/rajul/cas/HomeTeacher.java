package com.rajul.cas;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeTeacher extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void jumptoDialog1(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.uploadattendancedialogbox);
        dialog.setTitle(R.string.dialog_upload_title1);
        dialog.show();
    }
    public void jumptoDialog2(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.updateattendancedialogbox);
        dialog.setTitle(R.string.dialog_upload_title2);
        dialog.show();
    }

    public void jumptoattendanceUpload(View v){
        Intent intent = new Intent(getApplicationContext(),AttendanceUpload.class);
        startActivity(intent);
    }

    public void jumptoattendanceUpdate(View v){
        Intent intent = new Intent(getApplicationContext(),AttendanceUpload.class);
        startActivity(intent);

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
            Intent intent = new Intent(getApplicationContext(), HomeTeacher.class);
            startActivity(intent);
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(getApplicationContext(), Teacherprofileentry.class);
            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
