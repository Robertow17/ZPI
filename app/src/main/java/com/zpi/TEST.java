package com.zpi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import com.zpi.searcher.model.WeddingHall;
import com.zpi.searcher.utils.Service;
import com.zpi.searcher.utils.ServicesAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.zpi.Constants.SERVER_URI;

public class TEST extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private ServicesAdapter adapter;

    private  ArrayList<Integer> photosWeddingHall = new ArrayList<Integer>()
    {
        {
            add(R.drawable.img1);
            add(R.drawable.img2);
            add(R.drawable.img3);

        }
    };

    private  List<WeddingHall> test = new ArrayList<WeddingHall>()
        {{
            add(new WeddingHall("Zacisze", "Wrocław", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall,
                "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Zajazd u Bożenki", "Warszawa", 300, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall
                , "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Dom weselny u Beatki", "Zakopane", 250, false, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall, "609781153", "psliwinska@onet.eu"));
        add(new WeddingHall("Laguna", "Kraków", 120, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ex lorem, porttitor non faucibus ac, venenatis nec odio. Vivamus justo erat, accumsan ut pharetra eu, feugiat mollis ex. Aenean lectus libero", photosWeddingHall, "609781153"
                , "psliwinska@onet.eu"));


    }};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        searchView = findViewById(R.id.searchView2);

        recyclerView =  findViewById(R.id.testRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ServicesAdapter(this, test);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



    private void setSearchView()
    {
        searchView = findViewById(R.id.searchView2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                adapter.filterLocalization(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.filterLocalization(newText);
                return false;
            }

        });
    }

    private void setSearchAutoComplete(List<String> localizations)
    {
        searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);

        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, localizations);
        searchAutoComplete.setAdapter(newsAdapter);

        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id)
            {
                Log.d("aktywnosc","click");
                String queryString = (String) adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText(queryString);
                searchAutoComplete.focusSearch(View.FOCUS_RIGHT);
                searchAutoComplete.clearFocus();

                //searchView.clearFocus();
                //searchView.focusSearch(View.FOCUS_RIGHT);
                // searchView.focusSearch(view,View.FOCUS_RIGHT);
                //view.requestFocus();
            }
        });

        searchAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                searchAutoComplete.showDropDown();
            }
        });
    }

    public void click(View view) {
        Log.d("aktywnosc","click");

        getStrFromServer();


    }

    public void getStrFromServer(){

        try
        {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground( Void... voids ) {
                    try
                    {
                        URL url = new URL(SERVER_URI + "/services/all");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/json");
                        if(conn.getResponseCode() != 200)
                        {
                            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                        }
                        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                        String output;
                        Log.d("aktywnosc","Output from Server .... \n");
                        while((output = br.readLine()) != null)
                        {
                            Log.d("aktywnosc","output z servera: "+ output);
                        }
                        conn.disconnect();

                    } catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute().get();
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        } catch(ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}

