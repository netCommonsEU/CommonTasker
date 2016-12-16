package com.example.commontasker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;




/**
 * Created by Αρης on 30/6/2016.
 */
public class maintask extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintask);
        //setMessageButton();
        addListenerOnButton1();

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
              startActivity(new Intent(maintask.this,MainActivity.class));

            }
        });

    }

   /* private void setMessageButton() {

        final Button button = (Button) findViewById(R.id.button2);
        assert button!= null;
        if (button != null)
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                ImageView image = (ImageView) layout.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_action_warning);

                Toast toast = new Toast(getBaseContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                return false;
            }
        });

        Button button2 = (Button) findViewById(R.id.button3);
        assert button2!= null;
        if (button2 != null)

        button2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                ImageView image = (ImageView) layout.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_action_warning);

                Toast toast = new Toast(getBaseContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();

                return false;
            }
        });

        Button button3 = (Button) findViewById(R.id.button4);
        assert button3 != null;
        if (button3 != null)
            button3.setOnTouchListener(new View.OnTouchListener() {


                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.ic_action_warning);

                    Toast toast = new Toast(getBaseContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    return false;
                }
            });
        }*/

        public void addListenerOnButton1() {
        //Select a specific button to bundle it with the action you want
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ergo.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);


            }

        });
        button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), questionanswers.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);


            }
        });
            button3 = (Button) findViewById(R.id.button4);
            button3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), common.class);
                    startActivityForResult(intent, 0);
                    view.startAnimation(buttonClick);

                }
            });

  }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bottom, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch(item.getItemId()){
            case R.id.action_facebook:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.facebook.com"));
                startActivity(it);
                break;
            case R.id.action_phone:
                Intent intent = new Intent(this, sos.class);
                startActivity(intent);
                break;
            case R.id.action_home:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_login:
                Intent intent2 = new Intent(this, eggrafh.class);
                startActivity(intent2);
                break;
            case R.id.logout:
               logout();
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to exit the application;");
        alertDialogBuilder
                .setMessage("Press YES to exit!")
                .setCancelable(false)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(maintask.this,"Successfull Logout",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(maintask.this,eggrafh.class));
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


}
