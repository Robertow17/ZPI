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
import com.zpi.guests.model.Data;
import com.zpi.guests.model.Guest;

public class EditGuestDialog extends DialogFragment {
    Data data = new Data();

    public EditGuestDialog() {}

    public static EditGuestDialog newInstance(int position, String name, String phone) {
        EditGuestDialog frag = new EditGuestDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("name", name);
        args.putString("phone", phone);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int pos = getArguments().getInt("position");
        final String current_name = getArguments().getString("name");
        final String current_phone = getArguments().getString("phone");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_edit_guest, null);
        final EditText name = (EditText) v.findViewById(R.id.guest);
        final EditText phone= (EditText) v.findViewById(R.id.phoneNumber);
        name.setText(current_name);
        phone.setText(current_phone);
        builder.setView(v)
                .setPositiveButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!name.getText().toString().equals("")) {
                            data.modifyGuest(pos, name.getText().toString(), phone.getText().toString());
                            GuestsListActivity a = (GuestsListActivity) getActivity();
                            a.notifyChanged();
                            Toast.makeText(v.getContext(), "Edytowano " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(), "Nie wpisano nazwiska gościa. Gość nie został zmodyfikowany.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditGuestDialog.this.getDialog().cancel();
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
