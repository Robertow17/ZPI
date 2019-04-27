package com.zpi.searcher.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.favourites.FavouritesActivity;
import com.zpi.model.Service;
import com.zpi.serviceProvider.activities.EditService;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;
import com.zpi.serviceProvider.model.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>
{
    private List<Service> services;
    private static Context context;
    private PagerAdapter pagerAdapter;
    private List<Service> servicesCopy;
    private boolean isUser;
    public static final String EXTRA_SERVICE = "com.zpi.searcher.service";


    public ServicesAdapter(Context context, List<Service> services)
    {
        this.context = context;
        this.services = services;
        this.servicesCopy = new ArrayList<>(services);

        if(context instanceof ServiceProviderMainActivity)
        {
            isUser = false;
        }
        else
        {
            isUser = true;
        }

    }

    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.searcher_recyclerview_item, parent, false);

        return new ViewHolder(itemView, services);
    }

    @Override
    public void onBindViewHolder(final ServicesAdapter.ViewHolder holder, final int position)
    {
        final Service service = services.get(position);
        holder.name.setText(service.getName());
        holder.localization.setText(service.getLocalization());

        pagerAdapter = new ItemViewPagerAdapter(context, service, position);
        holder.viewPager.setAdapter(pagerAdapter);


        if(null == (services.get(position)).getSubcategory())
        {

            holder.subcategory.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.subcategory.setText(service.getSubcategory().getName());
        }


        if(isUser)
        {/*
            if(service.isFavourite())
            {
                holder.favouriteIcon.setImageResource(android.R.drawable.star_big_on);
            }

            else
            {
                holder.favouriteIcon.setImageResource(android.R.drawable.star_off);
            }*/
        }
        else
        {
            holder.favouriteIcon.setImageResource(R.drawable.baseline_settings_white_18dp);
        }



        holder.favouriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(context instanceof FavouritesActivity)
                {
                    showConfirmFavouriteServiceAlertDialog(position);
                }
                if(!isUser){


                            PopupMenu popup = new PopupMenu(context, holder.favouriteIcon);

                    /*  The below code in try catch is responsible to display icons*/
                    try {
                        Field[] fields = popup.getClass().getDeclaredFields();
                        for (Field field : fields) {
                            if ("mPopup".equals(field.getName())) {
                                field.setAccessible(true);
                                Object menuPopupHelper = field.get(popup);
                                Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                                Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                                setForceIcons.invoke(menuPopupHelper, true);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //inflate menu
                    popup.getMenuInflater().inflate(R.menu.servicemenu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.editService:
                                    Intent intent = new Intent(context, EditService.class);
                                    intent.putExtra(EXTRA_SERVICE, service);
                                    //intent.putExtra(EXTRA_POSITION, recyclerViewPosiotion);
                                    context.startActivity(intent);
                                    return true;
                                case R.id.removeService:
                                    Data data = new Data();
                                    data.getServiceProvider().removeService(position);
                                    notifyDataSetChanged();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });

                    //show menu
                    popup.show();
//                            ------------------------------------------------------
//                    PopupMenu popup = new PopupMenu(context , holder.favouriteIcon);
//                    popup.inflate(R.menu.servicemenu);
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.editService:
//                                    Intent intent = new Intent(context, EditService.class);
//                                    context.startActivity(intent);
//                                    return true;
//                                case R.id.removeService:
//
//                                    return true;
//                                default:
//                                    return false;
//                            }
//                        }
//                    });
//                    popup.show();
                }
               /* else
                {
                    service.setFavourite(!service.isFavourite());

                    if(service.isFavourite())
                    {
                        holder.favouriteIcon.setImageResource(android.R.drawable.star_big_on);

                    }

                    else
                    {
                        holder.favouriteIcon.setImageResource(android.R.drawable.star_off);
                    }
                }*/

            }
        });

    }

    private void showConfirmFavouriteServiceAlertDialog(final int positionToRemove){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Czy na pewno chcesz usunąć usługę z ulubionych?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "TAK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeAt(positionToRemove);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NIE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
                if(item.getLocalization().toLowerCase().contains(text))
                {
                    services.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void removeAt(int position)
    {
        services.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, services.size());
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
                if(item.getSubcategory().toLowerCase().contains(text) || item.getSubcategory()
                .toLowerCase().contains(text))
                {
                    services.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }*/

   /* @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    servicesCopy = services;
                } else {
                    List<Service> filteredList = new ArrayList<>();

                    for(Service item : services){
                        if (item.getLocalization().toLowerCase().contains(charString.toLowerCase
                        ())) {
                            filteredList.add(item);
                        }
                    }

                    servicesCopy = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = servicesCopy;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                servicesCopy = (ArrayList<Service>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, localization, subcategory;
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
            subcategory = view.findViewById(R.id.subcategory);
        }
    }
}
