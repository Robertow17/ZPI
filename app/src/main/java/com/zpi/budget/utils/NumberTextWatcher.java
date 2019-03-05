package com.zpi.budget.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NumberTextWatcher implements TextWatcher {

    private EditText et;

    public NumberTextWatcher(EditText et) {

        this.et = et;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable edt) {

        String temp = edt.toString();
        int posDot = temp.indexOf(".");

        if (posDot <= 0) {
            return;
        }
        if (temp.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, posDot + 4);
        }


    }
}