package com.example.commontasker;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Αρης on 7/8/2016.
 */
public class moirasou extends ActivityGroup {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moirasou);

        tabHost=(TabHost)findViewById(R.id.tabHost);

        tabHost.setup(this.getLocalActivityManager());
        TabHost.TabSpec tabSpec =tabHost.newTabSpec("Tab One");
        tabSpec.setContent(new Intent(this,moirasou1.class));
        tabSpec.setIndicator("ELEMENTS OF OBJECT");
        tabHost.addTab(tabSpec);

    }

}
