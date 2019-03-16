package com.zpi.searcher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.utils.WeddingHallAdapter;

public class MusicFragment extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;
    private static final String[] SUBCATEGORIES = new String[] {"Zespół weselny", "DJ"};

    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.searcher_fragment_with_subcategory, container, false);

        setRecyclerView();
        setSubcategories();

        return rootView;
    }



    private void setRecyclerView()
    {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WeddingHallAdapter(getActivity(), Data.getWeddingHalls()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setSubcategories()
    {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, SUBCATEGORIES);

        final AutoCompleteTextView subcategory = rootView.findViewById(R.id.autoCompleteTextView);
        subcategory.setAdapter(adapter);

        subcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                subcategory.showDropDown();
            }
        });

        subcategory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                subcategory.showDropDown();
            }
        });

    }

}