package com.zpi.searcher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.model.Service;
import com.zpi.searcher.utils.ServicesAdapter;

import java.util.ArrayList;

public class FragmentBasic extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private ServicesAdapter adapter;



    public static FragmentBasic newInstance(ArrayList<Service> services)
    {
        FragmentBasic fragment = new FragmentBasic();

        Bundle args = new Bundle();
        args.putParcelableArrayList("LIST", services);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


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
        recyclerView =  rootView.findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getArguments().getParcelableArrayList("LIST");


        ArrayList<Service> services = getArguments().getParcelableArrayList("LIST");

        adapter = new ServicesAdapter(getContext(), services);
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                adapter.filterLocalization(newText);
                return false;
            }

        });

        searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);

        ArrayList<Service> services = getArguments().getParcelableArrayList("LIST");

        ArrayList<String> localizations = Data.getLocalizations(services);
        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(getContext(),
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
}