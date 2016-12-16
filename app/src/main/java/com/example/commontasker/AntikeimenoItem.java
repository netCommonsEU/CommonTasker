package com.example.commontasker;

/**
 * Created by Αρης on 19/10/2016.
 */

public class AntikeimenoItem  {
    private String image;
    private String title;
    private String time;
    private String datearxh;
    private String datetel;
    private String description;
    private String location;

    public AntikeimenoItem() {

    }

    public AntikeimenoItem(String image, String location, String description, String title, String time, String datearxh, String datetel) {

        this.image = image;
        this.location = location;
        this.description = description;
        this.title = title;
        this.time = time;
        this.datearxh = datearxh;
        this.datetel = datetel;

    }

    public String getDatearxh() {
        return datearxh;
    }

    public String getDatetel() {
        return datetel;
    }



    public void setDatearxh(String datearxh) {

        this.datearxh = datearxh;
    }
    public void setDatetel(String datetel) {

        this.datetel = datetel;
    }


    public void setPerigrafh(String perigrafh) {
        this.description = perigrafh;
    }

    public String getPerigrafh() {
        return description;
    }




    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }


    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
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