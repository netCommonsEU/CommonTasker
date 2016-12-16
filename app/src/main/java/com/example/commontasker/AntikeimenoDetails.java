package com.example.commontasker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Αρης on 1/11/2016.
 */

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/
import com.squareup.picasso.Picasso;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



public class AntikeimenoDetails extends AppCompatActivity {

    String perigrafh, image, titlos, datearx,datetel, location, time;
    TextView post_title;
    TextView post_perigrafh;
    TextView post_time;
    TextView post_location;
    TextView post_datingarx;
    TextView post_datingtel;
    ImageView post_image;

    Intent intent;
    //private DatabaseReference databaselike;
    private boolean process = false;

    ImageButton imagelike;
    String post_key;

    private Button button;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_antikeimena);

        imagelike = (ImageButton) findViewById(R.id.likepost);
        post_title = (TextView) findViewById(R.id.title);
        post_perigrafh = (TextView) findViewById(R.id.perigrafh);
        post_datingarx = (TextView) findViewById(R.id.date);
        post_datingtel = (TextView) findViewById(R.id.datelast);
        post_time = (TextView) findViewById(R.id.time);
        post_location = (TextView) findViewById(R.id.location);
        post_image = (ImageView) findViewById(R.id.imaposting);


        //databaselike = FirebaseDatabase.getInstance().getReference().child("Likes");
        //databaselike.keepSynced(true);

        /*databaselike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(post_key).hasChild(String.valueOf(dataSnapshot.child("username")))) {
                    imagelike.setImageResource(R.drawable.like_red);
                } else {
                    imagelike.setImageResource(R.drawable.like);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            perigrafh = extras.getString("description").trim();
            image = extras.getString("image").trim();
            titlos = extras.getString("title").trim();
            datearx = extras.getString("start_date").trim();
            datetel= extras.getString("last_date").trim();
            location = extras.getString("location").trim();
            time = extras.getString("time").trim();
        }

        Bitmap bm = null;
        try {
            if (post_image == null) {
                System.out.println("tsit");
            }
            URL aURL = new URL(image);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            post_image.setImageBitmap(bm);
            bis.close();
            is.close();
            System.out.println("wrong!");

        } catch (Exception e) {
            Log.v("EXCEPTION", "Error getting bitmap", e);
        }

        BlogViewHolder viewHolder = new BlogViewHolder();

        viewHolder.setPerigrafh(perigrafh);
        viewHolder.setImage(getApplicationContext(), image);
        viewHolder.setLocation(location);
        viewHolder.setTime(time);
        viewHolder.setDateArxh(datearx);
        viewHolder.setDateTel(datetel);
        viewHolder.setTitlos(titlos);

        button = (Button) findViewById(R.id.button6);
        listen();
    }

    private void listen() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), sms.class);
                startActivityForResult(intent, 0);
                view.startAnimation(buttonClick);
            }
        });
    }


    public class BlogViewHolder {

        public void setTitlos(String titlos) {
            post_title.setText(titlos);
        }

        public void setPerigrafh(String perigrafh) {
            post_perigrafh.setText(perigrafh);
        }

        public void setDateArxh(String dateArxh) {
            post_datingarx.setText(dateArxh);
        }

        public void setDateTel(String dateTel) {
            post_datingtel.setText(dateTel);
        }
        public void setTime(String time) {
            post_time.setText(time);
        }

        public void setLocation(String location) {
            post_location.setText(location);
        }

        public void setImage(Context context, String image) {
            Picasso.with(context).load(image).into(post_image);
        }

       /* public void setPost(final String post_key) {
            databaselike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(post_key).hasChild(String.valueOf(dataSnapshot.child("username")))) {
                        imagelike.setImageResource(R.drawable.like_red);
                    } else {
                        imagelike.setImageResource(R.drawable.like);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }*/

    }

}



