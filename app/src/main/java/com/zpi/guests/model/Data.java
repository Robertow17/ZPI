package com.zpi.guests.model;

import java.util.ArrayList;

public class Data {

    static ArrayList<Guest> guests = new ArrayList<Guest>();

    public Data()
    { }

    public void setGuests(){
        guests.add(new Guest("Jan Abacki"));
        guests.add(new Guest("Jan Kowalski"));
        guests.add(new Guest("Anna Mucha"));
        guests.add(new Guest("Janusz Tracz"));
        guests.add(new Guest("Zbigniew Tomasz"));
        guests.add(new Guest("Tomasz Judym"));
        guests.add(new Guest("Jan Nowak"));
        guests.add(new Guest("Kasia Bielska"));
        guests.add(new Guest("Adam Mickiewicz"));
        guests.add(new Guest("Adam Małysz"));

        for(int i=0; i<90; i++){
            guests.add(new Guest("Jan Nowak"));
        }


        for (int i=0; i<5;i++){
            guests.get(i).setConfirmed(true);
        }
    }

    public static ArrayList<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest){
        guests.add(guest);
    }

    public void modifyGuest(int position, String name){
        guests.get(position).setName(name);
    }


}
