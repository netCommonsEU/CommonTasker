package com.example.commontasker;

/**
 * Created by Αρης on 28/10/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class answer extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;

   private DatabaseReference database;
 private DatabaseReference databaselike;

    HashMap<String, List<String>> listDataChild;

    private  boolean process=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
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
                startActivity(new Intent(answer.this,questionanswers.class));

            }
        });


       database = FirebaseDatabase.getInstance().getReference().child("Questions");
       databaselike= FirebaseDatabase.getInstance().getReference().child("Likes");

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                v.setSelected(true);
                Intent intent_name = new Intent(getApplicationContext(), AnswerDetails.class);

                if(groupPosition == 0){


                    intent_name.putExtra("description", food1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", food1.get(childPosition).getImage());
                    intent_name.putExtra("title", food1.get(childPosition).getTitlos());
                    intent_name.putExtra("time", food1.get(childPosition).getTime());
                    intent_name.putExtra("date", food1.get(childPosition).getDate());
                    intent_name.putExtra("location", food1.get(childPosition).getLocation());


                    startActivity(intent_name);
                }

                else if(groupPosition == 1){

                    intent_name.putExtra("description", economy1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", economy1.get(childPosition).getImage());
                    intent_name.putExtra("title", economy1.get(childPosition).getTitlos());
                    intent_name.putExtra("time", economy1.get(childPosition).getTime());
                    intent_name.putExtra("date", economy1.get(childPosition).getDate());
                    intent_name.putExtra("location", economy1.get(childPosition).getLocation());

                    startActivity(intent_name);
                }

                else if(groupPosition == 2){
                    intent_name.putExtra("description", health1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", health1.get(childPosition).getImage());
                    intent_name.putExtra("title", health1.get(childPosition).getTitlos());
                    intent_name.putExtra("time", health1.get(childPosition).getTime());
                    intent_name.putExtra("date", health1.get(childPosition).getDate());
                    intent_name.putExtra("location", health1.get(childPosition).getLocation());
                    intent_name.setClass(getApplicationContext(), AnswerDetails.class);

                    startActivity(intent_name);
                }

                else if(groupPosition == 3){
                    intent_name.putExtra("description", social1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", social1.get(childPosition).getImage());
                    intent_name.putExtra("title", social1.get(childPosition).getTitlos());
                    intent_name.putExtra("time", social1.get(childPosition).getTime());
                    intent_name.putExtra("date", social1.get(childPosition).getDate());
                    intent_name.putExtra("location", social1.get(childPosition).getLocation());
                    intent_name.setClass(getApplicationContext(), AnswerDetails.class);

                    startActivity(intent_name);
                }
                return false;
            }
        });
    }

    private List<String> food = new ArrayList<String>();
    private List<String> economy = new ArrayList<String>();
    private List<String>  health= new ArrayList<String>();
    private List<String> social = new ArrayList<String>();


    private List<Questionitem> food1 = new ArrayList<Questionitem>();
    private List<Questionitem> economy1 = new ArrayList<Questionitem>();
    private List<Questionitem> health1 = new ArrayList<Questionitem>();
    private List<Questionitem> social1 = new ArrayList<Questionitem>();

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Food");
        listDataHeader.add("Politics");
        listDataHeader.add("Health");
        listDataHeader.add("Social");

        Query query = database;

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot Question : snapshot.getChildren()) {
                    if(Question.child("title").getValue().equals("Food")){
                        food.add(Question.child("description").getValue().toString().trim());
                        Questionitem add = new Questionitem(Question.child("title").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("image").getValue().toString().trim(),Question.child("date").getValue().toString().trim(),Question.child("description").getValue().toString().trim());
                        food1.add(add);

                    }
                     if(Question.child("title").getValue().equals("Politics")){
                        economy.add(Question.child("description").getValue().toString().trim());
                        Questionitem add = new Questionitem(Question.child("title").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("image").getValue().toString().trim(),Question.child("date").getValue().toString().trim(),Question.child("description").getValue().toString().trim());
                        economy1.add(add);
                    }
                    else if(Question.child("title").getValue().equals("Health")){
                        health.add(Question.child("description").getValue().toString().trim());
                        Questionitem add = new Questionitem(Question.child("title").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("image").getValue().toString().trim(),Question.child("date").getValue().toString().trim(),Question.child("description").getValue().toString().trim());
                        health1.add(add);
                    }
                    else if(Question.child("title").getValue().equals("Social")){
                        social.add(Question.child("description").getValue().toString().trim());
                        Questionitem add = new Questionitem(Question.child("title").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("image").getValue().toString().trim(),Question.child("date").getValue().toString().trim(),Question.child("description").getValue().toString().trim());
                        social1.add(add);
                    }
                }

                if(food.isEmpty())
                    food.add("Empty");
                if(economy.isEmpty())
                    economy.add("Empty");
                if(health.isEmpty())
                    health.add("Empty");
                if(social.isEmpty())
                    social.add("Empty");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listDataChild.put(listDataHeader.get(0), food); // Header, Child data
        listDataChild.put(listDataHeader.get(1), economy);
        listDataChild.put(listDataHeader.get(2), health);
        listDataChild.put(listDataHeader.get(3), social);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bottom, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch(item.getItemId()){
            case R.id.action_facebook:
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("http://www.facebook.com"));
                startActivity(it);
                break;
            case R.id.action_phone:
                Intent intent = new Intent(this, sos.class);
                startActivity(intent);
                break;
            case R.id.action_home:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_login:
                Intent intent2 = new Intent(this, eggrafh.class);
                startActivity(intent2);
                break;
            case R.id.logout:
                logout();
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Do you want to quit from app ?");
        alertDialogBuilder
                .setMessage("Press  YES to quit!")
                .setCancelable(false)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(answer.this,"Logout SuccessFull!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(answer.this,eggrafh.class));
                            }
                        })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
