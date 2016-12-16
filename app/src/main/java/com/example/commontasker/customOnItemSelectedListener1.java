package com.example.commontasker;

/**
 * Created by Αρης on 1/7/2016.
 */
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class customOnItemSelectedListener1 extends MainActivity implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
