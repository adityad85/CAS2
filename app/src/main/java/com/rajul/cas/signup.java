package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class signup extends AppCompatActivity {
    Boolean q;
    String emailis, passwordis, lNameis, fNameis, userna;
    EditText email, password, fName, lName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = (EditText) findViewById(R.id.KietEmailIdsignup);
        email.setAlpha(.75f);
        password = (EditText) findViewById(R.id.createPasswordsignup);
        password.setAlpha(.75f);
        fName = (EditText) findViewById(R.id.firstname);
        fName.setAlpha(.75f);
        lName = (EditText) findViewById(R.id.lastname);
        lName.setAlpha(.75f);
        Intent intent = getIntent();
        q = intent.getExtras().getBoolean("represent");
        Log.i("here", q.toString());

    }
    public void jumptoAskLoginScreen(View v){
        emailis = email.getText().toString();
        userna = emailis.split("@")[0];
        passwordis = password.getText().toString();
        fNameis = fName.getText().toString();
        lNameis = lName.getText().toString();

        String se;
        if (q == true)
            se = "TEACHERS";
        else se = "students";
        final ParseObject obj = new ParseObject(se);
        ParseUser user = new ParseUser();
        user.setUsername(userna);
        user.setPassword(passwordis);
        user.setEmail(emailis);
        user.put("decide", q);
        obj.put("fName", fNameis);
        obj.put("lName", lNameis);
        obj.put("id", userna);
        obj.put("email", emailis);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    final String title = "Account Created Successfully";
                    final String message = "Please verify your email before Login";
                    Toast.makeText(getApplicationContext(), title + message, Toast.LENGTH_LONG).show();
                    obj.saveInBackground();
                } else
                    e.printStackTrace();
            }
        });


        Intent intent = new Intent(getApplicationContext(), AskLogin.class);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
