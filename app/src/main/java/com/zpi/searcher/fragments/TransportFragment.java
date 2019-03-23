package com.zpi.searcher.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.model.Service;
import com.zpi.searcher.utils.ServicesAdapter;

import java.util.ArrayList;

public class TransportFragment extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;
    private SearchView localizationSearchView;
    private SearchView subcategorySearchView;
    private SearchView.SearchAutoComplete searchAutoCompleteLoc;
    private SearchView.SearchAutoComplete searchAutoCompleteSubCat;
    private ServicesAdapter adapter;
    private static final String[] SUBCATEGORIES = new String[] {"Limuzyna", "Samochody zabytkowe", "Inne pojazdy"};

    public TransportFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    /*@Override
    public void onResume()
    {
        super.onResume();
        rootView.requestFocus();
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.searcher_fragment_with_subcategory, container, false);

        setRecyclerView();
        setLocalization();
        setSubcategories();

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
        localizationSearchView = rootView.findViewById(R.id.searchView);


        localizationSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
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

        searchAutoCompleteLoc =
                localizationSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoCompleteLoc.setDropDownBackgroundResource(android.R.color.white);


        ArrayList<String> localizations = Data.getLocalizations(Data.getWeddingHalls());
        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, localizations);
        searchAutoCompleteLoc.setAdapter(newsAdapter);

        searchAutoCompleteLoc.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id)
            {
                String queryString = (String) adapterView.getItemAtPosition(itemIndex);
                searchAutoCompleteLoc.setText("" + queryString);
            }
        });

        searchAutoCompleteLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                searchAutoCompleteLoc.showDropDown();
            }
        });


    }


    private void setSubcategories()
    {
        subcategorySearchView = rootView.findViewById(R.id.subcategorySearchView);


        searchAutoCompleteSubCat =
                subcategorySearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoCompleteSubCat.setDropDownBackgroundResource(android.R.color.white);


        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, SUBCATEGORIES);
        searchAutoCompleteSubCat.setAdapter(newsAdapter);

        searchAutoCompleteSubCat.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id)
            {
                String queryString = (String) adapterView.getItemAtPosition(itemIndex);
                searchAutoCompleteSubCat.setText("" + queryString);
            }
        });

        searchAutoCompleteSubCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                searchAutoCompleteSubCat.showDropDown();
            }
        });

    }
}
