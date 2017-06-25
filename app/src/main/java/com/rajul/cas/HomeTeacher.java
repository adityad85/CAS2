package com.rajul.cas;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeTeacher extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayAdapter<String> adaptersem, adapterbra, adaptersec, adaptersub;
    ArrayList<String> finalSem = new ArrayList<String>();
    ArrayList<String> finalBra = new ArrayList<String>();
    ArrayList<String> finalSec = new ArrayList<String>();
    ArrayList<String> finalSub = new ArrayList<String>();
    Spinner sem, branch, sec, sub, lec;
    String semis, branchis, secis, subis, lecis;
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
        lec = (Spinner) findViewById(R.id.spinner5);

    }


    public void jumptoDialog1(View v){
        final Dialog dialog = new Dialog(this);        // Context, this, etc.
        dialog.setContentView(R.layout.upload_attendance_dialog_box);
        sem = (Spinner) dialog.findViewById(R.id.spinner1);
        branch = (Spinner) dialog.findViewById(R.id.spinner2);
        sec = (Spinner) dialog.findViewById(R.id.spinner3);
        sub = (Spinner) dialog.findViewById(R.id.spinner4);
        lec = (Spinner) dialog.findViewById(R.id.spinner5);
        sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semis = parent.getSelectedItem().toString();
                Log.i("asd", semis);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchis = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secis = parent.getSelectedItem().toString();
                Log.i("asd", semis);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subis = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lecis = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

   /*     sec.setOnItemSelectedListener(this);
        sub.setOnItemSelectedListener(this);
        lec.setOnItemSelectedListener(this);
*/
        dialog.setTitle(R.string.dialog_upload_title1);
        clearr();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Teacher_sub");
        query.whereContains("id", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            Set<String> reDu = new HashSet<String>();
            Set<String> reDu1 = new HashSet<String>();
            Set<String> reDu2 = new HashSet<String>();
            Set<String> reDu3 = new HashSet<String>();
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (ParseObject obj : objects) {
                    reDu.add(obj.get("subject").toString());
                    reDu1.add(obj.get("branch").toString());
                    reDu2.add(obj.get("section").toString());
                    reDu3.add(obj.get("sem").toString());
                }
                finalSub.addAll(sor(reDu));
                finalSec.addAll(sor(reDu2));
                finalSem.addAll(sor(reDu3));
                finalBra.addAll(sor(reDu1));
            }
        });
        setAdapter1(finalSub, adaptersub, sub);
        setAdapter1(finalSec, adaptersec, sec);
        setAdapter1(finalSem, adaptersem, sem);
        setAdapter1(finalBra, adapterbra, branch);
        dialog.show();

    }

    public ArrayList<String> sor(Set<String> s) {
        ArrayList<String> asd = new ArrayList<String>(s);
        Collections.sort(asd);
        return asd;

    }

    public void clearr() {
        finalBra.clear();
        finalSub.clear();
        finalSem.clear();
        finalSec.clear();
        finalSem.add("Sem");
        finalBra.add("Branch");
        finalSec.add("Sec");
        finalSub.add("Sub");

    }
    public void setAdapter1(ArrayList<String> a, ArrayAdapter<String> ad, Spinner s) {
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, a);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(ad);
    }

    public void duplicates(ArrayList<String> a) {
        Set<String> reDu = new HashSet(a);
        a.clear();
        a.addAll(reDu);
        //Log.i("aqw",a.get(1));
    }
    public void jumptoDialog2(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.updateattendancedialogbox);
        dialog.setTitle(R.string.dialog_upload_title2);

        dialog.show();
    }

    public void jumptoattendanceUpload(View v){

        Intent intent = new Intent(getApplicationContext(),AttendanceUpload.class);
        intent.putExtra("section", sec.getSelectedItem().toString());
        intent.putExtra("branch", branch.getSelectedItem().toString());
        intent.putExtra("semester", sem.getSelectedItem().toString());
        intent.putExtra("lecture", lec.getSelectedItem().toString());
        intent.putExtra("subject", sub.getSelectedItem().toString());
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch ((int) id) {
            case R.id.spinner1: {
                semis = parent.getSelectedItem().toString();
                Log.i("asdqwe", semis);
                break;
            }
            case R.id.spinner2: {
                branchis = parent.getSelectedItem().toString();
                Log.i("asd", branchis);
                break;
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
