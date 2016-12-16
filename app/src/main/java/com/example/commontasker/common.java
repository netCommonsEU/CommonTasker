package com.example.commontasker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Αρης on 7/8/2016.
 */
public class common extends AppCompatActivity {

        private Button button;
        private Button button1;
        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.common);
            addListenerOnButton6();

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
                    startActivity(new Intent(common.this,maintask.class));

                }
            });
        }

        public void addListenerOnButton6() {

            button = (Button) findViewById(R.id.button4);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), moirasou.class);
                    startActivityForResult(intent, 0);
                    view.startAnimation(buttonClick);
                }

            });
            button1 = (Button) findViewById(R.id.button5);
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), diathesimothta.class);
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
                                Toast.makeText(common.this,"Successfull Logout",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(common.this,eggrafh.class));
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

