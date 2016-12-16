package com.example.commontasker;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Αρης on 17/9/2016.
 */
public class ReceiveSMSActivity extends Activity implements AdapterView.OnItemClickListener {

    private static ReceiveSMSActivity inst;
    ArrayList<String> smsMessageList= new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;

    public  static ReceiveSMSActivity instance(){
        return inst;
    }
    protected void onStart(){
        super.onStart();
        inst=this;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_sms);

        smsListView=(ListView) findViewById(R.id.list);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,smsMessageList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);
        refreshSmsInbox();


    }

    public void refreshSmsInbox(){
        ContentResolver contentResolver=getContentResolver();
        Cursor smsInboxCursor=contentResolver.query(Uri.parse("content://sms/inbox"),null,null,null,null);

        int indexBody=smsInboxCursor.getColumnIndex("body");
        int indexAddress=smsInboxCursor.getColumnIndex("address");
        int timeMillis=smsInboxCursor.getColumnIndex("date");

        Date date=new Date(timeMillis);
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

        String dateText=format.format(date);

        if(indexBody<0||!smsInboxCursor.moveToFirst())
            return;

        arrayAdapter.clear();
        do{
            String str=smsInboxCursor.getString(indexAddress) + " στο "+ "\n"+ smsInboxCursor.getString(indexBody) + " "+ dateText +"\n";
            arrayAdapter.add(str);
        }
        while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage){
        arrayAdapter.insert(smsMessage,0);
        arrayAdapter.notifyDataSetChanged();
    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        try {
            String [] smsMessages=smsMessageList.get(position).split("\n");
            String address=smsMessages[0];
            String smsMessage="";

            for (int i = 0; i<smsMessages.length; i++){
                smsMessage+=smsMessages[i];

            }
            String smsMessageStr=address +"\n";
            smsMessageStr+=smsMessage;
            Toast.makeText(this,smsMessageStr,Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToCompose(View view){
        Intent intent=new Intent(ReceiveSMSActivity.this,SendSmsActivity.class);
        startActivity(intent);
    }

}
