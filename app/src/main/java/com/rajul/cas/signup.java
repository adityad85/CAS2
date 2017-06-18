package com.rajul.cas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText edittext1 = (EditText)findViewById(R.id.KietEmailIdsignup);
        edittext1.setAlpha(.75f);
        EditText edittext2 = (EditText)findViewById(R.id.createPasswordsignup);
        edittext2.setAlpha(.75f);
        EditText edittext3 = (EditText)findViewById(R.id.firstname);
        edittext3.setAlpha(.75f);
        EditText edittext4 = (EditText)findViewById(R.id.lastname);
        edittext4.setAlpha(.75f);
    }
    public void jumptoAskLoginScreen(View v){
        Intent intent = new Intent(getApplicationContext(), AskLogin.class);
        startActivity(intent);
    }
}
