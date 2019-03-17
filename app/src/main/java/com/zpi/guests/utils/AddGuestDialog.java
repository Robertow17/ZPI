package com.zpi.guests.utils;

import android.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.guests.activities.GuestsListActivity;
import com.zpi.guests.model.Data;
import com.zpi.guests.model.Guest;

public class AddGuestDialog extends DialogFragment {
    Data data = new Data();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_add_guest, null);
        builder.setView(v)
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        final EditText name = (EditText) v.findViewById(R.id.guest);
                        data.addGuest(new Guest(name.getText().toString()));
                        GuestsListActivity a = (GuestsListActivity) getActivity();
                        a.setGuestsAmount();
                        Toast.makeText(v.getContext(), "Dodano " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddGuestDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
