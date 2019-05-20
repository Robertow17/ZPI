package com.zpi.PhotoConnector;

import com.zpi.ServerConnector.RequestType;
import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.model.Photo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.zpi.Constants.SERVER_URI;


public class PhotoConnector {
    ServerConnector<Photo> serverConnector = new ServerConnector<>(ServiceName.photos);

//    private void addPhoto(byte[] photo) {}

    public void uploadPhoto() throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(SERVER_URI + "5").openConnection();
        urlConnection.setRequestMethod(RequestType.GET.toString());
        urlConnection.setConnectTimeout(5000);
//        urlConnection.setRequestProperty();

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(String.valueOf(urlConnection.getResponseCode()));
        }
    }

    public Object[] getAllPhotos(int serviceId) {
        List<Photo> photos = serverConnector.getAll();

        int i = 1;

//        List<Photo> list = new Gson().fromJson(photos, new TypeToken<List<Photo>>() .getType());
//        Type listType = new TypeToken<ArrayList<Photo>>() {}.getType();
//        List<Photo> list2 = new Gson().fromJson(photos, listType);


//        for (LinkedTreeMap photo: photos) {
//            Object a = photo.get("id");
////            Object id = photo.getIdService();
////            Object a = id;
//            int b = 2;
//        }
        return new Object[] {};
//        return photos.stream().filter(photo -> photo.getIdService() == serviceId).toArray();
    }
}
