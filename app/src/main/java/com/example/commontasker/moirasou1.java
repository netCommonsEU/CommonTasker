package com.example.commontasker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Αρης on 10/10/2016.
 */
public class moirasou1 extends AppCompatActivity implements View.OnClickListener {

    private int year, month, day,hour,minute,hour1,minute1;
    private EditText text, text1,text3,text5;
    private TextView text2,text4;
    private ImageButton imagebtn;
    private Button btn,photo;
    private Spinner spinner,spin;
    private static final int dialog = 0;
    private Calendar calendar = Calendar.getInstance();
    private static DatePickerDialog.OnDateSetListener datadial;
    private static final int GALLERY_REQUEST=1;
    private Uri imageUri;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
   private StorageReference storage;
    private DatabaseReference database;
    private DatabaseReference databaseUSers;
    private ProgressDialog mProgress;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    ArrayAdapter<CharSequence> arrayAdapter,arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.moirasou_tab1);

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
                startActivity(new Intent(moirasou1.this,common.class));

            }
        });



        spinner=(Spinner) findViewById(R.id.spiner);
        spin=(Spinner) findViewById(R.id.spiner1);

        storage= FirebaseStorage.getInstance().getReference();
        database= FirebaseDatabase.getInstance().getReference().child("Antikeimena");
       //databaseUSers = FirebaseDatabase.getInstance().getReference().child("Users");
       // databaseUSers.keepSynced(true);
        text1 = (EditText) findViewById(R.id.editText2);
        text2 = (EditText) findViewById(R.id.editText6);
        text = (EditText) findViewById(R.id.editText5);
        text3 = (EditText) findViewById(R.id.editText);
        text4= (EditText) findViewById(R.id.autoCompleteTextView);
        text5= (EditText) findViewById(R.id.editText7);
        imagebtn=(ImageButton)  findViewById(R.id.imageButton);
        Locale locale = new Locale("el-GR");
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", locale);


        setDateTimeField();

        btn=(Button) findViewById(R.id.bt);
        photo=(Button) findViewById(R.id.photobtn);
        mProgress=new ProgressDialog(this);

        arrayAdapter=ArrayAdapter.createFromResource(this,R.array.mhxanimata,android.R.layout.simple_spinner_item);
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
        print1();

        datadial = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int yearr, int monthh, int dayy) {
                year = yearr;
                month = monthh;
                day = dayy;
                print();
                print1();

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


    private void setDateTimeField() {

        text.setOnClickListener(this);
        text5.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                text.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                text5.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }



    private void startPosting() {
        mProgress.setTitle("Tool Created");

        final String title=text2.getText().toString();
        final String perigrafh=text3.getText().toString();
        final String xronos=text1.getText().toString();
        final String hmeromhniarxhs=text.getText().toString();
        final String hmeromhniatelous=text5.getText().toString();
        final String topothesia=text4.getText().toString();

        if(title.equals("")){
            Toast.makeText(moirasou1.this,"Title can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(perigrafh.equals("")){
            Toast.makeText(moirasou1.this,"Description can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(hmeromhniarxhs.equals("")){
            Toast.makeText(moirasou1.this,"Date can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(hmeromhniatelous.equals("")){
            Toast.makeText(moirasou1.this,"Date can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(xronos.equals("")){
            Toast.makeText(moirasou1.this,"Time can't be null" ,Toast.LENGTH_LONG).show();
        }
        if(topothesia.equals("")){
            Toast.makeText(moirasou1.this,"Location can't be null" ,Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(xronos)&&!TextUtils.isEmpty(hmeromhniarxhs)
                &&!TextUtils.isEmpty(hmeromhniatelous)&&!TextUtils.isEmpty(perigrafh)&&!TextUtils.isEmpty(topothesia)){

            mProgress.show();
            StorageReference filepath=storage.child("Tools").child(imageUri.getLastPathSegment());
           filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri=taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost=database.push();
                    newPost.child("title").setValue(title);
                    newPost.child("time").setValue(xronos);
                    newPost.child("start_date").setValue(hmeromhniarxhs);
                    newPost.child("last_date").setValue(hmeromhniatelous);
                    newPost.child("description").setValue(perigrafh);
                    newPost.child("location").setValue(topothesia);
                    newPost.child("image").setValue(downloadUri.toString());

                    mProgress.dismiss();

                    startActivity(new Intent(moirasou1.this,diathesimothta.class));
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

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            text.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            text.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
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
        text.setText(day + "-" + (month + 1) + "-" + year);
    }
    public void print1() {
        text.setText(day + "-" + (month + 1) + "-" + year);
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

    @Override
    public void onClick(View v) {
        if(v == text) {
            fromDatePickerDialog.show();
        } else if(v == text5) {
            toDatePickerDialog.show();
        }
    }
}
