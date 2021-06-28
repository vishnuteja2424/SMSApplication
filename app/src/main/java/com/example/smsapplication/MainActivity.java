package com.example.smsapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.RECEIVE_SMS}, 1000);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1000);
        }


        EditText phno = findViewById(R.id.smsnum);
        EditText msg = findViewById(R.id.smsbody);
        Button button = findViewById(R.id.btn);

        button.setOnClickListener(view -> {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phno.getText().toString(), null, msg.getText().toString(), null, null);
            Toast.makeText(this, "SMS sent Successfully", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1000) {
            if(grantResults.length!= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted!!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission not granted!!!Please grant SMS Permission in App info", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}