package com.example.commontasker;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Αρης on 17/9/2016.
 */
public class SendSmsActivity extends Activity {

     Button btn;
    EditText phonenumber;

    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;

    Intent intent;
    private boolean side = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.send_sms);
        btn=(Button)  findViewById(R.id.sendsms);
        listView = (ListView) findViewById(R.id.listView1);
        phonenumber=(EditText) findViewById(R.id.editTextphone);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return  sendSms();
                }
                return false;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendSms();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

private boolean sendSms(){

        String phone=phonenumber.getText().toString();
        String sms=chatText.getText().toString();

        try {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone , null ,sms , null ,null);
            chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString(),phonenumber.getText().toString()));
            Toast.makeText(this,"The message was sent!",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "The SMS sending failed, please try again.", Toast.LENGTH_LONG).show();
         e.printStackTrace();
        }

    chatText.setText("");
    side = !side;
    return true;
    }

    public  void goToInbox(View view){
        Intent intent=new Intent(SendSmsActivity.this,ReceiveSMSActivity.class);
        startActivity(intent);
    }

}
