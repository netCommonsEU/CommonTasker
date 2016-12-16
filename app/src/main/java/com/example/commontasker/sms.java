package com.example.commontasker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.commontasker.discovery.WifiActivity;

/**
 * Created by Αρης on 17/9/2016.
 */

public class sms extends Activity {
    private Button button,button1;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        button=(Button) findViewById(R.id.button17);
        button1=(Button) findViewById(R.id.button18);
        addListenerOnButton();
    }

    public void goToInbox(View view){
        Intent intent=new Intent(sms.this,ReceiveSMSActivity.class);
        startActivity(intent);
    }

    public void goToCompose(View view){
        Intent intent=new Intent(sms.this,SendSmsActivity.class);
        startActivity(intent);
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WifiActivity.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), realchattime.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

    }
}
