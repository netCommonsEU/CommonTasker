package com.example.commontasker;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    String query;
    HashMap<String,List<String>> Categories;
    List<String> catogories_list;
    ExpandableListView expandableListView;
    CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.search_results_toolbar);
        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Intent searchIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
            query = searchIntent.getStringExtra(SearchManager.QUERY);
            getSupportActionBar().setTitle(query);
            Toast.makeText(SearchResultsActivity.this, query, Toast.LENGTH_SHORT).show();

            SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this, SearchableProvider.AUTHORITY, SearchableProvider.MODE);
            searchRecentSuggestions.saveRecentQuery(query, null);
        }

        Categories = DataProvider.getInfo();
        catogories_list = new ArrayList<String>(Categories.keySet());

        HashMap<String,List<String>> tests = (HashMap<String,List<String>>) Categories;
        ArrayList<String> searchResults = new ArrayList<String>();

        for (int i = 0; i < tests.size(); i++) {
            for (int j = 0; j < tests.get(catogories_list.get(i)).size(); j++) {
            if (tests.get(catogories_list.get(i)).get(j).toLowerCase().contains(query.toLowerCase()))
                searchResults.add(tests.get(catogories_list.get(i)).get(j));
            }
        }
        ListView listView_search_results = (ListView) findViewById(R.id.listView_searchResults);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searchResults);
        listView_search_results.setAdapter(adapter);

    }
}
