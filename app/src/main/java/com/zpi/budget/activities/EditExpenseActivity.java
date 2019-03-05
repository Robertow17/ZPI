package com.zpi.budget.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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

public class EditExpenseActivity extends AppCompatActivity {


    int day,month,year=0;

    TextView dateTextView;
    Expense expense;
    EditText nameEditText;
    Spinner categorySpinner;
    EditText priceEditText;
    Toolbar toolbar;

    String [] categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_edit_expense_activity);

//        toolbar = (Toolbar) findViewById(R.id.toolbar5);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setToolbarFont();

        dateTextView = findViewById(R.id.dateTextView);
        expense = (Expense) getIntent().getSerializableExtra("expense");

        //Edit Date
        final Date date = expense.getDate(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR)-1900;
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        dateTextView.setText(day+"/"+(month+1)+"/"+year);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearOnSet, int monthOnSet, int dayOnSet) {
                        dateTextView.setText(dayOnSet+"/"+(monthOnSet+1)+"/"+yearOnSet);
                        year = yearOnSet;
                        month = monthOnSet;
                        day=dayOnSet;



                    }
                },year,month,day);
                datePickerDialog.show();
            }

        });

        // NAME
        nameEditText = findViewById(R.id.ExpenseNameEditText);
        nameEditText.setText(expense.getName());

        //CATEGORY
        categorySpinner = findViewById(R.id.categorySpinner);
        categories = (String[]) getIntent().getSerializableExtra("categories");

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.budget_spinner_style,categories) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(18);
                return v;
            }
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,parent);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
        };

        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(getCategoryPosition());

        //PRICE
        priceEditText=findViewById(R.id.priceEditText);
        // priceEditText.addTextChangedListener(new NumberTextWatcher(priceEditText, "%.2f $"));

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00",otherSymbols);

        if (expense.getPrice()<0)
        {
            priceEditText.setText(df.format(expense.getPrice()*-1));
        }
        else
        {
            priceEditText.setText(df.format(expense.getPrice()));
        }

        priceEditText.addTextChangedListener(new NumberTextWatcher(priceEditText));

        saveChange();
    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        Log.d("aktywnosc","destroy edytowanie");
        super.onDestroy();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("action","back");
        setResult(1,intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent();
        setResult(-11,intent);
        finish();

    }

    public int getCategoryPosition(){
        int position =0;

        for (int i=0;i<categories.length;i++){
            if (categories[i].equals(expense.getCategory()))
            {
                position=i;
            }
        }
        return position;
    }

    public void saveChange()
    {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(priceEditText.getText().toString())|| Double.parseDouble(priceEditText.getText().toString()) == 0)
                {
                    Snackbar.make(view, "Musisz ustawić cenę", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    if(nameEditText.getText().toString().matches(""))
                    {
                        Snackbar.make(view, "Musisz ustawić nazwę", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else
                    {
                        Log.d("aktywnosc", "zapis expense");
                        //DATA
                        Date date = new Date(year, month, day);
                        Log.d("aktywnosc", String.valueOf(date.getDate() + "/") + String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear()));
                        //NAME
                        String name = nameEditText.getText().toString();
                        //CATEGORY
                        String category = categories[categorySpinner.getSelectedItemPosition()];
                        //PRICE
                        double price;
                        if (TextUtils.isEmpty(priceEditText.getText().toString())) {
                            price = expense.getPrice();
                            Log.d("aktywnosc", "pusty");
                        } else {
                            price = Double.parseDouble(priceEditText.getText().toString());
                        }
                        Intent intent = new Intent();
                        intent.putExtra("action", "save");
                        intent.putExtra("newDate", date);
                        intent.putExtra("newName", name);
                        intent.putExtra("newCategory", category);
                        intent.putExtra("newPrice", price);
                        intent.putExtra("oldPrice", expense.getPrice());
                        intent.putExtra("newPosition", getIntent().getSerializableExtra("position"));
                        setResult(1, intent);
                        finish();
                    }
                }
            }

        });
    }
}
