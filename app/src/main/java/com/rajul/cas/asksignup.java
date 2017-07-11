package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.anim.slide_in_left;
import static android.R.anim.slide_out_right;

public class asksignup extends AppCompatActivity implements View.OnClickListener {
    Button b, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asksignup);
        b = (Button) findViewById(R.id.teacherasksignupbutton);
        b1 = (Button) findViewById(R.id.studentasksignupbutton);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);

    }

    /*public void signupScreen(View v){
        Intent intent = new Intent(getApplicationContext(), signup.class);
        Log.i("here",v.toString());
        //intent.putExtra("represent",1);
        startActivity(intent);
    }*/
    public void jumptoAskLoginScreen(View v){
        Intent intent = new Intent(getApplicationContext(), AskLogin.class);
        //intent.putExtra("represent",0);
        startActivity(intent);
        finish();
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.teacherasksignupbutton:
                intent = new Intent(getApplicationContext(), signup.class);
                intent.putExtra("represent", true);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                break;
            case R.id.studentasksignupbutton:
                intent = new Intent(getApplicationContext(), signup.class);
                intent.putExtra("represent", false);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                break;
            default:
                break;
        }
    }
}
