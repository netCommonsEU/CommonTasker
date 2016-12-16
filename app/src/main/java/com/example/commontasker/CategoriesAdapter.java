package com.example.commontasker;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Αρης on 6/10/2016.
 */
public class CategoriesAdapter  extends BaseExpandableListAdapter{

    private Context context;
    private HashMap<String,List<String>> categorieshash;
    private List<String> categorieslist;

    public CategoriesAdapter(Context context,HashMap<String,List<String>> categorieshash,List<String>categorieslist){
        this.context=context;
        this.categorieslist=categorieslist;
        this.categorieshash=categorieshash;
    }

    @Override
    public int getGroupCount() {

        return categorieslist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return categorieshash.get(categorieslist.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return categorieslist.get(i);
    }

    @Override
    public Object getChild(int parent, int child) {

        return categorieshash.get(categorieslist.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View ConvertView, ViewGroup parentview) {
       String group_title= (String) getGroup(parent);
        if(ConvertView==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConvertView=inflater.inflate(R.layout.parent_layout,parentview,false);
        }
        TextView parent_text= (TextView) ConvertView.findViewById(R.id.parent_text);
        ImageView  image=(ImageView) ConvertView.findViewById(R.id.im);
       String name="";

        if(name.equals("Δουλειές Σπιτιού")){
            image.setImageResource(R.drawable.broom);
        }
        else if(name.equals("Κάνοντας Ψώνια")){
            image.setImageResource(R.drawable.shopping);
        }
        else if(name.equals("Μαθαίνοντας Κάποιoν")){
            image.setImageResource(R.drawable.ic_school_black_24dp);
        }
        else if(name.equals("Βοηθώντας Κάποιον")){
            image.setImageResource(R.drawable.care_icon);
        }
        parent_text.setTypeface(null, Typeface.BOLD);
        parent_text.setText(group_title);
        return ConvertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastchild, View ConvertView, ViewGroup parentView) {
       String child_title= (String) getChild(parent,child);

        if(ConvertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             ConvertView=inflater.inflate(R.layout.child_layout,parentView,false);
        }
        TextView child_text= (TextView) ConvertView.findViewById(R.id.child_text);
     child_text.setText(child_title);

        return ConvertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
