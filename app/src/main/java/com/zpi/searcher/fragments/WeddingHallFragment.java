package com.zpi.searcher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.SearchView;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.model.Service;
import com.zpi.searcher.utils.ServicesAdapter;

import java.util.ArrayList;


public class WeddingHallFragment extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private ServicesAdapter adapter;

    public WeddingHallFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.searcher_fragment, container, false);

        setRecyclerView();
        setLocalization();

        return rootView;
    }


    private void setRecyclerView()
    {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Service> serviceList = new ArrayList<Service>(Data.getWeddingHalls());
        adapter = new ServicesAdapter(getActivity(),serviceList);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLocalization()
    {
        searchView = rootView.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                adapter.filterLocalization(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.filterLocalization(newText);
                return true;
            }
        });

        searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);


        ArrayList<String> localizations = Data.getLocalizations(Data.getWeddingHalls());
        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, localizations);
        searchAutoComplete.setAdapter(newsAdapter);

        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id)
            {
                String queryString = (String) adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
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



}