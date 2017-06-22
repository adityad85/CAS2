package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {
    EditText username, password;
    Intent i;
    Boolean q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.KietEmailIdlogin);
        username.setAlpha(.75f);
        password = (EditText) findViewById(R.id.LognPassword);
        password.setAlpha(.75f);
        i = getIntent();
        q = i.getExtras().getBoolean("represent");
    }
    public void dashboard(View v){
        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(final ParseUser user, ParseException e) {
                if (user != null) {
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
                                            } else {
                                                Intent intent = new Intent(getApplicationContext(), Teacherprofileentry.class);
                                                startActivity(intent);
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
                        Intent i = new Intent(getApplicationContext(), StudentProfileEntry.class);
                        startActivity(i);
                    }
                } else e.printStackTrace();
            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
