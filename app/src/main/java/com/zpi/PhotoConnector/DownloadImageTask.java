package com.zpi.PhotoConnector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;

import java.io.InputStream;
import java.util.List;

import static com.zpi.PhotoConnector.Constants.getRequestUrl;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... ids) {
        String serviceId = ids[0];
        String photoId = ids[1];

//        ServerConnector<String> sc = new ServerConnector<>(ServiceName.photos);
//        List<String> a = sc.getAll();

        Bitmap image = null;

        try {
            InputStream in = new java.net.URL(getRequestUrl(serviceId, photoId)).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return image;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}