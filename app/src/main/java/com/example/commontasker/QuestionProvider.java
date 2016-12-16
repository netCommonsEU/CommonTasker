package com.example.commontasker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Αρης on 18/10/2016.
 */

public class QuestionProvider {
    public static HashMap<String, List<String>> getInfo() {

        HashMap<String, List<String>> catogories_details = new HashMap<String, List<String>>();

        List<String> politikh = new ArrayList<String>();

        List<String> faghta = new ArrayList<String>();

        List<String> koinwnika = new ArrayList<String>();

        List<String> ygeia = new ArrayList<String>();

        catogories_details.put("Πολιτική", politikh);
        catogories_details.put("Φαγητά", faghta);
        catogories_details.put("Κοινωνικά", koinwnika);
        catogories_details.put("Υγεία",ygeia );

        return catogories_details;
    }
}