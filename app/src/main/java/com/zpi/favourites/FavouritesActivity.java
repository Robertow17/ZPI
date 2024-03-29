
package com.zpi.favourites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zpi.R;
import com.zpi.Data;
import com.zpi.searcher.utils.ServicesAdapter;

public class FavouritesActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private ServicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_activity);

        setRecyclerView();
    }


    private void setRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerViewOfOffers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ServicesAdapter(this, Data.getServices());
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


   /* @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }*/
}

