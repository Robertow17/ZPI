package com.zpi.searcher.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.utils.WeddingHallAdapter;


public class WeddingHallFragment extends Fragment
{
    private View rootView;
    private RecyclerView recyclerView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.searcher_fragment, container, false);

        setRecyclerView();

        return rootView;
    }


    private void setRecyclerView()
    {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new WeddingHallAdapter(getActivity(), Data.getWeddingHalls()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



}