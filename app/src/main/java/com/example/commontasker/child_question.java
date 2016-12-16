package com.example.commontasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

/**
 * Created by Αρης on 25/10/2016.
 */


public class child_question extends AppCompatActivity {

    private Button button;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_question);
        button=(Button) findViewById(R.id.button6);
        ListenerOnButton();

    }

    private void ListenerOnButton() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), realchattime.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
    }


}
