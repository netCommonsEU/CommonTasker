package com.example.commontasker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Αρης on 7/10/2016.
 */
public class erwthsh2 extends FragmentActivity {

    private Button button;
    private Button button1;
    String []areas;
    private EditText text;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.erwthsh2);

        addListenerOnButton5();

        text = (EditText) findViewById(R.id.editText6);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        areas = getResources().getStringArray(R.array.area_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areas);
        autoCompleteTextView.setAdapter(adapter);

    }
    // An AsyncTask class for accessing the GeoCoding Web Service

    public void addListenerOnButton5() {
        //Select a specific button to bundle it with the action you want
        button = (Button) findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ergo.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });

        button1 = (Button) findViewById(R.id.button8);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), createtask.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
    }
}
