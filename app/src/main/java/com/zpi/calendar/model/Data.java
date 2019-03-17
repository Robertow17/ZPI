package com.zpi.calendar.model;

import java.util.ArrayList;
import java.util.Date;

public class Data {
    static ArrayList<WeddingEvent> events = new ArrayList<>();


    public void setEvents() {
        Date date = new Date();

        events.add(new WeddingEvent(new Date(date.getTime()+(269000000L)), "Spotkanie w Willi Zacisze", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(2*269000000L)), "Spotkanie z fotografem", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(3*269000000L)), "Spotkanie w zespołem Bajm", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(4*269000000L)), "Zapłata zaliczki za salę weselna", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(5*269000000L)), "Próbny makijaż", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(6*269000000L)), "Spotkanie z kamerzystą", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(7*269000000L)), "Próbna fryzura", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(8*269000000L)), "Rezerwacja w salonie sukien ślubych", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(9*269000000L)), "Spotkanie w hotelu Metro", "Oglądanie sali o 13:00"));
        events.add(new WeddingEvent(new Date(date.getTime()+(10*269000000L)), "Zapłata zaliczki za fotografa", "Oglądanie sali o 13:00"));

        events.add(new WeddingEvent(new Date(date.getTime()+(40*269000000L)), "Spotkanie w Willi Zacisze", "Oglądanie sali o 13:00"));
    }

    public static ArrayList<WeddingEvent> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<WeddingEvent> events) {
        Data.events = events;
    }

    public void addEvent(WeddingEvent e){
        events.add(e);
    }

    public void removerEvent(WeddingEvent e){
        events.remove(e);
    }

    public void editEvent(long time, String currentTitle, String title, String description){
        for(WeddingEvent e : events){
            if(e.getEvent().getTimeInMillis()==time){
                if(e.getTitle().equals(currentTitle)){
                    e.setTitle(title);
                    e.setDescription(description);
                }

            }
        }

    }
}