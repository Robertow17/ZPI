package com.zpi.ServerConnector;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.zpi.Constants.SERVER_URI;

public class ServerConnector<T> {

    private final int REQUEST_TIME = 5000;
    private List<T> result;
    private ServiceName serviceName;

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

    public void add(T object){
        try {
            BackgroundTask backgroundTask = new BackgroundTask(Operation.add, object);
            backgroundTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        Operation operation;
        T object;

        private BackgroundTask(Operation operation) {
            this.operation = operation;
            this.object = null;
        }

        private BackgroundTask(Operation operation, T object) {
            this.operation = operation;
            this.object=object;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (operation == Operation.all)
                    result = getAllDataFromServer();
                if (operation == Operation.add)
                    addToServer(object);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private List<T> getAllDataFromServer() throws IOException {
            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.all);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(REQUEST_TIME);
            urlConnection.setRequestProperty("Accept", "application/json");
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
            Type listType = new TypeToken<ArrayList<T>>() {
            }.getType();
            List<T> list = new Gson().fromJson(data, listType);

            return list;
        }
        private void addToServer(T object) throws IOException {
            String jsonNotification =
                    new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(object);
            Log.d("aktywnosc", jsonNotification);
            URL url = new URL(SERVER_URI + "/" + serviceName + "/" + Operation.add);

            Log.d("URL",  url.toString());

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try
            {
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setConnectTimeout(REQUEST_TIME);
                DataOutputStream dos =
                        new DataOutputStream(urlConnection.getOutputStream());
                dos.write(jsonNotification.getBytes("UTF8"), 0,
                        jsonNotification.getBytes("UTF8").length);


                urlConnection.connect();

              /*  if(urlConnection.getResponseCode() != 201)
                {
                    throw new RuntimeException("BLAD" + urlConnection.getResponseCode());
                }*/
            } finally
            {
                urlConnection.disconnect();
            }



        }
    }
}