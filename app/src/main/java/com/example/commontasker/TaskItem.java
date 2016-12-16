package com.example.commontasker;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Αρης on 2/11/2016.
 */
public class TaskItem extends AppCompatActivity {
    private String image;

    public String getTitlos() {
        return title;
    }

    private String title;
    private String time;
    private String date;



    public void setPerigrafh(String perigrafh) {
        this.perigrafh = perigrafh;
    }

    public String getPerigrafh() {
        return perigrafh;
    }

    private String perigrafh;

    public TaskItem() {


    }

    public TaskItem(String title, String location, String time, String image, String date, String perigrafh) {

        this.title=title;
        this.location=location;
        this.time=time;
        this.image=image;
        this.date=date;
        this.perigrafh=perigrafh;
    }

    private String location;

    public String getImage() {

        return image;
    }

    public String getTime() {

        return time;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setTitlos(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setTime(String time) {

        this.time = time;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
