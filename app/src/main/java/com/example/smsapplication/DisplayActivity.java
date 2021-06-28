package com.example.smsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView tv1 = findViewById(R.id.smsnum);
        TextView tv2 = findViewById(R.id.smsbody);

        Intent intent = getIntent();
        String num = intent.getExtras().getString("num");
        String msg = intent.getExtras().getString("msg");

        tv1.append(num);
        tv2.append(msg);
    }
}