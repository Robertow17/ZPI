package com.zpi.calendar.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.zpi.FileManager.FileManager;
import com.zpi.R;
import com.zpi.calendar.model.WeddingEvent;
import com.zpi.calendar.utils.AddEventDialog;
import com.zpi.calendar.utils.AlarmReceiver;
import com.zpi.calendar.utils.EventAdapterWithSwipe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class CalendarActivity extends AppCompatActivity
    {
        private static final int MEMORY_ACCESS = 5;
        CompactCalendarView compactCalendar;
        RecyclerView recyclerView;
        EventAdapterWithSwipe eventAdapterWithSwipe;
        List<WeddingEvent> events;
        TextView monthHeader;
        ArrayList<WeddingEvent> eventsOfTheMonth;
        TextView eventsInfo;
        FileManager<WeddingEvent> fm;
        List<Event> calendarEvents;
        String[] months = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar_activity);

            eventsInfo = findViewById(R.id.EventsInfoTextView);
            recyclerView = findViewById(R.id.eventsOfTheMonth);

            if (ActivityCompat.shouldShowRequestPermissionRationale(CalendarActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(CalendarActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
            }

            compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
            String names[] = {"Pon", "Wt", "Śr", "Czw", "Pt", "Sob", "Ndz"};
            compactCalendar.setDayColumnNames(names);
            compactCalendar.shouldSelectFirstDayOfMonthOnScroll(false);

            fm = new FileManager<>("events");
            events = fm.getFromFile();

            calendarEvents = new ArrayList<>();
            for(int i=0; i<events.size(); i++){
                WeddingEvent e = events.get(i);
                calendarEvents.add(new Event(Color.RED, e.getTimeInMillis(), e.getTitle()));
                compactCalendar.addEvent(calendarEvents.get(i));
            }

            eventsOfTheMonth = chooseProperEvents(new Date());
            setEventsInfo();

            eventAdapterWithSwipe = new EventAdapterWithSwipe(this, eventsOfTheMonth);
            recyclerView.setAdapter(eventAdapterWithSwipe);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.VERTICAL));

            monthHeader = findViewById(R.id.monthHeader);
            setMonthHeader(new Date());
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
            WeddingEvent e = eventsOfTheMonth.get(position);
            int index = events.indexOf(e);
            compactCalendar.removeEvent(calendarEvents.get(index));
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
                    long result = (o1.getTimeInMillis()) - (o2.getTimeInMillis());
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
                WeddingEvent newWeddingEvent =events.get(events.size() - 1);
                eventsOfTheMonth.add(newWeddingEvent);
                calendarEvents.add(new Event(Color.RED, newWeddingEvent.getTimeInMillis(), newWeddingEvent.getTitle() ));
                compactCalendar.addEvent(calendarEvents.get(calendarEvents.size()-1));
                sortEventsOfMonth(eventsOfTheMonth);
                eventAdapterWithSwipe.notifyDataSetChanged();
                setEventsInfo();
        }

        public void notifyChanged(){
            eventAdapterWithSwipe.notifyDataSetChanged();
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            fm.saveToFile(events);
        }

        @Override
        public void onStop()
        {
            super.onStop();
            fm.saveToFile(events);
        }

        @Override
        public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
            super.onSaveInstanceState(outState, outPersistentState);
            fm.saveToFile(events);
        }

        public void addEvent(WeddingEvent e){
            events.add(e);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent notificationIntent = new Intent(this, AlarmReceiver.class);
            notificationIntent.putExtra("Title", e.getTitle());
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(e.getTimeInMillis());
            cal.add(Calendar.HOUR, -7);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
        }

        public void removerEvent(WeddingEvent e){
            events.remove(e);
        }

        public void editEvent(long time, String currentTitle, String title, String description){
            for(WeddingEvent e : events){
                if(e.getTimeInMillis()==time){
                    if(e.getTitle().equals(currentTitle)){
                        e.setTitle(title);
                        e.setDescription(description);
                        break;
                    }

                }
            }

        }
    }

