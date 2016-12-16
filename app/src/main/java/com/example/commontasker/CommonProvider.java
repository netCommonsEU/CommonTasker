package com.example.commontasker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Αρης on 18/10/2016.
 */
public class CommonProvider {
    public  static HashMap<String,List<String>> getInfo(){

        HashMap<String,List<String>> catogories_details=new HashMap<String, List<String>>();

        List<String> machines=new ArrayList<String>();


        List<String>  lipasmata=new ArrayList<String>();

        List<String>  sporoi=new ArrayList<String>();

        catogories_details.put("Γεωργικά Μηχανήματα",machines);
        catogories_details.put("Λιπάσματα",lipasmata);
        catogories_details.put("Σπόροι",sporoi);


        return catogories_details;
    }

}
