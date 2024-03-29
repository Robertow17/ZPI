package com.zpi.guests.utils;

import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zpi.R;
import com.zpi.guests.activities.GuestsListActivity;
import com.zpi.guests.model.Guest;

public class AddGuestDialog extends DialogFragment {

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
                        final EditText phone = (EditText) v.findViewById(R.id.phoneNumber);
                        if (!name.getText().toString().equals("")) {
                            GuestsListActivity a = (GuestsListActivity) getActivity();
                            a.addGuest(new Guest(name.getText().toString(), phone.getText().toString()));
                            a.setGuestsAmount();
                            Toast.makeText(v.getContext(), "Dodano " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(), "Nie wpisano nazwiska gościa. Gość nie został dodany.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddGuestDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Button positive = ((android.support.v7.app.AlertDialog) getDialog()).getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE);
        positive.setTextColor(Color.BLACK);


        Button negative = ((android.support.v7.app.AlertDialog) getDialog()).getButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE);
        negative.setTextColor(Color.BLACK);
    }
}
