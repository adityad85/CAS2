package com.rajul.cas;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class viewAttendance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ArrayList<ViewAttendanceRow> viewAttendanceRow = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public String date, id;
    public Packet packet = new Packet();
    public Spinner spi;
    private LinearLayout headerProgress ;
    LinearLayout dim_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        headerProgress = (LinearLayout)findViewById(R.id.lHeaderProgress);
        dim_layout = (LinearLayout) findViewById(R.id.dim_layout);
        //yourListView.setAdapter(customAdapter);
        recyclerView = (RecyclerView) findViewById(R.id.viewAttendanceListview);
        recyclerView.setHasFixedSize(false);

        Intent i = getIntent();
        date = i.getStringExtra("date");
        String k = i.getExtras().getString("ch");
        Log.i("ll00", k);
        if (k.equals("1")) {
            Log.i("asssssa", "qqqqqqqq");
            getData2();

        } else {
            getData();
        }
        spi = (Spinner) findViewById(R.id.ViewAttendanceSpinner);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        spi.setOnItemSelectedListener(this);


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
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null) {
                        Intent intent = new Intent(getApplicationContext(), AskLogin.class);
                        startActivity(intent);
                    }
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

    public void getData() {
        headerProgress.setVisibility(View.VISIBLE);
        dim_layout.setVisibility(View.VISIBLE);
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
                    Toast.makeText(getApplicationContext(), "No Attendance Stored for this Day", Toast.LENGTH_SHORT).show();
                    headerProgress.setVisibility(View.INVISIBLE);
                    dim_layout.setVisibility(View.INVISIBLE);
                    Intent i=new Intent(getApplicationContext(),StudentDashboard.class);
                    startActivity(i);
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
            vee.setLec();
            vee.setPer();
            Log.i("asd", entry.getValue().get("pre").toString());
            viewAttendanceRow.add(vee);
        }
        adapter = new ListAdapterStudent(viewAttendanceRow, getApplicationContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        headerProgress.setVisibility(View.INVISIBLE);
        dim_layout.setVisibility(View.INVISIBLE);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    DatePickerDialog datePickerDialog;

    EditText daate1;
    String dateis1;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getSelectedItem().toString()) {
            case "Daily": {
                LocalDate date = new LocalDate();
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date", date.toString());
                intent.putExtra("ch","0");
                Log.i("vx", "nmmb");
                startActivity(intent);
                break;
            }
            case "Select Date": {
                final Dialog dialog1 = new Dialog(this);
                dialog1.setContentView(R.layout.select_date_dialog_box);
                daate1 = (EditText) dialog1.findViewById(R.id.dateSelectEdittext);
                daate1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR); // current year
                        int mMonth = c.get(Calendar.MONTH); // current month
                        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                        // date picker dialog
                        datePickerDialog = new DatePickerDialog(viewAttendance.this,
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

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
                dialog1.show();
                break;

            }
            case "Overall": {
                Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
                intent.putExtra("date", dateis1);
                intent.putExtra("ch","1");
                Log.i("zx", "cvc");
                startActivity(intent);


            }

        }
    }

    public void jumptoViewAttendance(View v) {
        Intent intent = new Intent(getApplicationContext(), viewAttendance.class);
        intent.putExtra("date", dateis1);
        intent.putExtra("ch","0");
        Log.i("zx", "cvc");
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getData2() {
        headerProgress.setVisibility(View.VISIBLE);
        dim_layout.setVisibility(View.VISIBLE);
        id = packet.getIds();
        Log.i("jjk", id);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("attendance_1");
        //Log.i("sdas0", date);
        //query.whereEqualTo("date", date);
        query.whereEqualTo("student_id", id);
        query.addAscendingOrder("subject");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0) {
                    Log.i("jjj", "kk");
                    parseData(objects);
                } else {
                    Toast.makeText(getApplicationContext(), "No Attendance Stored for this Day", Toast.LENGTH_SHORT).show();
                    headerProgress.setVisibility(View.INVISIBLE);
                    dim_layout.setVisibility(View.INVISIBLE);
                    Intent i=new Intent(getApplicationContext(),StudentDashboard.class);
                    startActivity(i);

                }
                if (e != null)
                    e.printStackTrace();
            }
        });

    }
}
