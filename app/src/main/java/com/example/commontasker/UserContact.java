package com.example.commontasker;

import android.provider.BaseColumns;

/**
 * Created by Αρης on 24/9/2016.
 */
public final  class UserContact {
    public static abstract class UserEntry implements BaseColumns {
        public static final String ID="id";
        public static final String NAME="name";
        public static final String LOCATION="location";
        public static final String AGE="age";
        public static final String PHONE="phone";
        public static final String SKILLS="skills";
        public static final String TABLE_NAME ="user";
    }
}
