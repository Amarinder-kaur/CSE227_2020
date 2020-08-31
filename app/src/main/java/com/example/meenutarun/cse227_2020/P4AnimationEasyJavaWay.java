package com.example.meenutarun.cse227_2020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class P4AnimationEasyJavaWay extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4_animation_easy_java_way);
        textView = (TextView)findViewById(R.id.t1);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().alphaBy(1).alpha(0).setDuration(2000).start();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().alphaBy(0).alpha(1).setDuration(2000).start();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().scaleXBy(1f).setDuration(2000).start();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().scaleX(0.6f).setDuration(2000).start();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().translationXBy(100).setDuration(1000).start();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.animate().rotationYBy(360).scaleX(2f).setDuration(1500).start();
            }
        });

    }
}
