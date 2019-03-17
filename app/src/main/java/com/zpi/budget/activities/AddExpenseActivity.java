package com.zpi.budget.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.budget.model.Expense;
import com.zpi.budget.utils.NumberTextWatcher;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {


    TextView dateTextView;
    EditText nameEditText;
    Spinner categorySpinner;
    EditText priceEditText;
    String[] categories;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_add_expense_activity);
        //NAME
        nameEditText = findViewById(R.id.ExpenseNameEditText);
        //CATEGORY
        categorySpinner = findViewById(R.id.categorySpinner);
        categories = (String[]) getIntent().getSerializableExtra("categories");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.budget_spinner_style, categories) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(18);
                return v;
            }
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
        };
        // ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,categories);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(0);
        //DATE
        dateTextView = findViewById(R.id.nameTextView);
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        dateTextView.setText(day + "/" + (month + 1) + "/" + year);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearOnSet, int monthOnSet, int dayOnSet) {
                        dateTextView.setText(dayOnSet + "/" + (monthOnSet + 1) + "/" + yearOnSet);
                        year = yearOnSet;
                        month = monthOnSet;
                        day = dayOnSet;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //PRICE
        priceEditText = findViewById(R.id.priceEditText);
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", otherSymbols);
        // double pr = 0;
        // priceEditText.setText(df.format(pr));
        priceEditText.addTextChangedListener(new NumberTextWatcher(priceEditText));
        addExpense();
    }


    public void addExpense() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(priceEditText.getText().toString()) || Double.parseDouble(priceEditText.getText().toString()) == 0) {
                    Snackbar.make(view, "Musisz ustawić cenę", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (nameEditText.getText().toString().matches("")) {
                        Snackbar.make(view, "Musisz ustawić nazwę", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("action", "expense");
                        Expense expense = new Expense(nameEditText.getText().toString(), categories[categorySpinner.getSelectedItemPosition()], Double.parseDouble(priceEditText.getText().toString()) * 1, new Date(year, month - 1, day));
                        intent.putExtra("expense", expense);
                        setResult(2, intent);
                        finish();
                    }
                }
            }
        });
    }
}
