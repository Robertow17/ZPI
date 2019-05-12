package com.zpi.searcher.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.net.Uri;

import com.zpi.R;
import com.zpi.model.Photo;
import com.zpi.serviceProvider.activities.AddService;
import com.zpi.serviceProvider.activities.EditService;


import java.util.List;

public class ViewPagerAdapter extends PagerAdapter
{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Photo> data;

    public ViewPagerAdapter(Context context, List<Photo> data) {
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
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.searcher_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        try
        {
            //imageView.setImageResource(data.get(position).getId());
            imageView.setImageResource(data.get(position).getIdService());
        }
        catch(NumberFormatException e){
//            Uri image = Uri.parse(String.valueOf(data.get(position).getId()));
//            imageView.setImageURI(image);
        }

        itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (mContext instanceof AddService) {
                    int rnumber = 0;
                            try {
                                //rnumber = data.get(position).getId();
                                rnumber = data.get(position).getIdService();
                            }
                            catch (NumberFormatException e) { }
                    if (rnumber != R.drawable.no_photos) {
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Czy chcesz usunąć to zdjęcie?")
                                .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        deletePhoto(position);
                                        if(data.size()==0){
                                            ((AddService)mContext).addEmptyPhoto();
                                            ((AddService)mContext).notifyPhoto();
                                        }
                                    }
                                })
                                .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                    }
                }
                else if (mContext instanceof EditService) {
                    int rnumber = 0;
                    try {
//                        rnumber = data.get(position).getId();
                        rnumber = data.get(position).getIdService();
                    }
                    catch (NumberFormatException e) { }
                    if (rnumber != R.drawable.no_photos) {
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Czy chcesz usunąć to zdjęcie?")
                                .setPositiveButton("TAK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        deletePhoto(position);
                                        if(data.size()==0){
                                            ((EditService)mContext).addEmptyPhoto();
                                            ((EditService)mContext).notifyPhoto();
                                        }
                                    }
                                })
                                .setNegativeButton("NIE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                    }
                }
            }
        });

        container.addView(itemView);

        return itemView;
    }

    public void deletePhoto(int position)
    {
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