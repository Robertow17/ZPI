package com.zpi.guests.model;

import java.util.ArrayList;

public class Data {

    static ArrayList<Guest> guests = new ArrayList<Guest>();

    public Data()
    { }

    public void setGuests(){
        guests.add(new Guest("Jan Abacki", "734854322"));
        guests.add(new Guest("Jan Kowalski", "734854322"));
        guests.add(new Guest("Anna Mucha", "734854322"));
        guests.add(new Guest("Janusz Tracz", "734854322"));
        guests.add(new Guest("Zbigniew Tomasz", "734854322"));
        guests.add(new Guest("Tomasz Judym", "734854322"));
        guests.add(new Guest("Jan Nowak", "734854322"));
        guests.add(new Guest("Kasia Bielska", "734854322"));
        guests.add(new Guest("Adam Mickiewicz", "734854322"));
        guests.add(new Guest("Adam Małysz", "734854322"));

        for(int i=0; i<90; i++){
            guests.add(new Guest("Jan Nowak", "734854322"));
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

    public void modifyGuest(int position, String name, String phone){
        guests.get(position).setName(name);
        guests.get(position).setPhone(phone);
    }


}
