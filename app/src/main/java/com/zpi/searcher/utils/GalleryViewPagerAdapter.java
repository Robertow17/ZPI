package com.zpi.searcher.utils;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.searcher.activities.WeddingHallDetails;
import com.zpi.searcher.model.WeddingHall;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewPagerAdapter extends PagerAdapter
{

    Context mContext;
    LayoutInflater mLayoutInflater;
    private ArrayList<Integer> data;


    public GalleryViewPagerAdapter(Context context, ArrayList<Integer> data) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;

    }

    @Override
    public int getCount() {
       return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.searcher_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(data.get(position));

        container.addView(itemView);

        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
