package com.zpi.searcher.utils;


import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.searcher.activities.WeddingHallDetails;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;

public class WeddingHallAdapter extends RecyclerView.Adapter<WeddingHallAdapter.ViewHolder>
{
    private ArrayList weddingHalls;
    private Context context;
    private PagerAdapter pagerAdapter;
    public static final String EXTRA_WEDDING_HALL = "com.zpi.searcher.model.WeddingHall";


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
        final WeddingHall hall = (WeddingHall) weddingHalls.get(position);
        holder.name.setText(hall.getName());
        holder.localization.setText(hall.getLocalization());

        pagerAdapter = new GalleryViewPagerAdapter(context, hall.getPhotos());

        holder.viewPager.setAdapter(pagerAdapter);


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
        WeddingHall weddingHall = (WeddingHall) weddingHalls.get(oldPosition);
        weddingHalls.remove(oldPosition);
        weddingHalls.add(newPosition, weddingHall);
        notifyItemMoved(oldPosition, newPosition);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener
    {
        public TextView name, localization;
        public ViewPager viewPager;
        private ArrayList weddingHalls;

        public ViewHolder(View view, final ArrayList<WeddingHall> weddingHalls)
        {
            super(view);
            this.weddingHalls = weddingHalls;
            name = view.findViewById(R.id.name);
            localization = view.findViewById(R.id.localization);
            viewPager = view.findViewById(R.id.viewpager);

            viewPager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Context context = view.getContext();
                    final WeddingHall weddingHall = (WeddingHall) weddingHalls.get(getAdapterPosition());
                    Intent intent = new Intent(context, WeddingHallDetails.class);
                    intent.putExtra(EXTRA_WEDDING_HALL, weddingHall);
                    context.startActivity(intent);
                    Toast.makeText(context, "This is my Toast message!",
                            Toast.LENGTH_LONG).show();
                }
            });

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {

        }
/*        @Override
        public void onClick(View view)
        {
            Context context = view.getContext();
            final WeddingHall weddingHall = (WeddingHall) weddingHalls.get(getAdapterPosition());
            Intent intent = new Intent(context, WeddingHallDetails.class);
            intent.putExtra(EXTRA_WEDDING_HALL, weddingHall);
            context.startActivity(intent);
            Toast.makeText(context, "This is my Toast message!",
                    Toast.LENGTH_LONG).show();
        }*/
    }
}
