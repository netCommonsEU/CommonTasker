package com.example.commontasker;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Αρης on 1/8/2016.
 */

public class taskdetails extends MainActivity {

    private Button button,accept,cancel;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    TextView post_perigrafh,post_title;
    String description, titlos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetails);


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
                startActivity(new Intent(taskdetails.this,task.class));

            }
        });
        accept=(Button) findViewById(R.id.button7);
        cancel=(Button) findViewById(R.id.button8);
        post_perigrafh = (TextView) findViewById(R.id.taskdetailsperigrafh);
        post_title= (TextView) findViewById(R.id.titleposting);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            description = extras.getString("perigrafh").trim();
            titlos = extras.getString("title").trim();

        }
        System.out.println(description);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(taskdetails.this, task.class);
                startActivityForResult(intent, 0);
                v.startAnimation(buttonClick);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentText(description).
                        setContentTitle("Welcome to CommonTasker").setSmallIcon(R.drawable.notification).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);

            }
        });

        BlogViewHolder viewHolder = new BlogViewHolder();
        viewHolder.setPerigrafh(description);
        viewHolder.setTitlos(titlos);
        addVideo();

    }
    public class BlogViewHolder {

        public void setPerigrafh(String perigrafh) {

            post_perigrafh.setText(perigrafh);
          }
        public void setTitlos(String titlos) {
            post_title.setText(titlos);
        }

        }

        public void addVideo() {

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(taskdetails.this, videos.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);

            }

        });

        }
    }

