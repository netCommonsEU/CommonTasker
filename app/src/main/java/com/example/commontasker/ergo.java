package com.example.commontasker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;


/**
 * Created by Αρης on 1/7/2016.
 */

public class ergo extends AppCompatActivity {


    private Button button;
    private Button button1;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergo);
        addListenerOnButton2();


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(null);
        }
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(ergo.this,maintask.class));

            }
        });


    }

    public void addListenerOnButton2() {

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), createtask.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }

        });
        button1 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), task.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

    }
}