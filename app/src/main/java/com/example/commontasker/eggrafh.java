package com.example.commontasker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;*/

import butterknife.ButterKnife;

public class eggrafh extends AppCompatActivity implements View.OnClickListener {

    Animation shake;
    private Vibrator vib;
    private EditText name, phone;
    private TextView textlink, textlink2;
    private TextInputLayout namel, phonel;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private Button btn;
    String phon, nam;
    String MacAddress;
    View view;
   // private DatabaseReference databaseUsers;
   // private DatabaseReference databaseChild;
    Intent fromQuestions;
    Context context;
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eggrafh);
        ButterKnife.bind(this);
        context = this;
        namel = (TextInputLayout) findViewById(R.id.singup);
        phonel = (TextInputLayout) findViewById(R.id.tele);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        textlink = (TextView) findViewById(R.id.textlink1);
        textlink2 = (TextView) findViewById(R.id.textlink2);
        btn = (Button) findViewById(R.id.bntsign);

       // databaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        //databaseChild = FirebaseDatabase.getInstance().getReference().child("Users").child("username");
        //databaseUsers.keepSynced(true);

        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        textlink.setOnClickListener(this);
        textlink2.setOnClickListener(this);
        btn.setOnClickListener(this);

        fromQuestions = getIntent();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bntsign:
                //checkLogin();
                startActivity(new Intent(eggrafh.this,maintask.class));
                break;
            case R.id.textlink1:
                textlink.setClickable(true);
                textlink.setMovementMethod(LinkMovementMethod.getInstance());
                Intent intent = new Intent(this, register.class);
                view.startAnimation(buttonClick);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;

            case R.id.textlink2:
                textlink.setClickable(true);
                textlink.setMovementMethod(LinkMovementMethod.getInstance());
                Intent intent1 = new Intent(this, FbActivity.class);
                view.startAnimation(buttonClick);
                startActivity(intent1);
                break;

            default:
                break;

        }
        view.startAnimation(buttonClick);
    }

    @Override
    protected void onStart() {

        Context con;
        con = this;
        super.onStart();
        isConnected(con);

    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if ((wifiInfo != null && wifiInfo.isConnected())) {
            return true;
        } else {
            showDialog();
            return false;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to activate the wifi to perform the authentication of your data;")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      //  startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkLogin() {

        if (!validate()) {
            onLoginFailed();
            return;
        }
        try {

            checkUserExits();
        } catch (Exception ex) {

            System.out.println("Εξαίρεση!\n");

        }
    }

    private void checkUserExits() {

          WifiManager wifiManager=(WifiManager)  getSystemService(Context.WIFI_SERVICE);
          WifiInfo wifiInfo=wifiManager.getConnectionInfo();
          MacAddress=wifiInfo.getMacAddress();

        phon = phone.getText().toString();
        nam = name.getText().toString();

       // Query query = databaseUsers.orderByChild("τηλέφωνο").equalTo(phon);

       /* query.addListenerForSingleValueEvent(new ValueEventListener() {
            String userTest = null;
            boolean yparxon = false;

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot user : snapshot.getChildren()) {
                    userTest = (String) user.child("τηλέφωνο").getValue();
                    if (userTest.equals(phon)) {
                        yparxon = true;
                        break;
                    }
                    else {
                        yparxon = false;
                    }
                }

                if (yparxon) {
                    Toast.makeText(getBaseContext(), "Επιτυχής Αυθεντικοποίηση Χρήστη και Ενημέρωση Στοιχείων του Χρήστη!", Toast.LENGTH_LONG).show();
                    setResult(0, fromQuestions);
                    startActivity(new Intent(eggrafh.this,maintask.class));

                   Intent intent_name = new Intent();
                   intent_name.putExtra("username", nam);
                   intent_name.putExtra("τηλέφωνο", phon);
                   intent_name.setClass(context, eggrafh.class);

                    context.startActivity(intent_name);
                    finish();
                }
                else {
                    Toast.makeText(eggrafh.this, "Πρέπει να τσεκάρετε το λογαριασμό σας!Εαν δεν έχετε εγγραφεί παρακαλώ εγγραφείτε", Toast.LENGTH_LONG).show();
                    phone.setText("");
                    name.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Unsuccessful Data Update User! Please try again!", Toast.LENGTH_LONG).show();
        btn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String nam = name.getText().toString();
        String ph = phone.getText().toString();

        if (nam.isEmpty()) {
            name.setError("Name can't be null");
            valid = false;
        } else {
            name.setError(null);
        }

        if (ph.isEmpty() || ph.length() > 10) {
            phone.setError("The phone must have 10 numbers!");
            valid = false;
        } else {
            phone.setError(null);
        }

        return valid;
    }

    public void submitForm() {

        if (!checkPhone()) {
            phonel.setAnimation(shake);
            phonel.startAnimation(shake);
            return;
        }
        phonel.setAnimation(shake);
        phonel.setErrorEnabled(false);

    }


    private boolean checkPhone() {

        String regexStr = "[\\d]{10}";
        String number = phone.getText().toString();

        if (phone.getText().toString().length() < 10 || number.length() > 13 || number.matches(regexStr) == false) {
            Toast.makeText(getBaseContext(), "The number you entered was not feasible " + "\n" + "/=Please enter a desired number", Toast.LENGTH_SHORT).show();
            // am_checked=0;
        }
        return true;
    }


}

