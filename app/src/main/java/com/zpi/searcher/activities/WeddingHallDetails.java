package com.zpi.searcher.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.model.WeddingHall;
import com.zpi.searcher.utils.PageTransformer;
import com.zpi.searcher.utils.ViewPagerAdapter;
import com.zpi.searcher.utils.ServicesAdapter;

import static com.zpi.searcher.utils.ItemViewPagerAdapter.EXTRA_POSITION;

public class WeddingHallDetails extends AppCompatActivity
{

    private WeddingHall weddingHall;
    private TextView name, localization, descriptionLabel, description, guestsNumber;
    private ImageView localizationIcon, sleepIcon, favouriteStar;
    private FloatingActionButton callButton, emailButton;
    private ViewPager viewPager;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher_activity_wedding_hall_details);

        setLayoutView();
        setListeners();
    }


    private void setLayoutView()
    {

        findViewsById();

        weddingHall = getIntent().getExtras().getParcelable(ServicesAdapter.EXTRA_WEDDING_HALL);
        position = getIntent().getExtras().getInt("POZ");

        name.setText(weddingHall.getName());
        localization.setText(weddingHall.getLocalization());
        description.setText(weddingHall.getDescription());
        guestsNumber.setText(Integer.toString(weddingHall.getMaxNumberOfGuests()));

        if(!weddingHall.canSleep())
        {
            sleepIcon.setVisibility(View.INVISIBLE);
        }

        if(weddingHall.isFavourite())
        {
            favouriteStar.setImageResource(R.drawable.baseline_favorite_black_18dp);
        }

        else
        {
            favouriteStar.setImageResource(R.drawable.baseline_favorite_border_black_18dp);
        }


        viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), weddingHall.getPhotos()));
        viewPager.setPageTransformer(true, new PageTransformer());

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
    }


    private void setListeners()
    {
        callButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + weddingHall.getPhoneNumber()));
                startActivity(callIntent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",weddingHall.getEmail(), null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });


        favouriteStar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                weddingHall.setFavourite(!weddingHall.isFavourite());

                Log.d("ONCLICK", String.valueOf(weddingHall.isFavourite()));

                if(weddingHall.isFavourite())
                {
                    favouriteStar.setImageResource(R.drawable.baseline_favorite_black_18dp);

                }

                else
                {
                    favouriteStar.setImageResource(R.drawable.baseline_favorite_border_black_18dp);
                }


                Data.getWeddingHalls().get(getIntent().getExtras().getInt("POZ")).setFavourite(weddingHall.isFavourite());

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Data.getWeddingHalls().get(position).setFavourite(weddingHall.isFavourite());
        Log.d(EXTRA_POSITION, String.valueOf(position));
        super.onBackPressed();
    }
}
