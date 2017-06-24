package com.rajul.cas;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeTeacher extends AppCompatActivity {
    ArrayAdapter<String> adaptersem, adapterbra, adaptersec, adaptersub;
    ArrayList<String> finalSem = new ArrayList<String>();
    ArrayList<String> finalBra = new ArrayList<String>();
    ArrayList<String> finalSec = new ArrayList<String>();
    ArrayList<String> finalSub = new ArrayList<String>();
    Spinner sem, branch, sec, sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        finalSem.add("Sem");
        finalBra.add("Branch");
        finalSec.add("Sec");
        finalSub.add("Sub");
        sem = (Spinner) findViewById(R.id.spinner1);
        branch = (Spinner) findViewById(R.id.spinner2);
        sec = (Spinner) findViewById(R.id.spinner3);
        sub = (Spinner) findViewById(R.id.spinner4);
        adaptersem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSem);
        adaptersem.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //sem.setAdapter(adaptersem);
        adapterbra = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalBra);
        adapterbra.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //branch.setAdapter(adapterbra);
        adaptersec = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSec);
        adaptersec.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //sec.setAdapter(adaptersec);
        adaptersub = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSub);
        adaptersub.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //sub.setAdapter(adaptersub);

    }
    public void jumptoDialog1(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.upload_attendance_dialog_box);
        sem = (Spinner) dialog.findViewById(R.id.spinner1);
        branch = (Spinner) dialog.findViewById(R.id.spinner2);
        sec = (Spinner) dialog.findViewById(R.id.spinner3);
        sub = (Spinner) dialog.findViewById(R.id.spinner4);

        dialog.setTitle(R.string.dialog_upload_title1);

        dialog.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Teacher_sub");
        query.whereContains("id", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (ParseObject obj : objects) {
                    finalSub.add(obj.get("subject").toString());
                    finalBra.add(obj.get("branch").toString());
                    finalSec.add(obj.get("section").toString());
                    finalSem.add(obj.get("sem").toString());
                }
            }
        });
        /*
        HashSet reDu=new HashSet();
        reDu.add(finalSub);
        finalSub.clear();
        finalSub.addAll(reDu);*/
        duplicates(finalSub);
        duplicates(finalSec);
        duplicates(finalSem);
        duplicates(finalBra);
        /*adaptersem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, finalSem);
        adaptersem.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sem.setAdapter(adaptersem);
        */
        setAdapter1(finalSub, adaptersub, sub);
        setAdapter1(finalSec, adaptersec, sec);
        setAdapter1(finalSem, adaptersem, sem);
        setAdapter1(finalBra, adapterbra, branch);

    }

    public void setAdapter1(ArrayList<String> a, ArrayAdapter<String> ad, Spinner s) {
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, a);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(ad);
    }

    public void duplicates(ArrayList<String> a) {
        HashSet reDu = new HashSet();
        reDu.add(a);
        a.clear();
        a.addAll(reDu);
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
            Intent intent = new Intent(getApplicationContext(), TeacherProfile.class);
            startActivity(intent);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
