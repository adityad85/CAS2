package com.rajul.cas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AskLogin extends AppCompatActivity implements View.OnClickListener {
    Button b, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_login);
        b = (Button) findViewById(R.id.teacheraskloginbutton);
        b1 = (Button) findViewById(R.id.studentaskloginbutton);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
    }
    public void jumptoAskSignupScreen(View v){
        Intent intent = new Intent(getApplicationContext(), asksignup.class);
        startActivity(intent);
        overridePendingTransition( R.anim.slide_in_down, R.anim.slide_out_down );
    }

    /*public void jumptoLoginScreen(View v){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);

    }*/
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.teacheraskloginbutton:
                intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("represent", true);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                startActivity(intent);
                break;
            case R.id.studentaskloginbutton:
                intent = new Intent(getApplicationContext(), Login.class);
                intent.putExtra("represent", false);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                break;
            default:
                break;
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
