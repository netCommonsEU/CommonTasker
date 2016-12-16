package com.example.commontasker.discovery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.commontasker.R;
import com.example.commontasker.discovery.chatmessages.WiFiChatFragment;
import com.example.commontasker.discovery.services.WiFiP2pServicesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Getter;


public class TabFragment extends Fragment {

    @Getter
    private SectionsPagerAdapter mSectionsPagerAdapter;
    @Getter
    private ViewPager mViewPager;
    @Getter
    private static WiFiP2pServicesFragment wiFiP2pServicesFragment;
    @Getter
    private static List<WiFiChatFragment> wiFiChatFragmentList;

    /**
     * Method to obtain a new Fragment's instance.
     *
     * @return This Fragment instance.
     */
    public static TabFragment newInstance() {
        TabFragment fragment = new TabFragment();
        wiFiP2pServicesFragment = WiFiP2pServicesFragment.newInstance();
        wiFiChatFragmentList = new ArrayList<>();
        return fragment;
    }

    /**
     * Default Fragment constructor.
     */
    public TabFragment() {
    }

    public static WiFiP2pServicesFragment getWiFiP2pServicesFragment() {
        return wiFiP2pServicesFragment;
    }

    public static List<WiFiChatFragment> getWiFiChatFragmentList() {
        return wiFiChatFragmentList;
    }


    public WiFiChatFragment getChatFragmentByTab(int tabNumber) {
        return wiFiChatFragmentList.get(tabNumber - 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);


        // When swiping between different sections, select the corresponding
        // tab.
        tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mSectionsPagerAdapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }


    public boolean isValidTabNum (int tabNum) {
        return tabNum >= 1 && tabNum <= wiFiChatFragmentList.size();
    }

    public ViewPager getMViewPager() {
        return mViewPager;
    }

    public SectionsPagerAdapter getMSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return wiFiP2pServicesFragment; //the first fragment reserved to the serviceListFragment
            } else {
                return wiFiChatFragmentList.get(position - 1); //chatFragments associated to this position
            }
        }

        @Override
        public int getCount() {
            //because the first fragment (not inside into the list) is a WiFiP2pServicesFragment
            return wiFiChatFragmentList.size() + 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return ("Services").toUpperCase(l);
                default:

                    return ("Chat" + position).toUpperCase(l);
            }
        }
    }
}
