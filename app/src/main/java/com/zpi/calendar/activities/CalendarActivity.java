package com.zpi.calendar.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.zpi.R;
import com.zpi.calendar.model.Data;
import com.zpi.calendar.model.WeddingEvent;
import com.zpi.calendar.utils.AddEventDialog;
import com.zpi.calendar.utils.EventAdapterWithSwipe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class CalendarActivity extends AppCompatActivity
    {
        CompactCalendarView compactCalendar;
        RecyclerView recyclerView;
        EventAdapterWithSwipe eventAdapterWithSwipe;
        ArrayList<WeddingEvent> events;
        Toolbar toolbar;
        TextView monthHeader;
        ArrayList<WeddingEvent> eventsOfTheMonth;
        TextView eventsInfo;
        String[] months = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar_activity);

            eventsInfo = findViewById(R.id.EventsInfoTextView);
            recyclerView = findViewById(R.id.eventsOfTheMonth);
            Data db = new Data();
            if(db.getEvents().isEmpty()){
                db.setEvents();
            }
            events = db.getEvents();
            eventsOfTheMonth = chooseProperEvents(new Date());
            setEventsInfo();
            eventAdapterWithSwipe = new EventAdapterWithSwipe(this, eventsOfTheMonth);
            recyclerView.setAdapter(eventAdapterWithSwipe);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            monthHeader = findViewById(R.id.monthHeader);
            setMonthHeader(new Date());

            compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
            String names[] = {"Pon", "Wt", "Śr", "Czw", "Pt", "Sob", "Ndz"};
            compactCalendar.setDayColumnNames(names);
            compactCalendar.shouldSelectFirstDayOfMonthOnScroll(false);


            for(WeddingEvent e : events){
                compactCalendar.addEvent(e.getEvent());
            }

            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    FragmentManager fm = getSupportFragmentManager();
                    AddEventDialog aed = AddEventDialog.newInstance(dateClicked);
                    aed.show(fm, "addDialog");

                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    setMonthHeader(firstDayOfNewMonth);
                    eventsOfTheMonth = chooseProperEvents(firstDayOfNewMonth);
                    changeEventsOfMonth();
                }
            });


        }

        public void notifyRemovedEvent(int position){
            WeddingEvent e = eventsOfTheMonth .get(position);
            compactCalendar.removeEvent(e.getEvent());
        }

        public void changeEventsOfMonth(){
            eventAdapterWithSwipe.setEventsList(eventsOfTheMonth);
            eventAdapterWithSwipe.notifyDataSetChanged();
            setEventsInfo();
        }

        public ArrayList<WeddingEvent> chooseProperEvents(Date firstDayOfMonth){
            int month = getMonthFromDate(firstDayOfMonth);
            int year = getYearFromDate(firstDayOfMonth);
            ArrayList<WeddingEvent> helper = new ArrayList<>();
            for(WeddingEvent e : events){
                if (e.getMonth()==month && e.getYear()==year){
                    helper.add(e);
                }
            }
            sortEventsOfMonth(helper);
            return helper;
        }

        private void sortEventsOfMonth(ArrayList<WeddingEvent> helper) {
            Collections.sort(helper, new Comparator<WeddingEvent>() {
                @Override
                public int compare(WeddingEvent o1, WeddingEvent o2) {
                    long result = (o1.getEvent().getTimeInMillis()) - (o2.getEvent().getTimeInMillis());
                    if(result<0)
                        return -1;
                    else if(result==0)
                        return 0;
                    else return 1;
                }
            });
        }

        private int getMonthFromDate(Date date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.MONTH);
        }

        private int getYearFromDate(Date date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR);
        }

        public void setEventsInfo(){
            if(eventsOfTheMonth.size()==0){
                eventsInfo.setText("W tym miesiącu nie masz nadchodzących wydarzeń");
            }
            else{
                eventsInfo.setText("Nadchodzące wydarzenia:");
            }
        }

        private void setMonthHeader(Date date){
           int month = getMonthFromDate(date);
           int year = getYearFromDate(date);
           String header = months[month] + " " + String.valueOf(year);
           monthHeader.setText(header);
        }

        public void notifyNewEvent(){
            eventsOfTheMonth.add(events.get(events.size()-1));
            compactCalendar.addEvent(eventsOfTheMonth.get(eventsOfTheMonth.size()-1).getEvent());
            sortEventsOfMonth(eventsOfTheMonth);
            eventAdapterWithSwipe.notifyDataSetChanged();
            setEventsInfo();
        }

        public void notifyChanged(){
            eventAdapterWithSwipe.notifyDataSetChanged();
        }
    }

