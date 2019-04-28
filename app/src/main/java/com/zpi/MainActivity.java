package com.zpi;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zpi.ServerConnector.ServerConnector;
import com.zpi.ServerConnector.ServiceName;
import com.zpi.budget.activities.BudgetActivity;
import com.zpi.calendar.activities.CalendarActivity;
import com.zpi.favourites.FavouritesActivity;
import com.zpi.guests.activities.GuestsListActivity;
import com.zpi.model.Category;
import com.zpi.model.Photo;
import com.zpi.model.Service;
import com.zpi.model.Subcategory;
import com.zpi.model.TransportDetails;
import com.zpi.model.WeddingHallDetails;
import com.zpi.searcher.activities.SearcherActivity;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) throws IOException {
        Intent intent;
        switch(view.getId())
        {
            case R.id.budgetImageView:
                Log.d("aktywnosc", "RozmiarWMain:");
                intent = new Intent(MainActivity.this, BudgetActivity.class);
//                ServerConnector<Service> serverConnector = new ServerConnector<>(ServiceName.services);
//                 List<Service> list =serverConnector.getAll();
//
//                Log.d("aktywnosc", "RozmiarWMain:" +String.valueOf(list.size()));
//
//
//                Service service = new Service("Opis", "WrocławNOWYETYTOWANY", "dsadasda",
//                        "609781153", "psliwinska@onet.eu", new Category("SALE"), null,
//                        null, null,
//                        null);
//
//
//                boolean a = serverConnector.add(service);
//                Log.d("aktywnosc", "DODAWANIE:" +a);
//
//                boolean b = serverConnector.delete(14);
//                Log.d("aktywnosc", "USUWANIE:" +b);
//
//                boolean c = serverConnector.update(18,service);
//                Log.d("aktywnosc", "MODYFIKOWANIE:" +c);


                startActivity(intent);
                break;
            case R.id.searcherImageView:
                intent = new Intent(MainActivity.this, SearcherActivity.class);
                startActivity(intent);
                break;
            case R.id.calendarImageView:
                intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.guestsListImage:
                intent = new Intent(MainActivity.this, GuestsListActivity.class);
                startActivity(intent);
                break;
            case R.id.photoImageView:
                intent = new Intent(MainActivity.this, ServiceProviderMainActivity.class);
                startActivity(intent);
                break;
            case R.id.favouritesImageView:
                intent = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(intent);
                break;
        }
    }
}