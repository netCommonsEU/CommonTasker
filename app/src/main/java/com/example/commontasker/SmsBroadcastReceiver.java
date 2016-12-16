package com.example.commontasker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Αρης on 17/9/2016.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    public  static final String  SMS_BUNDLE="pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras=intent.getExtras();

        if(intentExtras!=null){
            Object [] sms=(Object[]) intentExtras.get(SMS_BUNDLE);

            String smsMessageStr="";

            for (int i=0;i<sms.length;i++){
                SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) sms[i]);
                 String smsBody=smsMessage.getMessageBody().toString();
                String address=smsMessage.getOriginatingAddress();

                long timeMillis=smsMessage.getTimestampMillis();

                Date date=new Date(timeMillis);

                SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
                String dateText =format.format(date);

                smsMessageStr+=address + " στις " + "\t" +dateText + "\n";
                smsMessageStr+=smsBody +"\n";

            }

            Toast.makeText(context,smsMessageStr,Toast.LENGTH_LONG).show();

           ReceiveSMSActivity inst=ReceiveSMSActivity.instance();
            if (inst!=null){
                inst.updateList(smsMessageStr);
            }

        }
    }
}
