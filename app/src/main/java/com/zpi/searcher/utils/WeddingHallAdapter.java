package com.zpi.searcher.utils;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class WeddingHallAdapter extends RecyclerView.Adapter<WeddingHallAdapter.ViewHolder>
{
    private ArrayList weddingHalls;
    private Context context;


    public WeddingHallAdapter(Context context, ArrayList<WeddingHall> weddingHalls)
    {
        this.context = context;
        this.weddingHalls = weddingHalls;

    }

    @Override
    public WeddingHallAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searcher_wedding_hall_item, parent, false);

        return new ViewHolder(itemView, weddingHalls);
    }

    @Override
    public void onBindViewHolder(WeddingHallAdapter.ViewHolder holder, int position)
    {
        final WeddingHall movie = (WeddingHall) weddingHalls.get(position);
        holder.name.setText(movie.getName());

    }



    @Override
    public int getItemCount()
    {
        return weddingHalls.size();
    }

    public void removeItem(int position)
    {
        weddingHalls.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, weddingHalls.size());
    }

    public void moveItem(int oldPosition, int newPosition)
    {
        WeddingHall movie = (WeddingHall) weddingHalls.get(oldPosition);
        weddingHalls.remove(oldPosition);
        weddingHalls.add(newPosition, movie);
        notifyItemMoved(oldPosition, newPosition);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener
    {
        public TextView name;
        private ArrayList movies;

        public ViewHolder(View view, ArrayList<WeddingHall> weddingHalls)
        {
            super(view);
            this.movies = movies;
            name = view.findViewById(R.id.name);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
           /* Context context = view.getContext();
            final Movie movie = (Movie) movies.get(getAdapterPosition());
            Intent intent = new Intent(context, Details.class);
            intent.putExtra(EXTRA_MOVIE, movie);
            context.startActivity(intent);*/
        }
    }
}
