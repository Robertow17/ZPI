package com.zpi.searcher.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.utils.WeddingHallAdapter;

public class PhotographyFragment extends Fragment
{

    private View rootView;
    private RecyclerView recyclerView;

    public PhotographyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
