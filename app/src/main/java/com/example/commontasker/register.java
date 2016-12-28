package com.example.commontasker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

import butterknife.ButterKnife;

/**
 * Created by Αρης on 24/8/2016.
 */

public class register extends AppCompatActivity implements View.OnClickListener {

    Animation shake;
    private Vibrator vib;
    private EditText name, lo,phone,age;
    String nam,loc,ph,ag;
    String MacAddress;
    Context context;

    private ProgressDialog mProgress;
    private TextInputLayout namel, local,agel,phonel;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private Button btn;
   // private DatabaseReference databaseUser;


   @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        context = this;


       Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
       setSupportActionBar(toolbar);

       if (getSupportActionBar() != null){
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setDisplayShowHomeEnabled(true);
           getSupportActionBar().setTitle("Back");
           toolbar.setTitleTextColor(Color.WHITE);

       }

       toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.left_arrow));
       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               finish();
               startActivity(new Intent(register.this,eggrafh.class));

           }
       });

       // databaseUser= FirebaseDatabase.getInstance().getReference().child("Users");
      // databaseUser.keepSynced(true);

        namel=(TextInputLayout) findViewById(R.id.singup);
        local = (TextInputLayout) findViewById(R.id.loc);
        agel=(TextInputLayout)  findViewById(R.id.ag);
        phonel=(TextInputLayout)  findViewById(R.id.tele);

        mProgress=new ProgressDialog(this);
        name = (EditText) findViewById(R.id.name);
        lo = (EditText) findViewById(R.id.location);
        age=(EditText)  findViewById(R.id.age);
        phone=(EditText)  findViewById(R.id.phone);
        btn = (Button) findViewById(R.id.bntsign);
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        btn.setOnClickListener(this);

     }


    @Override
    public void onClick(View view) {
        btn.startAnimation(buttonClick);
                startRegister();
    }

    private void startRegister() {
        submitForm();
        
        if (!validate()) {
            onSignupFailed();
            return;
        }
        btn.setEnabled(false);
        WifiManager wifiManager=(WifiManager)  getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo=wifiManager.getConnectionInfo();
        MacAddress=wifiInfo.getMacAddress();

        nam = name.getText().toString();
        loc= lo.getText().toString();
        ag = age.getText().toString();
        ph  = phone.getText().toString();

        if(!TextUtils.isEmpty(nam)&&!TextUtils.isEmpty(loc)&&!TextUtils.isEmpty(ag)&&!TextUtils.isEmpty(ph)){

            final ProgressDialog progressDialog = new ProgressDialog(register.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Create User...");
            progressDialog.show();
            validate();

            /*final  DatabaseReference newPost=databaseUser.push();
            databaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("username").setValue(nam);
                    newPost.child("τοποθεσία").setValue(loc);
                    newPost.child("ηλικία").setValue(ag);
                    newPost.child("τηλέφωνο").setValue(ph);
                    newPost.child("MacAddress").setValue(MacAddress);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed 
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            mProgress.dismiss();
                            startActivity(new Intent(register.this,eggrafh.class));
                        }

                    }, 3000);

            //Intent intent_name = new Intent();
            //intent_name.putExtra("username", nam);
            //intent_name.putExtra("τηλέφωνο", ph);
            //intent_name.setClass(context, eggrafh.class);
            //context.startActivity(intent_name);

        }

    }



    private void onSignupFailed() {

        Toast.makeText(getBaseContext(), "Unsuccessfully Register!", Toast.LENGTH_LONG).show();
        btn.setEnabled(true);
    }
    private void onSignupSuccess() {
        Toast.makeText(getBaseContext(), "Successfully Register!", Toast.LENGTH_LONG).show();
        btn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public boolean validate() {
        boolean valid = true;

        String n = name.getText().toString();
        String address = lo.getText().toString();
        String a = age.getText().toString();
        String mobile = phone.getText().toString();
         int value=0;
        try {

            value = Integer.parseInt(a);

        } catch (NumberFormatException e) {
            System.out.println("error!");
        }

        if (n.isEmpty() || n.length() < 3) {
            name.setError("The name must contain at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (address.isEmpty()) {
            lo.setError("Write Available Location");
            valid = false;
        } else {
            lo.setError(null);
        }

        if (value < 18) {
            age.setError("Put a desired age of 18 years old and over");
            valid = false;
        } else {
            age.setError(null);
        }

        if (a.isEmpty()||value < 18) {
            age.setError("Put a desired age of 18 years and over");
            valid = false;
        } else {
            age.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            phone.setError("Write a desired ten-digit number");
            valid = false;
        } else {
            phone.setError(null);
        }
        return valid;
    }

    public  void submitForm(){

           if(!checkName()) {
               name.setAnimation(shake);
               name.startAnimation(shake);
               vib.vibrate(120);
               return;
           }

            if(!checkLocation()) {
                local.setAnimation(shake);
                local.startAnimation(shake);
                vib.vibrate(120);
                return;
            }

            if(!checkPhone()) {
                phone.setAnimation(shake);
                phone.startAnimation(shake);
                vib.vibrate(120);
                return;
            }

            namel.setErrorEnabled(false);
            agel.setErrorEnabled(false);
            phonel.setErrorEnabled(false);
            local.setErrorEnabled(false);

        }


    private boolean checkName() {
        if(name.getText().toString().trim().isEmpty()){

            namel.setErrorEnabled(true);
            namel.setError(getString(R.string.err_msg_name));
            return false;
        }
        namel.setErrorEnabled(false);
        return true;
    }

    private boolean checkLocation() {

        if (lo.getText().toString().trim().isEmpty()) {

            local.setErrorEnabled(true);
            local.setError(getString(R.string.err_msg_password));
            requestFocus(local);
            return false;
        }
        local.setErrorEnabled(false);
        return true;
    }

    private boolean checkPhone() {

        if (phone.getText().toString().trim().isEmpty()) {
            phonel.setErrorEnabled(true);
            phonel.setError(getString(R.string.err_msg_password));
            requestFocus(phonel);
            return false;
        }

        String regexStr = "[\\d]{10}";
        String number=phone.getText().toString();

        if(phone.getText().toString().length()<10 || number.length()>13 || number.matches(regexStr)==false  ) {
            Toast.makeText(getBaseContext(),"The number you entered was not feasible"+"\n"+"/=Please enter a desired number",Toast.LENGTH_SHORT).show();
            // am_checked=0;
        }

        phonel.setErrorEnabled(false);
        return true;
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


}


