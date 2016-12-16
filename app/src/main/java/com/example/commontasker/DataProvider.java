package com.example.commontasker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Αρης on 6/10/2016.
 */
public class DataProvider {
    public  static HashMap<String,List<String>> getInfo(){

        HashMap<String,List<String>> catogories_details=new HashMap<String, List<String>>();

         List<String> house=new ArrayList<String>();
        house.add("Καθάρισμα Σπιτιού");
        house.add("Καθάρισμα Μπάνιου");
        house.add("Καθάρισμα Κουζίνας");
        house.add("Καθάρισμα Γκαράζ");

         List<String>  ergasies=new ArrayList<String>();
        ergasies.add("Συγγομηδή Ελίας");
        ergasies.add("Αμπέλια");
        ergasies.add("Αγορά απο Κρεοπώλη");
        ergasies.add("Καλλιέργεια Καλαμποκιού");

         List<String>  teaching=new ArrayList<String>();
        teaching.add("Mαθαίνοντας Αγγλικά");
        teaching.add("Mαθαίνοντας Ισπανικά για ένα Σεμιναριο");

         List<String>  Helping=new ArrayList<String>();
        Helping.add("Βοηθώντας Αδέσποτα ζώα");
        Helping.add("Βοηθώντας ένα Μεσήλικα να κάνει τα βασικά σε ένα Ηλεκτρονικό Υπολογιστή");
        Helping.add("Μετακόμιση");
        Helping.add("Προσοχή Ηλικιωμένου Ανθρώπου");

        catogories_details.put("Δουλειές Σπιτιού",house);
        catogories_details.put("Εξωτερικές Εργασίες",ergasies);
        catogories_details.put("Εκπαιδευτικές Εργασίες",teaching);
        catogories_details.put("Προσφέροντας Βοήθεια",Helping);

        return catogories_details;
    }

}
