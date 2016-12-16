package com.example.commontasker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Αρης on 26/7/2016.
 */

public class questions extends AppCompatActivity {

    private int year, month, day,hour,minute,hour1,minute1;
    private EditText text, text1,text3;
    private TextView text2,text4;
    private ImageButton imagebtn;
    private Button btn,photo;
    private Spinner spinner,spin;
    private static final int dialog = 0;
    private Calendar calendar = Calendar.getInstance();
    private static DatePickerDialog.OnDateSetListener datadial;
    private static final int GALLERY_REQUEST=1;
    private static final int fine=2;
    private Uri imageUri;
    String name;
    Context context;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private StorageReference storage;
    private DatabaseReference database;
    private ProgressDialog mProgress;
   private DatabaseReference databaseUsers;
    ArrayAdapter<CharSequence> arrayAdapter,arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

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
                startActivity(new Intent(questions.this,questionanswers.class));

            }
        });
        spinner=(Spinner) findViewById(R.id.spiner);
        spin=(Spinner) findViewById(R.id.spiner1);
        storage= FirebaseStorage.getInstance().getReference();
        database= FirebaseDatabase.getInstance().getReference().child("Questions");
        databaseUsers= FirebaseDatabase.getInstance().getReference().child("Users");
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        database.keepSynced(true);
        databaseUsers.keepSynced(true);

        context=this;

        text1 = (EditText) findViewById(R.id.editText2);
        text2 = (EditText) findViewById(R.id.editText6);
        text = (EditText) findViewById(R.id.editText5);
        text3 = (EditText) findViewById(R.id.editText);
        text4= (EditText) findViewById(R.id.autoCompleteTextView);
        imagebtn=(ImageButton)  findViewById(R.id.imageButton);


        btn=(Button) findViewById(R.id.bt);
        photo=(Button) findViewById(R.id.photobtn);
        mProgress=new ProgressDialog(this);

        arrayAdapter=ArrayAdapter.createFromResource(this,R.array.erwthseis,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        arrayAdapter1=ArrayAdapter.createFromResource(this,R.array.area_names,android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text2.setText(spinner.getAdapter().getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text4.setText(spin.getAdapter().getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);
        hour1 = calendar.get(Calendar.HOUR_OF_DAY);
        minute1=calendar.get(Calendar.MINUTE);

        setCurrentDateOnView();
        print();
        datadial = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearr, int monthh, int dayy) {
                year = yearr;
                month = monthh;
                day = dayy;
                print();
            }
        };

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,GALLERY_REQUEST);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });

        addListenerOnButtonn();
    }


    private void addListenerOnButtonn() {
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), camera.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
    }

    private void startPosting() {

        mProgress.setTitle("Question Created!");

        final String title=text2.getText().toString().trim();
        final String perigrafh=text3.getText().toString().trim();
        final String xronos=text1.getText().toString().trim();
        final String hmeromhnia=text.getText().toString().trim();
        final String topothesia=text4.getText().toString().trim();

        if(title.equals("")){
            Toast.makeText(questions.this,"Title can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(perigrafh.equals("")){
            Toast.makeText(questions.this,"Description can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(hmeromhnia.equals("")){
            Toast.makeText(questions.this,"Date can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(xronos.equals("")){
            Toast.makeText(questions.this,"Time can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(topothesia.equals("")){
            Toast.makeText(questions.this,"Location can't be null" ,Toast.LENGTH_LONG).show();
        }


        if(!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(xronos)&&!TextUtils.isEmpty(hmeromhnia)
                &&!TextUtils.isEmpty(perigrafh)&&!TextUtils.isEmpty(topothesia)){

            mProgress.show();
            StorageReference filepath=storage.child("Questions").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    final  Uri downloadUri=taskSnapshot.getDownloadUrl();
                    final  DatabaseReference newPost=database.push();

                    databaseUsers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            newPost.child("title").setValue(title);
                            newPost.child("time").setValue(xronos);
                            newPost.child("date").setValue(hmeromhnia);
                            newPost.child("description").setValue(perigrafh);
                            newPost.child("location").setValue(topothesia);
                            newPost.child("image").setValue(downloadUri.toString());

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    mProgress.dismiss();

                    startActivity(new Intent(questions.this, answer.class));
                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST&&resultCode==RESULT_OK){
            imageUri=data.getData();
           imagebtn.setImageURI(imageUri);
        }
        if(requestCode == 2) {

            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(getApplicationContext(), maintask.class);
                startActivity(intent);
            }
            else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(getApplicationContext(), "Η εγγραφή ακυρώθηκε από τον χρήστη", Toast.LENGTH_LONG).show();
            }
            imageUri=data.getData();
            imagebtn.setImageURI(imageUri);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    // gotoLogin();
    }

    private void gotoLogin() {

        Toast.makeText(questions.this, "Για οποιαδήποτε ένεργεια είτε δημιουργώντας μία ερώτηση είτε απαντώντας μία ερώτηση θα πρέπει να είστε εγγεγραμμένος χρήστης!", Toast.LENGTH_LONG).show();
        Intent mainIntent=new Intent(questions.this,eggrafh.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(mainIntent, 2);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                // set date picker as current date
                DatePickerDialog _date = new DatePickerDialog(this, datePickerListener, year, month,
                        day) {
                    @Override
                    public void onDateChanged(DatePicker view, int yearr, int monthOfYear, int dayOfMonth) {
                        if (yearr < year)
                            view.updateDate(year, month, day);

                        if (monthOfYear < month && yearr == year)
                            view.updateDate(year, month, day);

                        if (dayOfMonth < day && yearr == year && monthOfYear == month)
                            view.updateDate(year, month, day);

                    }
                };
                return _date;
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            text.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));

        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            setCurrentDateOnView();

        }
    };

    public void timeOnClick(View view) {
        new TimePickerDialog(this, time, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false).show();
    }
    public void print() {
        text.setText("Date: " + day + "-" + (month + 1) + "-" + year);
    }

    public void setCurrentDateOnView() {
        Locale locale = new Locale("el-GR");
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, locale.US);
        text1.setText(stf.format(calendar.getTime()));

    }

    public void Calenderprint(View control) {

        showDialog(dialog);

    }


}
