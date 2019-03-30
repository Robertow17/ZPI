package com.zpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zpi.budget.activities.BudgetActivity;
import com.zpi.calendar.activities.CalendarActivity;
import com.zpi.guests.activities.GuestsListActivity;
import com.zpi.searcher.activities.SearcherActivity;
import com.zpi.serviceProvider.activities.ServiceProviderMainActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view)
    {
        Intent intent;
        switch(view.getId())
        {
            case R.id.budgetImageView:
                intent = new Intent(MainActivity.this, BudgetActivity.class);
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
        }
    }
}
