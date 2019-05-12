package com.zpi.checklist.utils;

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
import com.zpi.checklist.activities.CheckListActivity;

public class EditToDoDialog extends DialogFragment {

    public EditToDoDialog() {}

    public static EditToDoDialog newInstance(int position, String name) {
        EditToDoDialog frag = new EditToDoDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("name", name);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int pos = getArguments().getInt("position");
        final String current_name = getArguments().getString("name");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View v = inflater.inflate(R.layout.dialog_edit_to_do, null);
        final EditText name = (EditText) v.findViewById(R.id.guest);
        name.setText(current_name);
        builder.setView(v)
                .setPositiveButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!name.getText().toString().equals("")) {
                            CheckListActivity a = (CheckListActivity) getActivity();
                            a.modifyToDo(pos, name.getText().toString());
                            a.notifyChanged();
                            Toast.makeText(v.getContext(), "Edytowano " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(), "Nie wpisano nazwy sprawy. Sprawa nie została zmodyfikowana.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditToDoDialog.this.getDialog().cancel();
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
