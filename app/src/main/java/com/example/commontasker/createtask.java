package com.example.commontasker;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class createtask extends ActivityGroup {

   TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtask);

        tabHost=(TabHost)findViewById(R.id.tabHost);

        tabHost.setup(this.getLocalActivityManager());
        TabHost.TabSpec tabSpec =tabHost.newTabSpec("Tab One");
        tabSpec.setContent(new Intent(this,HomeFrag.class));
        tabSpec.setIndicator("ELEMENTS OF TASK");
        tabHost.addTab(tabSpec);

       /* TabHost.TabSpec tabSpec2 =tabHost.newTabSpec("Tab One");
        tabSpec2.setContent(new Intent(this,TopPaidFragment.class));
        tabSpec2.setIndicator("ΧΑΡΤΗΣ");
        tabHost.addTab(tabSpec2);*/

    }

}
