package com.zpi.ServerConnector;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zpi.model.Category;
import com.zpi.model.Favourite;
import com.zpi.model.Photo;
import com.zpi.model.Service;
import com.zpi.model.Subcategory;
import com.zpi.model.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.zpi.Constants.SERVER_URI;

public class ServerConnector<T> {

    final int REQUEST_TIME = 5000;
    private List<T> result;
    ServiceName serviceName;

    public ServerConnector(ServiceName serviceName) {
        this.serviceName = serviceName;
        this.result = new ArrayList<>();
    }

    public List<T> getAll() {
        try {
            BackgroundTask backgroundTask = new BackgroundTask(Operation.all);
            backgroundTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean add(T object) {
        boolean result = false;
        try {
            BackgroundTask backgroundTask = new BackgroundTask(Operation.add, object);
            result = backgroundTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(int id, T newObject) {
        boolean result = false;
        try {
            BackgroundTask backgroundTask = new BackgroundTask(Operation.update, newObject, id);
            result = backgroundTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            BackgroundTask backgroundTask = new BackgroundTask(Operation.delete, id);
            result = backgroundTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    private class BackgroundTask extends AsyncTask<Void, Void, Boolean> {

        private final String DATE_FORMAT = "yyyy-MM-dd";
        private final String REQUEST_VALUE = "application/json";
        private Operation operation;
        private T object;
        int id;

        private BackgroundTask(Operation operation) {
            this.operation = operation;
            this.object = null;
        }

        private BackgroundTask(Operation operation, T object) {
            this.operation = operation;
            this.object = object;
        }

        private BackgroundTask(Operation operation, int id) {
            this.operation = operation;
            this.object = null;
            this.id = id;
        }

        private BackgroundTask(Operation operation, T newObject, int id) {
            this.operation = operation;
            this.object = newObject;
            this.id = id;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean res = false;
            try {
                if (operation == Operation.all)
                    result = getAllDataFromServer();
                if (operation == Operation.add)
                    res = addToServer(object);
                if (operation == Operation.update)
                    res = updateObjectInServer(id, object);
                if (operation == Operation.delete)
                    res = deleteFromServer(id);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        private List<T> getAllDataFromServer() throws IOException {
            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.all);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(RequestType.GET.toString());
            urlConnection.setConnectTimeout(REQUEST_TIME);
            urlConnection.setRequestProperty("Accept",REQUEST_VALUE);
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(String.valueOf(urlConnection.getResponseCode()));
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String data = "";
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    data = data + line + "\n";
                }
            }
            br.close();
            urlConnection.disconnect();
            List<T> list = new Gson().fromJson(data, getTypeFromServiceName());
            return list;
        }

        private Type getTypeFromServiceName(){
            if (serviceName == ServiceName.services)
                return   new TypeToken<ArrayList<Service>>(){}.getType();
            if (serviceName == ServiceName.subcategories)
                return   new TypeToken<ArrayList<Subcategory>>(){}.getType();
            if (serviceName == ServiceName.users)
                return   new TypeToken<ArrayList<User>>(){}.getType();
            if (serviceName == ServiceName.photos)
                return   new TypeToken<ArrayList<Photo>>(){}.getType();
            if (serviceName == ServiceName.favourites)
                return   new TypeToken<ArrayList<Favourite>>(){}.getType();
            if (serviceName == ServiceName.categories)
                return   new TypeToken<ArrayList<Category>>(){}.getType();
            return null;
        }

        private boolean addToServer(T object) throws IOException {
            String jsonNotification =
                    new GsonBuilder().setDateFormat(DATE_FORMAT).create().toJson(object);
            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.add);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(REQUEST_TIME);
            try {

                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod(RequestType.POST.toString());
                urlConnection.setRequestProperty("Content-Type", REQUEST_VALUE);
                urlConnection.setConnectTimeout(REQUEST_TIME);
                DataOutputStream dos =
                        new DataOutputStream(urlConnection.getOutputStream());
                dos.write(jsonNotification.getBytes(StandardCharsets.UTF_8), 0,
                        jsonNotification.getBytes(StandardCharsets.UTF_8).length);


                urlConnection.connect();

                if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    throw new RuntimeException("ERROR" + urlConnection.getResponseCode());
                }
            } finally {
                urlConnection.disconnect();
            }

            return urlConnection.getDoOutput();
        }

        private boolean deleteFromServer(int id) throws IOException {
            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.delete + "/" + String.valueOf(id));
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

            try {
                httpCon.setDoOutput(true);
                httpCon.setConnectTimeout(REQUEST_TIME);
                httpCon.setRequestProperty("X-HTTP-Method-Override", "DELETE");
                httpCon.setRequestMethod(RequestType.DELETE.toString());
                httpCon.connect();

                if (httpCon.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
                    return false;
                }
            } finally {
                httpCon.disconnect();
            }
            return httpCon.getDoOutput();
        }

        private boolean updateObjectInServer(int id, T newObject) throws IOException {

            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.update + "/" + String.valueOf(id));
            String jsonNotification =
                    new GsonBuilder().setDateFormat(DATE_FORMAT).create().toJson(newObject);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod(RequestType.PUT.toString());
            urlConnection.setRequestProperty("Content-Type", REQUEST_VALUE);
            urlConnection.setConnectTimeout(REQUEST_TIME);


            DataOutputStream dos =
                    new DataOutputStream(urlConnection.getOutputStream());
            dos.write(jsonNotification.getBytes(StandardCharsets.UTF_8), 0,
                    jsonNotification.getBytes(StandardCharsets.UTF_8).length);
            urlConnection.connect();

            Log.d("kod", String.valueOf(urlConnection.getResponseCode()));
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED) {
                return false;
            }
            return urlConnection.getDoOutput();
        }


    }
}
