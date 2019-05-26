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
import com.zpi.model.Photo;
import com.zpi.serviceProvider.activities.AddService;
import com.zpi.serviceProvider.activities.EditService;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Photo> data;
    private PhotoConnector photoConnector = new PhotoConnector();
    private int serviceId;
    private int[] photoIds;

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
        try {
            new PhotoConnector().renderPhoto(serviceId, photoIds[position], imageView);
//            new PhotoConnector().renderAllPhotos(1, new ImageView[] {imageView});
//            imageView.setImageResource(data.get(position).getServiceId());
        } catch (Exception e) {
            int b = 0;
        }

        itemView.setOnClickListener(v -> {
            int rnumber = 0;
            try {
                rnumber = data.get(position).getServiceId();
            } catch (NumberFormatException ignored) {
            }

            if (rnumber != R.drawable.no_photos) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(mContext);

                if (mContext instanceof EditService) {
                    builder.setMessage("Czy chcesz usunąć to zdjęcie?")
                            .setPositiveButton("TAK", (dialog, which) -> {
                                deletePhoto(position);
                                if (data.size() == 0) {
                                    ((EditService) mContext).addEmptyPhoto();
                                    ((EditService) mContext).notifyPhoto();
                                }
                            });

                } else if (mContext instanceof AddService) {
                    builder.setMessage("Czy chcesz usunąć to zdjęcie?")
                            .setPositiveButton("TAK", (dialog, which) -> {
                                deletePhoto(position);
                                if (data.size() == 0) {
                                    ((AddService) mContext).addEmptyPhoto();
                                    ((AddService) mContext).notifyPhoto();
                                }
                            });
                }

                builder
                        .setNegativeButton("NIE", (dialog, which) -> {
                        }).show();
            }

        });

        container.addView(itemView);

        return itemView;
    }

    public void deletePhoto(int position) {
        // Remove the corresponding item in the data set
        data.remove(position);
        // Notify the adapter that the data set is changed
        notifyDataSetChanged();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}