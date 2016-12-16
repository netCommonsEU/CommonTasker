package com.example.commontasker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    public static final String DATABASE_NAME="commontasker.db";

    private static final String CREATE_QUERY="CREATE TABLE IF NOT EXISTS "+UserContact.UserEntry.TABLE_NAME+"("+UserContact.UserEntry.ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +UserContact.UserEntry.NAME+" string TEXT,"
            +UserContact.UserEntry.LOCATION +" string TEXT,"+UserContact.UserEntry.AGE +" INTEGER,"+UserContact.UserEntry.PHONE +" string  TEXT,"
            +UserContact.UserEntry.SKILLS+" string TEXT);";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DB_VERSION);
      Log.d("Database operations","creating!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations","table creating!");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +UserContact.UserEntry.TABLE_NAME);
        this.onCreate(sqLiteDatabase);

    }

    public void AddInformations(DatabaseHelper db, String name, String location, String age, String phone, String skills) {
       SQLiteDatabase sqLiteDatabase=db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.UserEntry.NAME,name);
        contentValues.put(UserContact.UserEntry.LOCATION,location);
        contentValues.put(UserContact.UserEntry.AGE,age);
        contentValues.put(UserContact.UserEntry.PHONE,phone);
        contentValues.put(UserContact.UserEntry.SKILLS, skills);

       long k= sqLiteDatabase.insert(UserContact.UserEntry.TABLE_NAME,null ,contentValues);

        Log.d("Database Operations...","One row inserting");
    }
    public Cursor getInformation(DatabaseHelper databaseHelper){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
          String[]columns={UserContact.UserEntry.NAME,UserContact.UserEntry.PHONE};
           Cursor cursor=sqLiteDatabase.query(UserContact.UserEntry.TABLE_NAME,columns,null,null,null,null,null);
           return cursor;
    }




}