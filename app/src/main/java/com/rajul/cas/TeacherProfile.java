package com.rajul.cas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TeacherProfile extends AppCompatActivity {
    TextView fName, lName;
    Packet p;
    public ArrayList<Teacher> teacher1 = new ArrayList<>();
    ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.assignedClaassesListView);
        recyclerView.setHasFixedSize(false);
        fName = (TextView) findViewById(R.id.firstnameprofileTeacher);
        lName = (TextView) findViewById(R.id.lastnameprofileTeacher);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("TEACHERS");
        query.whereContains("id", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                ParseObject obj = objects.get(0);
                fName.setText(obj.getString("fName"));
                lName.setText(obj.getString("lName"));
                Log.i("kjn", "iub");
            }
        });
        getData();
        //adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    public void jumptoChangePasswordDialogTeacher(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.change_password_teacher_dialog);
        dialog.setTitle("Change Password");
        dialog.show();
    }
    public void jumptoteacherprofileFromDialog(View v){
        Intent intent = new Intent(getApplicationContext(),TeacherProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
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
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(getApplicationContext(), TeacherProfile.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }
        if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void getData() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Teacher_sub");
        query.whereEqualTo("id", ParseUser.getCurrentUser().getUsername());
        Log.i("fg", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    parseData(objects);
                    Log.i("as", "asd");
                }
            }
        });
    }

    public void parseData(List<ParseObject> obj) {
        for (ParseObject o : obj) {
            Teacher te = new Teacher();
            try {
                te.setBra(o.getString("branch"));
                te.setSec(o.getString("section"));
                te.setSub(o.getString("subject"));
                te.setSem(o.getString("sem"));
                Log.i("asa", o.getString("sem"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            teacher1.add(te);
            Log.i("AKS", "ALS");
        }
/*Teacher t=new Teacher();
        t.setBra("asd");
        t.setSec("a");
        t.setSem("as");
        t.setSub("easd");
        //teacher1.add(t);*/

        adapter = new TeacherProfileAdapter(teacher1, getApplicationContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
