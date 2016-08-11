package com.westhillcs.notextingwhilebusy;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private static ListView lv_messageList;
    private static Spinner  sp_autoReplySpinner;
    private static ArrayList<String> spinnerChoices;
    private static Context context;

    private static ArrayAdapter<TextMessage> messageListAdapter;
    private static ArrayList<TextMessage> messages;
    
    private static String autoReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        context = this;
        messages = new ArrayList<TextMessage>();
        lv_messageList = (ListView)findViewById(R.id.textMessageList_lv);
        sp_autoReplySpinner = (Spinner) findViewById(R.id.autoReplySpinner_sp);

        spinnerChoices = new ArrayList<>();
        spinnerChoices = new ArrayList<String>();
        spinnerChoices.add("Driving");
        spinnerChoices.add("Sleeping");
        spinnerChoices.add("Eating");
        autoReply = "I cannot reply to your message because I am: Driving";

        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  spinnerChoices);
        sp_autoReplySpinner.setAdapter(spinnerAdapter);
        sp_autoReplySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Auto Reply Selected is: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                MainActivity.autoReply = "I cannot reply to your message because i am:" + ((TextView) view).getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MainActivity.messageListAdapter = new ArrayAdapter<TextMessage>(this, android.R.layout.simple_list_item_1, messages);
        MainActivity.lv_messageList.setAdapter(messageListAdapter);
    }

    public static void updateList(TextMessage tm) {
        TextMessage _reply = new TextMessage(MainActivity.autoReply, tm.getPhoneNumber());
        MainActivity.sendText(_reply);
        MainActivity.messages.add(tm);
        MainActivity.messageListAdapter.notifyDataSetChanged();
    }

    private static void sendText(TextMessage tm) {
        try {
            SmsManager sm = SmsManager.getDefault();
            String _message = tm.getMessageBody();
            sm.sendTextMessage(tm.getPhoneNumber(), null, _message, null, null);

            Toast.makeText(MainActivity.context, _message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.context, "ERROR SENDING TEXT" + e.toString(), Toast.LENGTH_LONG).show();
        }

    }


}


