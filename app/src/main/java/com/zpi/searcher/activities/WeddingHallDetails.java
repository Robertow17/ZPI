package com.zpi.searcher.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.model.WeddingHall;
import com.zpi.searcher.utils.WeddingHallAdapter;

public class WeddingHallDetails extends AppCompatActivity
{

    private WeddingHall weddingHall;
    private TextView name, localization, descriptionLabel, description;
    private ImageView localizationIcon;
    private FloatingActionButton callButton, emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_hall_details);

        setLayoutView();
    }


    private void setLayoutView()
    {
        name = findViewById(R.id.nameTextView);
        localization = findViewById(R.id.localizationTextView);
        description =  findViewById(R.id.description);
        descriptionLabel = findViewById(R.id.descriptionLabel);
        localizationIcon = findViewById(R.id.imageView);
        callButton = findViewById(R.id.callButton);
        emailButton = findViewById(R.id.emailButton);

        weddingHall = getIntent().getExtras().getParcelable(WeddingHallAdapter.EXTRA_WEDDING_HALL);

        name.setText(weddingHall.getName());
        localization.setText(weddingHall.getLocalization());
        description.setText(weddingHall.getDescription());

    }
}
