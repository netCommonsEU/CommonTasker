package com.example.commontasker;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button button,button1;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text1 = (TextView) findViewById(R.id.textView);
        final TextView text2 = (TextView) findViewById(R.id.textView2);
        final TextView text3 = (TextView) findViewById(R.id.textView3);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.zoom);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.falling);
        text1.setAnimation(anim1);
        text3.setAnimation(anim);
        text2.startAnimation(anim);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button15);

        ObjectAnimator colorAnim = ObjectAnimator.ofInt(button, "textColor", Color.DKGRAY, Color.TRANSPARENT);
        colorAnim.setDuration(1000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        addListenerOnButton();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to exit the application;");
        alertDialogBuilder
                .setMessage("Press YES to exit!")
                .setCancelable(false)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), maintask.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), swipe.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

    }

}