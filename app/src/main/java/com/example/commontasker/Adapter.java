package com.example.commontasker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Αρης on 19/9/2016.
 */
public class Adapter extends ArrayAdapter<String> {

    Context c;
    String [] categories;
    int []images;
    public Adapter(Context ctx,String[]categories,int[]images){
        super(ctx,R.layout.model,categories);
        this.c=ctx;
        this.categories=categories;
        this.images=images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)c.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView=inflater.inflate(R.layout.model,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.textView6);
        ImageView img=(ImageView)convertView.findViewById(R.id.imageView);

        textView.setText(categories[position]);
        img.setImageResource(images[position]);


        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)c.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            convertView=inflater.inflate(R.layout.model,null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.textView6);
        ImageView img=(ImageView)convertView.findViewById(R.id.imageView);

        textView.setText(categories[position]);
        img.setImageResource(images[position]);

        return convertView;
    }
}
