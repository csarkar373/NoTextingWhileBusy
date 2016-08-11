package com.westhillcs.notextingwhilebusy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chandan on 8/8/2016.
 */

// most of this code was donated by Shirish Herwade on Stack Overflow
public class TextReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages = null;
        TextMessage _tm = new TextMessage("No Text Message Received", "");

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");

            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = myBundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {

                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                _tm = new TextMessage(
                        messages[i].getMessageBody(),
                        messages[i].getOriginatingAddress()
                 );
            }

            Log.v("SMS", _tm.toString());
            Toast.makeText(context, _tm.toString(), Toast.LENGTH_LONG).show();
            MainActivity.updateList(_tm);
        }
    }
}

