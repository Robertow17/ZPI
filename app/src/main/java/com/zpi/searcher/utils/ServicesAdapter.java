package com.zpi.searcher.utils;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.model.Service;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>
{
    private List services;
    private static Context context;
    private PagerAdapter pagerAdapter;
    private ArrayList<Service> servicesCopy;
    public static final String EXTRA_WEDDING_HALL = "com.zpi.searcher.model.WeddingHall";


    public ServicesAdapter(Context context, List<Service> services)
    {
        this.context = context;
        this.services = services;
        this.servicesCopy = new ArrayList<>();

    }


    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.searcher_recyclerview_item, parent, false);

        return new ViewHolder(itemView, services);
    }

    @Override
    public void onBindViewHolder(ServicesAdapter.ViewHolder holder, int position)
    {
        final Service hall = (Service) services.get(position);
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
        return services.size();
    }


    public void filterLocalization(String text)
    {
        services.clear();
        if(text.isEmpty())
        {
            services.addAll(servicesCopy);
        }
        else
        {
            text = text.toLowerCase();
            for(Service item : servicesCopy)
            {
                if(item.getLocalization().toLowerCase().contains(text) || item.getLocalization().toLowerCase().contains(text))
                {
                    services.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


   /* public void filterSubcategory(String text)
    {
        services.clear();
        if(text.isEmpty())
        {
            services.addAll(servicesCopy);
        }
        else
        {
            text = text.toLowerCase();
            for(WeddingHall item : servicesCopy)
            {
                if(item.getSubcategory().toLowerCase().contains(text) || item.getSubcategory().toLowerCase().contains(text))
                {
                    services.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, localization;
        public ViewPager viewPager;
        private List<Service> services;
        private ImageView favouriteIcon;


        public ViewHolder(View view, final List<Service> services)
        {
            super(view);
            this.services = services;
            name = view.findViewById(R.id.name);
            localization = view.findViewById(R.id.localization);
            viewPager = view.findViewById(R.id.viewpager);
            favouriteIcon = view.findViewById(R.id.star);

            favouriteIcon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Service hall =  services.get(getAdapterPosition());
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
