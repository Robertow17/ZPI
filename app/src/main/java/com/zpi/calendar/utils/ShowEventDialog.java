package com.zpi.calendar.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zpi.R;

import java.util.Calendar;
import java.util.Date;

public class ShowEventDialog extends DialogFragment {

    public ShowEventDialog() {}

    public static ShowEventDialog newInstance(long time, String title, String description) {
        ShowEventDialog frag = new ShowEventDialog();
        Bundle args = new Bundle();
        args.putLong("time", time);
        args.putString("title", title);
        args.putString("description", description);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long time = getArguments().getLong("time");
        final String evetTitle = getArguments().getString("title");
        final String eventDescription = getArguments().getString("description");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_show_event, null);
        final TextView title = (TextView) v.findViewById(R.id.eventTitle1);
        title.setText(evetTitle);
        final TextView description = (TextView) v.findViewById(R.id.eventDescription1);
        String descriptionFinal = "Opis: "+eventDescription;
        description.setText(descriptionFinal);
        final TextView date = (TextView) v.findViewById(R.id.eventDate1);
        date.setText(getStringDate(time));
        builder.setView(v)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ShowEventDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    private String getStringDate(long time){
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return "Data: "+ String.format("%02d", day)+"."+String.format("%02d", month+1)+"."+String.valueOf(year);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button positive = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(Color.BLACK);


        Button negative = ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_NEGATIVE);
        negative.setTextColor(Color.BLACK);
    }
}
