package com.zpi.PhotoConnector;

import android.widget.ImageView;

import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.model.Photo;

public class PhotoConnector {
    private ServerConnector<Photo> serverConnector = new ServerConnector<>(ServiceName.photos);

//    public void uploadPhoto() throws Exception {
//        HttpURLConnection urlConnection = (HttpURLConnection) new URL(SERVER_URI + "5").openConnection();
//        urlConnection.setRequestMethod(RequestType.GET.toString());
//        urlConnection.setConnectTimeout(5000);
////        urlConnection.setRequestProperty();
//
//        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//            throw new RuntimeException(String.valueOf(urlConnection.getResponseCode()));
//        }
//    }

    public void deletePhoto(int serviceId, int photoId) {
        new RemoveImageTask(serviceId, photoId).execute();
    }

    public void renderPhoto(int serviceId, int photoId, ImageView view) {
        new DownloadImageTask(view).execute(String.valueOf(serviceId), String.valueOf(photoId));
    }

    public void renderAllPhotos(int serviceId, ImageView[] views) {
        int[] photosIds = (new PhotoConnector().getAllPhotosIds(serviceId));

        for (int i = 0; i < Math.min(photosIds.length, views.length); i++) {
            renderPhoto(serviceId, photosIds[i], views[i]);
        }
    }

    public int[] getAllPhotosIds(int serviceId) {
        Object[] integersAsObject = serverConnector
                .getAll()
                .stream()
                .filter(photoInfo -> photoInfo.getServiceId() == serviceId)
                .map(Photo::getPhotoId)
                .toArray();

        int[] iHateJavaTypeSystem = new int[integersAsObject.length];

        for (int i = 0; i < integersAsObject.length; i++) {
            iHateJavaTypeSystem[i] = (int) integersAsObject[i];
        }

        return iHateJavaTypeSystem;
    }
}
