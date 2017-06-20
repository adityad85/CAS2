package com.rajul.cas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edittext1 = (EditText)findViewById(R.id.KietEmailIdlogin);
        edittext1.setAlpha(.75f);
        EditText edittext2 = (EditText)findViewById(R.id.LognPassword);
        edittext2.setAlpha(.75f);
    }
    public void dashboard(View v){
      /**  Intent intent = new Intent(getApplicationContext(), Teacherprofileentry.class);
        startActivity(intent);
*/
    }

}
