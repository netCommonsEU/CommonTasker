package com.example.commontasker;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Αρης on 11/9/2016.
 */
public class swipe extends AppCompatActivity  {


    private TextView text, text1, text2, text3, text4, text5;
    ViewFlipper flipper;

    private float lastX;

    private Button button, button1;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.swipe);

        flipper = (ViewFlipper) findViewById(R.id.view);

        text = (TextView) findViewById(R.id.t1);
        text1 = (TextView) findViewById(R.id.t2);
        text2 = (TextView) findViewById(R.id.t3);
        text3 = (TextView) findViewById(R.id.t4);
        text4 = (TextView) findViewById(R.id.t5);


        button = (Button) findViewById(R.id.button11);
        button1 = (Button) findViewById(R.id.epistrofh);

        button.setVisibility((View.INVISIBLE));
        button1.setVisibility((View.INVISIBLE));

        addListenerOnButtonFirst();

    }


    public void addListenerOnButtonFirst() {
        //Select a specific button to bundle it with the action you want

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), eggrafh.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchevent) {

        switch (touchevent.getAction()) {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN: {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {



                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX) {
                    // If no more View/Child to flip
                    if (flipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    flipper.setInAnimation(this, R.anim.in_from_left);
                    flipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen


                        button.setVisibility((View.INVISIBLE));
                        button1.setVisibility((View.INVISIBLE));

                    flipper.showNext();

                    System.out.println(flipper.getDisplayedChild());

                }

                // if right to left swipe on screen
                if (lastX > currentX) {
                    if (flipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    flipper.setInAnimation(this, R.anim.in_from_right);
                    flipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen

                    if (flipper.getDisplayedChild() == 2) {
                        button.setVisibility(View.VISIBLE);
                        button1.setVisibility(View.VISIBLE);
                    } else {

                        button.setVisibility((View.INVISIBLE));
                        button1.setVisibility((View.INVISIBLE));
                    }
                    flipper.showPrevious();

                }
                break;
            }
        }
        return false;

      }


    }



