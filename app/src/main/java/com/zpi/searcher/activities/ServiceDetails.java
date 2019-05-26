package com.zpi.searcher.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.model.Service;
import com.zpi.searcher.utils.PageTransformer;
import com.zpi.searcher.utils.ServicesAdapter;
import com.zpi.searcher.utils.ViewPagerAdapter;

import static com.zpi.searcher.utils.ItemViewPagerAdapter.EXTRA_POSITION;

public class ServiceDetails extends AppCompatActivity
{

    private Service service;
    private TextView name, localization, descriptionLabel, description, guestsNumber, subcategory;
    private ImageView localizationIcon, sleepIcon, favouriteStar;
    private FloatingActionButton callButton, emailButton;
    private ViewPager viewPager;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher_activity_details);

        setLayoutView();
        setListeners();

        setWeddingHalls();

    }


    private void setLayoutView()
    {

        findViewsById();


        service = getIntent().getParcelableExtra(ServicesAdapter.EXTRA_SERVICE);
        position = getIntent().getExtras().getInt(EXTRA_POSITION);

        name.setText(service.getName());
        localization.setText(service.getLocalization());
        description.setText(service.getDescription());


        if(null == service.getSubcategory())
        {
            subcategory.setVisibility(View.INVISIBLE);
        }
        else
        {
            subcategory.setText(service.getSubcategory().getName());
        }

       /* if(service.isFavourite())
        {
            favouriteStar.setImageResource(android.R.drawable.star_big_on);
        }

        else
        {
            favouriteStar.setImageResource(android.R.drawable.star_off);
        }*/

        viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), service.getId() == 0 ? 1 : service.getId()));
        viewPager.setPageTransformer(true, new PageTransformer());

    }

    private void setWeddingHalls()
    {
        if(service.getCategory().equals("SALE"))
        {

            guestsNumber.setText(Integer.toString(service.getWeddingHallDetails().getMaxNumberOfGuests()));

            if(service.getWeddingHallDetails().isCanSleep())
            {
                sleepIcon.setImageResource(R.drawable.hotel);
            }
        }
    }

    private void findViewsById()
    {
        name = findViewById(R.id.nameTextView);
        localization = findViewById(R.id.localizationTextView);
        description = findViewById(R.id.description);
        descriptionLabel = findViewById(R.id.descriptionLabel);
        localizationIcon = findViewById(R.id.localizationIcon);
        callButton = findViewById(R.id.callButton);
        emailButton = findViewById(R.id.emailButton);
        guestsNumber = findViewById(R.id.guestsNumber);
        sleepIcon = findViewById(R.id.sleepIcon);
        favouriteStar = findViewById(R.id.favouriteStar);
        viewPager = findViewById(R.id.hallPhotosPager);
        subcategory = findViewById(R.id.subcategory);
    }


    private void setListeners()
    {
        callButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + service.getPhoneNumber()));
                startActivity(callIntent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                        service.getEmail(), null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });


      /*  favouriteStar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                service.setFavourite(!service.isFavourite());


                if(service.isFavourite())
                {
                    favouriteStar.setImageResource(android.R.drawable.star_big_on);

                }

                else
                {
                    favouriteStar.setImageResource(android.R.drawable.star_off);
                }

            }
        });*/
    }

    @Override
    public void onBackPressed()
    {/*
        if(service instanceof WeddingHall)
        {
            Data.getWeddingHalls().get(position).setFavourite(service.isFavourite());

        }
        if(service instanceof Transport)
        {
            Data.getTransports().get(position).setFavourite(service.isFavourite());
        }
        if(service instanceof Photography)
        {
            Data.getPhotographs().get(position).setFavourite(service.isFavourite());
        }
        if(service instanceof Others)
        {
            Data.getOthers().get(position).setFavourite(service.isFavourite());
        }
        if(service instanceof Music)
        {
            Data.getMusicians().get(position).setFavourite(service.isFavourite());
        }
        if(service instanceof Fashion)
        {
            Data.getFashion().get(position).setFavourite(service.isFavourite());
        }
        if(service instanceof Decorations)
        {
            Data.getDecorations().get(position).setFavourite(service.isFavourite());
        }
*/
        super.onBackPressed();
    }

}

