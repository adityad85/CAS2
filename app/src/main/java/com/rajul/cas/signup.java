package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class signup extends AppCompatActivity {
    Boolean q;
    String emailis, passwordis, lNameis, fNameis, userna;
    EditText email, fName, lName,password;
    private LinearLayout headerProgress ;
    LinearLayout dim_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent intent = getIntent();
        q = intent.getExtras().getBoolean("represent");
        Log.i("here", q.toString());
        email=(EditText)findViewById(R.id.KietEmailIdsignup);
        password=(EditText) findViewById(R.id.createPasswordsignupWrapper);
        fName=(EditText)findViewById(R.id.firstname);
        lName=(EditText)findViewById(R.id.lastname);
        headerProgress = (LinearLayout)findViewById(R.id.lHeaderProgress);
        dim_layout = (LinearLayout) findViewById(R.id.dim_layout);




    }

    Boolean cancel = false;
    View focusView = null;

    public void check(View v) {
        if (TextUtils.isEmpty(passwordis) || passwordis.length() < 6) {
            focusView = password;
            cancel = true;

        }else
        if (TextUtils.isEmpty(fNameis)) {
            fName.setError("Can't Be Empty");
            focusView = fName;
            cancel = true;
        }else

        if (TextUtils.isEmpty(lNameis)) {
            lName.setError("Can't Be Empty");
            focusView = lName;
            cancel = true;
        }else
        if (TextUtils.isEmpty(emailis)) {
            email.setError("Can't Be Empty");
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(emailis)) {
            email.setError("Not Valid");
            focusView = email;
            cancel = true;
        }else cancel=false;

    }

    private boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        // final String emailk = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
        pattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\.[_A-Za-z0-9-]+)*+@kiet.edu$");
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }
    public void jumptoAskLoginScreen(View v){
        emailis = email.getText().toString();
        userna = emailis.split("@")[0];
        passwordis = password.getText().toString();
        fNameis = fName.getText().toString();
        lNameis = lName.getText().toString();

        check(v);
        if (cancel) {
            focusView.requestFocus();
        } else {

            headerProgress.setVisibility(View.VISIBLE);
            dim_layout.setVisibility(View.VISIBLE);
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

                    headerProgress.setVisibility(View.INVISIBLE);
                    dim_layout.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getApplicationContext(), AskLogin.class);
                    startActivity(intent);
                    obj.saveInBackground();
                } else {
                    if (e.toString() == "com.parse.ParseRequest$ParseRequestException: Account already exists for this username.")
                        Toast.makeText(getApplication(), "Account already exists for this username.", Toast.LENGTH_LONG).show();

                    headerProgress.setVisibility(View.INVISIBLE);
                    dim_layout.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getApplicationContext(), AskLogin.class);

                    startActivity(intent);
                    e.printStackTrace();
                }

            }
        });



            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
