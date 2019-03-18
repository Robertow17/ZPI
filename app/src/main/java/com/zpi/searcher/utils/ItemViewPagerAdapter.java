package com.zpi.searcher.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpi.R;
import com.zpi.searcher.activities.WeddingHallDetails;
import com.zpi.searcher.model.Service;
import com.zpi.searcher.model.WeddingHall;


import static com.zpi.searcher.utils.ServicesAdapter.EXTRA_WEDDING_HALL;

public class ItemViewPagerAdapter extends PagerAdapter
{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Service service;
    private int recyclerViewPosiotion;
    public static final String EXTRA_POSITION = "com.zpi.searcher.utils.ItemViewPagerAdapter";


    public ItemViewPagerAdapter(Context context, Service service, int recyclerViewPosiotion) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.service = service;
        this.recyclerViewPosiotion = recyclerViewPosiotion;

    }

    @Override
    public int getCount() {
       return service.getPhotos().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.searcher_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(service.getPhotos().get(position));

        container.addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mContext, WeddingHallDetails.class);
                intent.putExtra(EXTRA_WEDDING_HALL, (Parcelable) service);
                intent.putExtra(EXTRA_POSITION, recyclerViewPosiotion);
                mContext.startActivity(intent);
            }
        });
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
