package com.zpi.ServerConnector;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zpi.model.Service;

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

public class ServerConnectorUtils extends ServerConnector
{
    private List<Service> services;

    public ServerConnectorUtils(ServiceName serviceName)
    {
        super(serviceName);
        services = new ArrayList<>();
    }


    public List<Service> getAllServicesOfUser(String login) {
        try {
            new AsyncTask<Void, Void, Void>()
            {
                @Override
                protected Void doInBackground(Void... voids)
                {
                    try
                    {
                        services = getServices(login);
                    } catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    return null;
                }

            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return services;
    }


    private List<Service> getServices(String login) throws IOException
    {
        URL url = new URL(SERVER_URI + "/" + super.serviceName + "/" + Operation.get + "/" + login);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(RequestType.GET.toString());
        urlConnection.setConnectTimeout(REQUEST_TIME);
        urlConnection.setRequestProperty("Accept", "application/json"); //super.REQUEST_VALUE);
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
        Type listType = new TypeToken<ArrayList<Service>>() {
        }.getType();
        List<Service> list = new Gson().fromJson(data, listType);

        return list;
    }
}
