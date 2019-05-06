package com.zpi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zpi.PhotoConnector.DownloadImageTask;

public class FetchingImages extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetching_images);

        new DownloadImageTask(findViewById(R.id.fetchImage2)).execute("10", "20");
    }
}
