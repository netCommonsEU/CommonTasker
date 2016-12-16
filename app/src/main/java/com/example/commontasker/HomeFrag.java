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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFrag extends AppCompatActivity {

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

   private DatabaseReference database;
   private StorageReference storage;

    private ProgressDialog mProgress;

    ArrayAdapter<CharSequence> arrayAdapter,arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        /*String DB_URL = "jdbc:mysql://192.168.1.103";
        String USER = "root";
        String PASS = "root";

        Connection conn = null;


        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        java.sql.Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;

        sql="SELECT * FROM checkins where POI_category_id=";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while(rs.next()){
                rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        spinner=(Spinner) findViewById(R.id.spiner);
        spin=(Spinner) findViewById(R.id.spiner1);

        storage= FirebaseStorage.getInstance().getReference();
        database= FirebaseDatabase.getInstance().getReference().child("Tasks");
       database.keepSynced(true);

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
                startActivity(new Intent(HomeFrag.this,ergo.class));

            }
        });


        text1 = (EditText) findViewById(R.id.editText2);
        text2 = (EditText) findViewById(R.id.editText6);
        text = (EditText) findViewById(R.id.editText5);
        text3 = (EditText) findViewById(R.id.editText);
        text4= (EditText) findViewById(R.id.autoCompleteTextView);
        text5= (EditText) findViewById(R.id.editText7);
        imagebtn=(ImageButton)  findViewById(R.id.imageButton);

        btn=(Button) findViewById(R.id.bt);
        photo=(Button) findViewById(R.id.photobtn);
        mProgress=new ProgressDialog(this);

        arrayAdapter=ArrayAdapter.createFromResource(this,R.array.erga,android.R.layout.simple_spinner_item);
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
                try {
                   // Store();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        mProgress.setTitle("Create Task");

        final String title = text2.getText().toString();
        final String perigrafh = text3.getText().toString();
        final String xronos = text1.getText().toString();
        final String hmeromhnia = text.getText().toString();
        final String topothesia = text4.getText().toString();

       if(title.equals("")){
           Toast.makeText(HomeFrag.this,"Title can't be null" ,Toast.LENGTH_LONG).show();
       }
       if(perigrafh.equals("")){
           Toast.makeText(HomeFrag.this,"Description can't be null" ,Toast.LENGTH_LONG).show();
       }
       if(hmeromhnia.equals("")){
           Toast.makeText(HomeFrag.this,"Date can't be null" ,Toast.LENGTH_LONG).show();
       }
       if(xronos.equals("")){
           Toast.makeText(HomeFrag.this,"Time can't be null" ,Toast.LENGTH_LONG).show();
       }
       if(topothesia.equals("")){
           Toast.makeText(HomeFrag.this,"Location can't be null" ,Toast.LENGTH_LONG).show();
       }

       if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(xronos) && !TextUtils.isEmpty(hmeromhnia)
                && !TextUtils.isEmpty(perigrafh) && !TextUtils.isEmpty(topothesia)) {

            mProgress.show();


           StorageReference filepath=storage.child("Tasks").child(imageUri.getLastPathSegment());
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    final  Uri downloadUri=taskSnapshot.getDownloadUrl();
                    final  DatabaseReference newPost=database.push();

                    database.addValueEventListener(new ValueEventListener() {
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

                    startActivity(new Intent(HomeFrag.this, task.class));
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


    public void setCurrentDateOnView() {
        Locale locale = new Locale("el-GR");
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, locale.US);
        text1.setText(stf.format(calendar.getTime()));

    }


    public void Calenderprint(View control) {
        showDialog(dialog);

    }

   /* private void Store() throws  Exception{
        String userName = "sa";
        String password = "root";
        //String DB_URL = "jdbc:jtds:sqlserver://10.25.141.248:1993;databaseName=CommonTasker;integratedSecurity=true";
        String url = "jdbc:sqlserver://10.25.141.248:1992;databaseName=CommonTasker;user=sa;password=root;integratedSecurity=true;";

        //String DB_URL = "jdbc:sqlserver://MARINA-MATTY\\SQLEXPRESS;databaseName=CommonTasker;integratedSecurity=true";
       //localhost;integratedSecurity=true;
        //  Database credentials
        //jdbc:sqlserver://localhost;integratedSecurity=true;
        //String DB_URL = "jdbc:mysql://10.25.141.248";

        Connection conn=null;

        mProgress.setTitle("Δημιουργία Έργου");

        final String title = text2.getText().toString();
        final String perigrafh = text3.getText().toString();
        final String xronos = text1.getText().toString();
        final String hmeromhnia = text.getText().toString();
        final String topothesia = text4.getText().toString();

        java.sql.Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           //' Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //STEP 3: Open a connection
        conn = DriverManager.getConnection(url, userName, password);
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String query = "INSERT INTO task values(?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement

            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, title);
            preparedStmt.setString(2, perigrafh);
            preparedStmt.setString(3, xronos);
            preparedStmt.setString(4, hmeromhnia);
            preparedStmt.setString(5,topothesia );

             System.out.println(" " + title );

            // execute the preparedstatement
            preparedStmt.execute();

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try*/




}


