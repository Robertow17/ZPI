package com.zpi.calendar.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.zpi.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.calendar_fragment, container, false);


        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/"+ dayOfMonth ;


            }
        });



        Calendar calendar = Calendar.getInstance();
        Date date =  calendar.getTime();
        long time = date.getTime();

        calendarView.setDate(time+100000000, true,false );
        calendarView.setDate(time+300000000, true,false );
        // Inflate the layout for this fragment
        return rootView;

    }
}
