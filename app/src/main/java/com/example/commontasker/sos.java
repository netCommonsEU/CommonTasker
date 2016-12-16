package com.example.commontasker;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class sos extends MainActivity implements View.OnClickListener {
    private ImageButton imb2;
    private ImageButton imb3;
    private EditText screen;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos);

        initializeView();

        imb2 = (ImageButton) findViewById(R.id.imb2);
        imb2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: 100"));
                if (ActivityCompat.checkSelfPermission(sos.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
        imb3 = (ImageButton) findViewById(R.id.imb3);
        imb3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: 166"));
                if (ActivityCompat.checkSelfPermission(sos.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    public void initializeView() {
        screen = (EditText) findViewById(R.id.screen);
        int idList[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btndelete, R.id.btnhash, R.id.btnstar, R.id.btncall};

        for (int d : idList) {
            View v = (View) findViewById(d);
            v.setOnClickListener(this);

        }
    }

    public void display(String val) {
        screen.append(val);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                view.startAnimation(buttonClick);
                display("0");
                break;

            case R.id.btn1:
                view.startAnimation(buttonClick);
                display("1");
                break;

            case R.id.btn2:
                view.startAnimation(buttonClick);
                display("2");
                break;
            case R.id.btn3:
                view.startAnimation(buttonClick);
                display("3");
                break;
            case R.id.btn4:
                view.startAnimation(buttonClick);
                display("4");
                break;
            case R.id.btn5:
                view.startAnimation(buttonClick);
                display("5");
                break;
            case R.id.btn6:
                view.startAnimation(buttonClick);
                display("6");
                break;
            case R.id.btn7:
                view.startAnimation(buttonClick);
                display("7");
                break;
            case R.id.btn8:
                view.startAnimation(buttonClick);
                display("8");
                break;
            case R.id.btn9:
                view.startAnimation(buttonClick);
                display("9");
                break;
            case R.id.btnhash:
                view.startAnimation(buttonClick);
                display("#");
                break;
            case R.id.btnstar:
                view.startAnimation(buttonClick);
                display("*");
                break;
            case R.id.btncall:

              if(screen.getText().toString().isEmpty())
                  Toast.makeText(getApplicationContext(),"Enter some digits please",Toast.LENGTH_LONG).show();
                else if(checkPermission())
                  view.startAnimation(buttonClick);
                 startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel: "+ screen.getText())));
                break;
            case R.id.btndelete:
                view.startAnimation(buttonClick);
                if(screen.getText().toString().length()>=1) {
                    String newScreen = screen.getText().toString().substring(0, screen.getText().toString().length() - 1);
                   screen.setText(newScreen);
                    break;
                }
                    default:
                break;
        }


    }
    private boolean checkPermission(){
        String permission="android.permission.CALL_PHONE";
        int res =getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res== PackageManager.PERMISSION_GRANTED);
    }

}