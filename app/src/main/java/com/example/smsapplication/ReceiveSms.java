package com.example.smsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS Received!!", Toast.LENGTH_SHORT).show();
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String num = "";
        String body = "";
        if(bundle != null){

            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for(int i=0; i<msgs.length;i++){

                msgs[i]= SmsMessage.createFromPdu((byte[])pdus[i]);
                num = msgs[i].getOriginatingAddress();
                body = msgs[i].getMessageBody();
            }

        }

        Intent intent1 = new Intent(context, MyService.class);
        intent1.putExtra("message", body);
        intent1.putExtra("number", num);
        context.startService(intent1);
    }
}
