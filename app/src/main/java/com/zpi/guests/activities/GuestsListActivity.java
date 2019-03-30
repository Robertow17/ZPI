package com.zpi.guests.activities;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpi.FileManager.FileManager;
import com.zpi.R;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    FileManager<Guest> fm;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guests_list_main);
        recyclerView = findViewById(R.id.guestsList);

        fm = new FileManager<>("guests");
        guests=fm.getFromFile();
        guestsAdapterWithSwipe = new GuestsAdapterWithSwipe(this, guests);
        recyclerView.setAdapter(guestsAdapterWithSwipe);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fm.saveToFile(guests);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        fm.saveToFile(guests);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        fm.saveToFile(guests);
    }

    public void addGuest(Guest guest){
        guests.add(guest);
    }

    public void modifyGuest(int position, String name, String phone){
        guests.get(position).setName(name);
        guests.get(position).setPhone(phone);
    }
}
