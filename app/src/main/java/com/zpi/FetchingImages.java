package com.zpi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zpi.PhotoConnector.PhotoConnector;

public class FetchingImages extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetching_images);

        int serviceId = 6;
        ImageView[] views = new ImageView[] { findViewById(R.id.fetchImage1), findViewById(R.id.fetchImage2) };

        new PhotoConnector().renderAllPhotos(serviceId, views);
    }
}
