package com.zpi.calendar.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.calendar.activities.CalendarActivity;
import com.zpi.calendar.model.Data;
import com.zpi.calendar.model.WeddingEvent;

import java.util.Date;

public class AddEventDialog extends DialogFragment {
    Data data = new Data();

    public AddEventDialog() {}

    public static AddEventDialog newInstance(Date date) {
        AddEventDialog frag = new AddEventDialog();
        Bundle args = new Bundle();
        args.putLong("time", date.getTime());
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long time = getArguments().getLong("time");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_add_event, null);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
        final EditText title = (EditText) v.findViewById(R.id.eventTitle);
        final EditText description = (EditText) v.findViewById(R.id.eventDescription);
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if(!title.getText().toString().equals("")) {
                            data.addEvent(new WeddingEvent(new Date(time), title.getText().toString(), description.getText().toString()));
                            CalendarActivity a = (CalendarActivity) getActivity();
                            a.notifyNewEvent();
                            Toast.makeText(v.getContext(), "Dodano " + title.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(),"Nie wpisano tytułu! Wydarzenie nie zostało dodane.", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddEventDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
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
