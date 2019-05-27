package com.zpi.PhotoConnector;

import android.os.AsyncTask;
import android.util.Log;

import com.zpi.ServerConnector.RequestType;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.zpi.PhotoConnector.Constants.getRequestUrl;

public class RemoveImageTask extends AsyncTask<String, Void, Void> {
    private int serviceId;
    private int photoId;

    public RemoveImageTask(int serviceId, int photoId) {
        this.serviceId = serviceId;
        this.photoId = photoId;
    }

    protected Void doInBackground(String... ids) {
        try {
            URL url = new URL(getRequestUrl(String.valueOf(serviceId), String.valueOf(photoId)));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(RequestType.DELETE.toString());
            urlConnection.setConnectTimeout(5000);
            urlConnection.connect();
            urlConnection.getResponseCode();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}