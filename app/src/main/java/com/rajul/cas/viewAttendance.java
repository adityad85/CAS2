package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class viewAttendance extends AppCompatActivity {
    private ArrayList<ViewAttendanceRow> viewAttendanceRow = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public String date, id;
    public Packet packet = new Packet();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //yourListView.setAdapter(customAdapter);
        recyclerView = (RecyclerView) findViewById(R.id.viewAttendanceListview);
        recyclerView.setHasFixedSize(false);
        Intent i = getIntent();
        date = i.getStringExtra("date");
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }



    // get data from the table by the ListAdapter


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
        if (id== R.id.action_logout){
            if(id == R.id.action_logout){
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void getData() {
        id = packet.getIds();
        Log.i("jjk", id);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("attendance_1");
        Log.i("sdas0", date);
        query.whereEqualTo("date", date);
        query.whereEqualTo("student_id", id);
        query.addAscendingOrder("subject");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    Log.i("jjj", "kk");
                    parseData(objects);
                } else {
                    Log.i("jjlj", "kk");
                }
                if (e != null)
                    e.printStackTrace();
            }
        });
    }

    public HashMap<String, Map<String, Integer>> a = new HashMap<>();
    public HashMap<String, Integer> innerMap = new HashMap<>();

    private void parseData(List<ParseObject> objects) {
        for (ParseObject o : objects) {
            //ViewAttendanceRow vee=new ViewAttendanceRow();
            String sub = o.getString("subject");
            if (a.containsKey(sub)) {
                Integer i = 0;
                if (o.getBoolean("present")) {
                    i = a.get(sub).get("pre");
                    innerMap.put("pre", i + 1);
                } else {
                    i = a.get(sub).get("abs");
                    innerMap.put("abs", i + 1);
                }
            } else {
                Integer i = 0;
                if (o.getBoolean("present")) {
                    innerMap.put("pre", 1);
                    innerMap.put("abs", 0);
                } else {
                    innerMap.put("abs", 1);
                    innerMap.put("pre", 0);
                }
            }
            a.put(sub, innerMap);

        }

        for (HashMap.Entry<String, Map<String, Integer>> entry : a.entrySet()) {
            ViewAttendanceRow vee = new ViewAttendanceRow();
            vee.setAbs(entry.getValue().get("abs"));
            vee.setPre(entry.getValue().get("pre"));
            vee.setName(entry.getKey());
            Log.i("asd", entry.getValue().get("pre").toString());
            viewAttendanceRow.add(vee);
        }
        adapter = new com.rajul.cas.ListAdapter(viewAttendanceRow, getApplicationContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
