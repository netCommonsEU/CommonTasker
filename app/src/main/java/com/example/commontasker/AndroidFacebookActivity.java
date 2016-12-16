package com.example.commontasker;

import android.os.Bundle;


import com.facebook.FacebookSdk;

public class AndroidFacebookActivity extends android.support.v4.app.FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.fb);
    }
}