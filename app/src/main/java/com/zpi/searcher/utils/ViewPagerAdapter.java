package com.zpi.searcher.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpi.PhotoConnector.PhotoConnector;
import com.zpi.R;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private int serviceId;
    private int[] photoIds;
    private PhotoConnector photoConnector = new PhotoConnector();

    public ViewPagerAdapter(Context context, int serviceId) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.serviceId = serviceId;
        this.photoIds = this.photoConnector.getAllPhotosIds(serviceId);
    }

    @Override
    public int getCount() {
        return photoIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.searcher_pager_item, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        this.photoConnector.renderPhoto(serviceId, photoIds[position], imageView);
        container.addView(itemView);

        itemView.setOnClickListener(v -> {
            new AlertDialog.Builder(mContext).setMessage("Czy chcesz usunąć to zdjęcie?")
                    .setPositiveButton("TAK", (dialog, which) -> {
                        container.removeView(itemView);
                        // FIXME: if there are not elements render "empty photo" image
                        // FIXME: if there is another element focus on it
                        this.photoConnector.deletePhoto(this.serviceId, photoIds[position]);
                    })
                    .setNegativeButton("NIE", ((dialog, which) -> {}))
                    .show();
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}