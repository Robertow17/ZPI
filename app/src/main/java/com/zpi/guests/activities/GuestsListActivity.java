package com.zpi.guests.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpi.R;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zpi.guests.model.Data;
import com.zpi.guests.model.Guest;
import com.zpi.guests.utils.AddGuestDialog;
import com.zpi.guests.utils.GuestsAdapterWithSwipe;

import java.util.List;


public class GuestsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Guest> guests;
    GuestsAdapterWithSwipe guestsAdapterWithSwipe;
    TextView guestsAmount;
    TextView confirmedAmount;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guests_list_main);
        recyclerView = findViewById(R.id.guestsList);
        Data db = new Data();
        guests=db.getGuests();
        guestsAdapterWithSwipe = new GuestsAdapterWithSwipe(this, guests);
        recyclerView.setAdapter(guestsAdapterWithSwipe);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        guestsAmount=findViewById(R.id.invitedAmount);
        confirmedAmount=findViewById(R.id.ConfirmedAmount);
        guestsAmount.setText(String.valueOf(guests.size()));

        setConfirmedAmount();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                AddGuestDialog agd = new AddGuestDialog();
                agd.show(fm,"addDialog");
            }
        });
    }

    public void setConfirmedAmount(){
        confirmedAmount.setText(String.valueOf(countConfirmedGuests()));
    }

    private int countConfirmedGuests(){
        int result =0;
        for (Guest e : guests){
            if(e.isConfirmed())
                result++;
        }
        return result;
    }


    public void notifyChanged(){
        guestsAdapterWithSwipe.notifyDataSetChanged();
    }

    public void setGuestsAmount(){
        guestsAmount.setText(String.valueOf(guests.size()));
    }

}
