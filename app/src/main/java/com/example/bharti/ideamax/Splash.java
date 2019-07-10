package com.example.bharti.ideamax;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String emailID = (String) AppPersistance.start(Splash.this).get(AppPersistance.keys.USER_NAME);
                if (emailID != null && emailID.length() > 0) {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 2 * 1000);

    }
}
