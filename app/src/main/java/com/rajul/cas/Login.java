package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {
    EditText username, password;
    Intent i;
    private LinearLayout headerProgress ;
    LinearLayout dim_layout;
    Boolean q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.KietEmailIdlogin);
        password = (EditText) findViewById(R.id.LognPassword);
        headerProgress = (LinearLayout)findViewById(R.id.lHeaderProgress);
        dim_layout = (LinearLayout) findViewById(R.id.dim_layout);
        i = getIntent();
        q = i.getExtras().getBoolean("represent");

    }



    boolean cancel = false;
    View focusView = null;

    public void check(View v) {


        if (TextUtils.isEmpty(password.getText().toString()) || password.getText().toString().length() < 6) {
            password.setError("Please Type Correctly");
            focusView = password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Can't Be Empty");
            focusView = username;
            cancel = true;

        }

    }
    public void check2(View v){
        //r a valid email address.
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Can't Be Empty");
            focusView = username;
            cancel = true;

        }
    }
    public void dashboard(View v){
        check(v);
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            headerProgress.setVisibility(View.VISIBLE);
            dim_layout.setVisibility(View.VISIBLE);
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(final ParseUser user, ParseException e) {
                    if (user != null) {
                        headerProgress.setVisibility(View.INVISIBLE);
                        dim_layout.setVisibility(View.INVISIBLE);
                        //ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("")
                        if (q == user.getBoolean("decide") && q == true) {
                            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("TEACHERS");
                            query.whereEqualTo("id", user.getUsername());
                            query.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    final ParseObject obj = objects.get(0);
                                    Boolean ans = obj.getBoolean("flag");
                                    if (ans) {
                                        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Teacher_sub");
                                        query1.whereEqualTo("id", user.getUsername());
                                        query1.findInBackground(new FindCallback<ParseObject>() {
                                            @Override
                                            public void done(List<ParseObject> objects, ParseException e) {
                                                if (objects.size() > 0) {
                                                    Intent intent = new Intent(getApplicationContext(), HomeTeacher.class);
                                                    startActivity(intent);
                                                    finish();
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                                                } else {
                                                    Intent intent = new Intent(getApplicationContext(), Teacherprofileentry.class);
                                                    startActivity(intent);
                                                    finish();
                                                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                                                }
                                            }
                                        });
                                  /*  Intent i= new Intent(getApplicationContext(),HomeTeacher.class);
                                    startActivity(i);*/
                                    } else
                                        Toast.makeText(getApplication(), "Get Verified By Contacting Admins", Toast.LENGTH_LONG
                                        ).show();

                                }
                            });
                        } else if (q == user.getBoolean("decide") && q == false) {
                            ParseQuery<ParseObject> a = new ParseQuery<ParseObject>("students");
                            a.whereEqualTo("id", user.getUsername());
                            a.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (objects.get(0).get("sem") != null) {
                                        Intent i = new Intent(getApplication(), StudentDashboard.class);
                                        startActivity(i);
                                        finish();
                                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                                    } else {
                                        Intent i = new Intent(getApplicationContext(), StudentProfileEntry.class);
                                        startActivity(i);
                                        finish();

                                    }

                                }
                            });


                        }else if(q==false){
                            Toast.makeText(getApplicationContext(),"You are a teacher, Go back",Toast.LENGTH_LONG).show();
                        }else if(q==true){
                            Toast.makeText(getApplicationContext(),"You are a student, Go back",Toast.LENGTH_LONG).show();
                        }
                    }else if (e != null) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Invalid Email/Password BC-Dekh K Daal", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Verify Your Email First", Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void forgotcheck(View view) {
        check2(view);
        if(cancel){
            focusView.requestFocus();
            Toast.makeText(getApplicationContext(),"Please Enter an Email",Toast.LENGTH_LONG).show();
        }else{
            ParseUser.requestPasswordResetInBackground(username.getText().toString(), new RequestPasswordResetCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(getApplicationContext(),"Password reset email sent",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"You're not registered with this email",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}
