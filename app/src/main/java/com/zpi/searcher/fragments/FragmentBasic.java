package com.zpi.searcher.fragments;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.zpi.Data;
import com.zpi.model.Service;
import com.zpi.searcher.utils.ServicesAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.zpi.searcher.activities.SearcherActivity.EXTRA_SERVICES;

public class FragmentBasic extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private ServicesAdapter adapter;



    public static FragmentBasic newInstance(List<Service> services)
    {
        FragmentBasic fragment = new FragmentBasic();

        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_SERVICES, (ArrayList<? extends Parcelable>) services);
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

        List<Service> services =  getArguments().getParcelableArrayList(EXTRA_SERVICES);
        List<String> localizations = Data.getLocalizations(services);

        setRecyclerView(services);
        setLocalization(localizations);

        return rootView;
    }


    private void setRecyclerView(List<? extends Service> services)
    {
        recyclerView =  rootView.findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ServicesAdapter(getContext(), (List<Service>) services);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLocalization(List<String> localizations)
    {

        setSearchView();
        setSearchAutoComplete(localizations);

    }

    private void setSearchView()
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
    }

    private void setSearchAutoComplete(List<String> localizations)
    {
        searchAutoComplete =
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);

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