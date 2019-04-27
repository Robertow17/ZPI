package com.zpi.ServerConnector;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
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


    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        Operation operation;

        private BackgroundTask(Operation operation) {
            this.operation = operation;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (operation == Operation.all)
                    result = getAllDataFromServer();
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
    }
}
