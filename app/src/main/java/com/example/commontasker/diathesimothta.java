package com.example.commontasker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Αρης on 1/7/2016.
 */

public class diathesimothta extends AppCompatActivity {

    HashMap<String, List<String>> Categories;
    List<String> catogories_list;
    ExpandableListView expandableListView;

    CategoriesCommonAdapter adapter;
    private DatabaseReference databaseReference;
  // private DatabaseReference databaselike;

    private  boolean process=false;
   private DatabaseReference database;
    private Button button1;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    HashMap<String, List<String>> listDataChild;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diathesimothta);


        database= FirebaseDatabase.getInstance().getReference().child("Antikeimena");
        //databaselike= FirebaseDatabase.getInstance().getReference().child("Likes");


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
                startActivity(new Intent(diathesimothta.this,common.class));

            }
        });

        /*expandableListView = (ExpandableListView) findViewById(R.id.expandableListView2);
        Categories = QuestionProvider.getInfo();
        catogories_list = new ArrayList<String>(Categories.keySet());
        adapter = new CategoriesCommonAdapter(this, Categories, catogories_list);
        expandableListView.setAdapter(adapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(getBaseContext(), "Επιλέξατε την κατηγορία " + catogories_list.get(i), Toast.LENGTH_LONG).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(getBaseContext(), "Aφήσατε την κατηγορία " + catogories_list.get(i), Toast.LENGTH_LONG).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int grouposition, int childposition, long id) {
                view.setSelected(true);
                Toast.makeText(getBaseContext(), "Επιλέξατε ότι θέλετε να αξιοποίησετε προς οφελός σας το διαθέσιμο " + Categories.get(catogories_list.get(grouposition)).get(childposition) + " απο την Κατηγορία " + catogories_list.get(grouposition), Toast.LENGTH_LONG).show();
                return false;
            }
        });*/

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                v.setSelected(true);

                Intent intent_name = new Intent(getApplicationContext(), AntikeimenoDetails.class);

                if(groupPosition == 0){
                    //intent_name = new Intent(getApplicationContext(), AnswerDetails.class);

                    intent_name.putExtra("description", mhxanhmata1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", mhxanhmata1.get(childPosition).getImage());
                    intent_name.putExtra("title", mhxanhmata1.get(childPosition).getTitle());
                    intent_name.putExtra("time", mhxanhmata1.get(childPosition).getTime());
                    intent_name.putExtra("start_date", mhxanhmata1.get(childPosition).getDatearxh());
                    intent_name.putExtra("last_date", mhxanhmata1.get(childPosition).getDatetel());
                    intent_name.putExtra("location", mhxanhmata1.get(childPosition).getLocation());

//                    intent_name.setClass(getApplicationContext(), AnswerDetails.class);
//                    intent_name.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent_name);
                }

                else if(groupPosition == 1){
//                    Intent intent_name = new Intent();
                    intent_name.putExtra("description", lipasmata1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", lipasmata1.get(childPosition).getImage());
                    intent_name.putExtra("title", lipasmata1.get(childPosition).getTitle());
                    intent_name.putExtra("time", lipasmata1.get(childPosition).getTime());
                    intent_name.putExtra("start_date", lipasmata1.get(childPosition).getDatearxh());
                    intent_name.putExtra("last_date", lipasmata1.get(childPosition).getDatetel());
                    intent_name.putExtra("location", lipasmata1.get(childPosition).getLocation());

//                    intent_name.setClass(getApplicationContext(), AnswerDetails.class);
//                    intent_name.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent_name);
                }

                else if(groupPosition == 2){
                    intent_name.putExtra("description", sporoi1.get(childPosition).getPerigrafh());
                    intent_name.putExtra("image", sporoi1.get(childPosition).getImage());
                    intent_name.putExtra("title", sporoi1.get(childPosition).getTitle());
                    intent_name.putExtra("time", sporoi1.get(childPosition).getTime());
                    intent_name.putExtra("start_date", sporoi1.get(childPosition).getDatearxh());
                    intent_name.putExtra("last_date", sporoi1.get(childPosition).getDatetel());
                    intent_name.putExtra("location", sporoi1.get(childPosition).getLocation());

//                    intent_name.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    getApplicationContext().startActivity(intent_name);
                    startActivity(intent_name);
                }


                return false;
            }
        });



    }

    private void prepareListData() {


        listDataHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Tools");
        listDataHeader.add("Machinery");
        listDataHeader.add("Fertilizers");

        Query query = database;

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot Question : snapshot.getChildren()) {
                    if(Question.child("title").getValue().equals("Tools")){
                        mhxanhmata.add(Question.child("description").getValue().toString().trim());
                        AntikeimenoItem add = new AntikeimenoItem(Question.child("image").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("description").getValue().toString().trim(),Question.child("title").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("start_date").getValue().toString().trim(),Question.child("last_date").getValue().toString().trim());
                        mhxanhmata1.add(add);

                    }
                    else if(Question.child("title").getValue().equals("Machinery")){
                        lipasmata.add(Question.child("description").getValue().toString().trim());
                        AntikeimenoItem add = new AntikeimenoItem(Question.child("image").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("description").getValue().toString().trim(),Question.child("title").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("start_date").getValue().toString().trim(),Question.child("last_date").getValue().toString().trim());
                        lipasmata1.add(add);
                    }
                    else if(Question.child("title").getValue().equals("Fertilizers")){
                        sporoi.add(Question.child("description").getValue().toString().trim());
                        AntikeimenoItem add = new AntikeimenoItem(Question.child("image").getValue().toString().trim(),Question.child("location").getValue().toString().trim(),Question.child("description").getValue().toString().trim(),Question.child("title").getValue().toString().trim(),Question.child("time").getValue().toString().trim(),Question.child("start_date").getValue().toString().trim(),Question.child("last_date").getValue().toString().trim());
                        sporoi1.add(add);
                    }

                }

                if(mhxanhmata.isEmpty())
                    mhxanhmata.add("Empty");
                if(lipasmata.isEmpty())
                    lipasmata.add("Empty");
                if(sporoi.isEmpty())
                    sporoi.add("Empty");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listDataChild.put(listDataHeader.get(0), mhxanhmata); // Header, Child data
        listDataChild.put(listDataHeader.get(1), lipasmata);
        listDataChild.put(listDataHeader.get(2), sporoi);

    }


    private List<String> mhxanhmata = new ArrayList<String>();
    private List<String> lipasmata = new ArrayList<String>();
    private List<String>  sporoi= new ArrayList<String>();



    private List<AntikeimenoItem> mhxanhmata1 = new ArrayList<AntikeimenoItem>();
    private List<AntikeimenoItem> lipasmata1 = new ArrayList<AntikeimenoItem>();
    private List<AntikeimenoItem> sporoi1 = new ArrayList<AntikeimenoItem>();



    @Override
    protected void onStart() {
        super.onStart();



     /*   FirebaseRecyclerAdapter<AntikeimenoItem, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AntikeimenoItem, BlogViewHolder>(AntikeimenoItem.class, R.layout.child_antikeimena, BlogViewHolder.class, database) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, AntikeimenoItem model, int position) {

                final  String post_key =getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setPerigrafh(model.getPerigrafh());
                viewHolder.setDatearxh(model.getDatearxh());
                viewHolder.setDatetel(model.getDatetel());
                viewHolder.setTime(model.getTime());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                viewHolder.setUsername(model.getUsername());
                viewHolder.setPost(post_key);

                viewHolder.imagelike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        process=true;

                        if(process){
                            databaselike.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(post_key).hasChild(String.valueOf(dataSnapshot.child("username")))){

                                        databaselike.child(post_key).child(String.valueOf(dataSnapshot.child("username"))).removeValue();

                                    }
                                    else{
                                        databaselike.child(post_key).child(String.valueOf(dataSnapshot.child("username")));
                                        process=false;
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });
            }

        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);*/


    }


   /* public static class BlogViewHolder extends RecyclerView.ViewHolder {


        View view;
        ImageButton imagelike;
        DatabaseReference databaselike;



        public BlogViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imagelike=(ImageButton) view.findViewById(R.id.likepost);
            databaselike= FirebaseDatabase.getInstance().getReference().child("Likes");
            databaselike.keepSynced(true);
        }

        public void setPost(final String post_key) {

            databaselike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(post_key).hasChild(String.valueOf(dataSnapshot.child("username")))){
                        imagelike.setImageResource(R.drawable.like_red);
                    }
                    else {
                        imagelike.setImageResource(R.drawable.like);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        public void setTitle(String title) {

            TextView post_title = (TextView) view.findViewById(R.id.title);
            post_title.setText(title);
        }

        public void setPerigrafh(String perigrafh) {
            TextView post_perigrafh = (TextView) view.findViewById(R.id.perigrafh);
            post_perigrafh.setText(perigrafh);
        }

        public void setDatearxh(String datearxh) {

            TextView post_datearxh = (TextView) view.findViewById(R.id.date);
            post_datearxh.setText(datearxh);
        }
        public void setDatetel(String datetel) {

            TextView post_datetel = (TextView) view.findViewById(R.id.datelast);
            post_datetel.setText(datetel);
        }

        public void setTime(String time) {

            TextView post_time = (TextView) view.findViewById(R.id.time);
            post_time.setText(time);
        }

        public void setLocation(String location) {

            TextView post_location = (TextView) view.findViewById(R.id.location);
            post_location.setText(location);
        }
        public void setUsername(String username){
            TextView post_name = (TextView) view.findViewById(R.id.user);
            post_name.setText(username);
        }
        public void setImage(Context context, String image) {
            ImageView post_image = (ImageView) view.findViewById(R.id.imaposting);
            Picasso.with(context).load(image).into(post_image);
        }
    }*/

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
        alertDialogBuilder.setTitle("Θέλετε να βγείτε απο την εφαρμογή?");
        alertDialogBuilder
                .setMessage("Πατήστε ΝΑΙ για έξοδο!")
                .setCancelable(false)
                .setPositiveButton("ΝΑΙ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(diathesimothta.this,"Επιτυχής Αποσύνδεση",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(diathesimothta.this,eggrafh.class));
                            }
                        })

                .setNegativeButton("ΟΧΙ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


}


