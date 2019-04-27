package com.zpi.searcher.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpi.R;
import com.zpi.model.Service;
import com.zpi.searcher.activities.ServiceDetails;
import com.zpi.serviceProvider.activities.EditService;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;

import static com.zpi.searcher.utils.ServicesAdapter.EXTRA_SERVICE;

public class ItemViewPagerAdapter extends PagerAdapter
{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Service service;
    private int recyclerViewPosiotion;
    private boolean isUser;
    public static final String EXTRA_POSITION = "com.zpi.searcher.utils.ItemViewPagerAdapter";


    public ItemViewPagerAdapter(Context context, Service service, int recyclerViewPosition) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.service = service;
        this.recyclerViewPosiotion = recyclerViewPosition;

        if(context instanceof ServiceProviderMainActivity){
            isUser=false;
        }
        else{
            isUser=true;
        }
    }

    @Override
    public int getCount() {
       return service.getPhotos().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ( object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.searcher_pager_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        try
        {
            imageView.setImageResource(Integer.valueOf(service.getPhotos().get(position).getValue()));
        }
        catch(NumberFormatException e){
            Uri image = Uri.parse(service.getPhotos().get(position).getValue());
            imageView.setImageURI(image);
        }
//        imageView.setImageResource(Integer.valueOf(service.getPhotos().get(position).getValue()));

        container.addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(isUser) {
                    Intent intent = new Intent(mContext, ServiceDetails.class);
                    intent.putExtra(EXTRA_SERVICE, service);
                    intent.putExtra(EXTRA_POSITION, recyclerViewPosiotion);
                    mContext.startActivity(intent);
                }
                else{
//                    Intent intent = new Intent(mContext, EditService.class);
//                    mContext.startActivity(intent);
                }
            }
        });
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

