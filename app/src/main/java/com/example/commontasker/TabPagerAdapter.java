package com.example.commontasker;

/**
 * Created by Αρης on 3/10/2016.
 */
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;

    public class TabPagerAdapter extends FragmentPagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            Bundle bundle = new Bundle();
            String tab = "";
            int colorResId = 0;
            switch (index) {
                case 0:
                    tab = "List of Missed Calls";
                    colorResId = R.color.color1;
                    break;
                case 1:
                    tab = "List of Dialled Calls";
                    colorResId = R.color.color2;
                    break;
                case 2:
                    tab = "List of Received Calls";
                    colorResId = R.color.color3;
                    break;
            }
            bundle.putString("tab",tab);
            bundle.putInt("color", colorResId);
            SwipeTabFragment swipeTabFragment = new SwipeTabFragment();
            swipeTabFragment.setArguments(bundle);
            return swipeTabFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

