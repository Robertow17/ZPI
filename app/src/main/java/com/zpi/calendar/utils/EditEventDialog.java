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
import android.widget.EditText;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.calendar.activities.CalendarActivity;

public class EditEventDialog extends DialogFragment {

    public EditEventDialog() {}

    public static EditEventDialog newInstance(long time, String title, String description) {
        EditEventDialog frag = new EditEventDialog();
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

        final View v = inflater.inflate(R.layout.dialog_add_event, null);
        final EditText title = (EditText) v.findViewById(R.id.eventTitle);
        title.setText(evetTitle);
        final EditText description = (EditText) v.findViewById(R.id.eventDescription);
        description.setText(eventDescription);
        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(!title.getText().toString().equals("")) {
                            CalendarActivity a = (CalendarActivity) getActivity();
                            a.editEvent(time, evetTitle, title.getText().toString(), description.getText().toString());
                            a.notifyChanged();
                            Toast.makeText(v.getContext(), "Edytowano " + title.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(),"Nie wpisano tytułu! Wydarzenie nie zostało zmodyfikowane.", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditEventDialog.this.getDialog().cancel();
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

