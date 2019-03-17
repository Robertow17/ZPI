package com.zpi.searcher.utils;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class WeddingHallAdapter extends RecyclerView.Adapter<WeddingHallAdapter.ViewHolder>
{
    private ArrayList weddingHalls;
    private static Context context;
    private PagerAdapter pagerAdapter;
    public static final String EXTRA_WEDDING_HALL = "com.zpi.searcher.model.WeddingHall";
    private ArrayList<WeddingHall> weddingHallsCopy;


    public WeddingHallAdapter(Context context, ArrayList<WeddingHall> weddingHalls)
    {
        this.context = context;
        this.weddingHalls = weddingHalls;
        this.weddingHallsCopy = new ArrayList<>(weddingHalls);

    }


    @Override
    public WeddingHallAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.searcher_recyclerview_item, parent, false);

        return new ViewHolder(itemView, weddingHalls);
    }

    @Override
    public void onBindViewHolder(WeddingHallAdapter.ViewHolder holder, int position)
    {
        final WeddingHall hall = (WeddingHall) weddingHalls.get(position);
        holder.name.setText(hall.getName());
        holder.localization.setText(hall.getLocalization());

        pagerAdapter = new ItemViewPagerAdapter(context, hall, position);

        holder.viewPager.setAdapter(pagerAdapter);

        if(hall.isFavourite())
        {
            holder.favouriteIcon.setImageResource(android.R.drawable.star_big_on);
        }

        else
        {
            holder.favouriteIcon.setImageResource(android.R.drawable.star_off);
        }

    }


    @Override
    public int getItemCount()
    {
        return weddingHalls.size();
    }


    public void filterLocalization(String text)
    {
        weddingHalls.clear();
        if(text.isEmpty())
        {
            weddingHalls.addAll(weddingHallsCopy);
        }
        else
        {
            text = text.toLowerCase();
            for(WeddingHall item : weddingHallsCopy)
            {
                if(item.getLocalization().toLowerCase().contains(text) || item.getLocalization().toLowerCase().contains(text))
                {
                    weddingHalls.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


   /* public void filterSubcategory(String text)
    {
        weddingHalls.clear();
        if(text.isEmpty())
        {
            weddingHalls.addAll(weddingHallsCopy);
        }
        else
        {
            text = text.toLowerCase();
            for(WeddingHall item : weddingHallsCopy)
            {
                if(item.getSubcategory().toLowerCase().contains(text) || item.getSubcategory().toLowerCase().contains(text))
                {
                    weddingHalls.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, localization;
        public ViewPager viewPager;
        private ArrayList<WeddingHall> weddingHalls;
        private ImageView favouriteIcon;


        public ViewHolder(View view, final ArrayList<WeddingHall> weddingHalls)
        {
            super(view);
            this.weddingHalls = weddingHalls;
            name = view.findViewById(R.id.name);
            localization = view.findViewById(R.id.localization);
            viewPager = view.findViewById(R.id.viewpager);
            favouriteIcon = view.findViewById(R.id.star);

            favouriteIcon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    WeddingHall hall =  weddingHalls.get(getAdapterPosition());
                    hall.setFavourite(!hall.isFavourite());

                    if(hall.isFavourite())
                    {
                        favouriteIcon.setImageResource(android.R.drawable.star_big_on);

                    }

                    else
                    {
                        favouriteIcon.setImageResource(android.R.drawable.star_off);
                    }
                }
            });

        }

    }
}
