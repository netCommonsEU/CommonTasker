package com.example.commontasker;

/**
 * Created by Αρης on 7/10/2016.
 */
import android.content.SearchRecentSuggestionsProvider;

public class SearchableProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.example.commontasker.SearchableProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }


}