package com.rajul.cas;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
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
    String semis, branchis, secis, subis, lecis, dateis;
    Packet p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        finalSem.add("Select Sem");
        finalBra.add("Select Branch");
        finalSec.add("Select Section");
        finalSub.add("Select Subject");
        sem = (Spinner) findViewById(R.id.spinner1);
        branch = (Spinner) findViewById(R.id.spinner2);
        sec = (Spinner) findViewById(R.id.spinner3);
        sub = (Spinner) findViewById(R.id.spinner4);
        lec = (Spinner) findViewById(R.id.spinner5);
        //sem2=(Spinner)findViewById(R.id.spi)


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
        finalSem.add("Select Sem");
        finalBra.add("Select Branch");
        finalSec.add("Select Section");
        finalSub.add("Select Subject");

    }
    public void setAdapter1(ArrayList<String> a, ArrayAdapter<String> ad, Spinner s) {
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, a);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(ad);
    }
    Boolean ans=true;
    EditText daate;
    DatePickerDialog datePickerDialog;
    public void jumptoDialog2(View v){
        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.updateattendancedialogbox);
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
                ans=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ans=false;
            }
        });
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchis = parent.getSelectedItem().toString();
                ans=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ans=false;
            }
        });
        sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secis = parent.getSelectedItem().toString();
                Log.i("asd", semis);
                ans=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               ans=false;
            }
        });
        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subis = parent.getSelectedItem().toString();
                ans=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
             ans=false;
            }
        });

        lec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lecis = parent.getSelectedItem().toString();
                ans=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
             ans=false;
            }
        });



        dialog.setTitle(R.string.dialog_upload_title2);
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

        daate = (EditText) dialog.findViewById(R.id.putdate);
        daate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(HomeTeacher.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                daate.setText(/*dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + */year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                dateis = daate.getText().toString();

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dialog.show();
    }

    public void jumptoattendanceUpload(View v) {
        if (ans) {
            //Either You've already made it or check the lecture nuumber or youre not authorised
            ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Teacher_Sub");
            query.whereEqualTo("subject",sub.getSelectedItem().toString());
            query.whereEqualTo("sem",sem.getSelectedItem().toString());
            query.whereEqualTo("section",sec.getSelectedItem().toString());
            query.whereEqualTo("id",ParseUser.getCurrentUser().getUsername());
            query.whereEqualTo("branch",branch.getSelectedItem().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(objects.size()>0){
                        ans=false;
                    }else{
                        Intent intent = new Intent(getApplicationContext(), AttendanceUpload.class);
                        intent.putExtra("section", sec.getSelectedItem().toString());
                        intent.putExtra("branch", branch.getSelectedItem().toString());
                        intent.putExtra("semester", sem.getSelectedItem().toString());
                        intent.putExtra("lecture", lec.getSelectedItem().toString());
                        intent.putExtra("subject", sub.getSelectedItem().toString());
                        LocalDate m = new LocalDate();
                        intent.putExtra("date", m.toString());
                        startActivity(intent);

                    }
                }
            });
            /*
            Intent intent = new Intent(getApplicationContext(), AttendanceUpload.class);
            intent.putExtra("section", sec.getSelectedItem().toString());
            intent.putExtra("branch", branch.getSelectedItem().toString());
            intent.putExtra("semester", sem.getSelectedItem().toString());
            intent.putExtra("lecture", lec.getSelectedItem().toString());
            intent.putExtra("subject", sub.getSelectedItem().toString());
            LocalDate m = new LocalDate();
            intent.putExtra("date", m.toString());
            startActivity(intent);*/
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    public void jumptoattendanceUpdate(View v) {
        if (ans) {
            //Either You've already made it or check the lecture nuumber or youre not authorised
            ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Teacher_Sub");
            query.whereEqualTo("subject",sub.getSelectedItem().toString());
            query.whereEqualTo("sem",sem.getSelectedItem().toString());
            query.whereEqualTo("section",sec.getSelectedItem().toString());
            query.whereEqualTo("id",ParseUser.getCurrentUser().getUsername());
            query.whereEqualTo("branch",branch.getSelectedItem().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(objects.size()>0){
                        ans=false;
                    }else{
                        Intent intent = new Intent(getApplicationContext(), AttendanceUpload.class);
                        intent.putExtra("section", sec.getSelectedItem().toString());
                        intent.putExtra("branch", branch.getSelectedItem().toString());
                        intent.putExtra("semester", sem.getSelectedItem().toString());
                        intent.putExtra("lecture", lec.getSelectedItem().toString());
                        intent.putExtra("subject", sub.getSelectedItem().toString());
                        intent.putExtra("date", dateis);
                        startActivity(intent);

                    }
                }
            });

/*            Intent intent = new Intent(getApplicationContext(), AttendanceUpload.class);
        intent.putExtra("section", sec.getSelectedItem().toString());
        intent.putExtra("branch", branch.getSelectedItem().toString());
        intent.putExtra("semester", sem.getSelectedItem().toString());
        intent.putExtra("lecture", lec.getSelectedItem().toString());
        intent.putExtra("subject", sub.getSelectedItem().toString());
        intent.putExtra("date", dateis);
        startActivity(intent);*/
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
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
        if(id== R.id.action_about){
            Intent intent = new Intent(getApplicationContext(), About.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
        }

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
