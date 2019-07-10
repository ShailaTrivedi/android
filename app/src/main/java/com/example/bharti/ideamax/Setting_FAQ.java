package com.example.bharti.ideamax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting_FAQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__faq);
         Button ans1=(Button)findViewById(R.id.ans1);
        Button ans2=(Button)findViewById(R.id.ans2) ;
        Button ans3=(Button)findViewById(R.id.ans3) ;
        Button ans4=(Button)findViewById(R.id.ans4) ;
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Answer1.class);
                startActivity(i);
            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),answer2.class);
                startActivity(i);
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Answer3.class);
                startActivity(i);
            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Answer4.class);
                startActivity(i);
            }
        });
    }
}
