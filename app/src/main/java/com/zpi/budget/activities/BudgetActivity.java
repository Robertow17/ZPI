package com.zpi.budget.activities;
import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.zpi.FileManager.FileManager;
import com.zpi.R;
import com.zpi.budget.model.Expense;
import com.zpi.budget.utils.ExpenseAdapterWithSwipe;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BudgetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Expense> expenses;
    ExpenseAdapterWithSwipe expenseAdapterWithSwipe;
    FileManager<Expense> fileManager;
    private static final int MEMORY_ACCESS = 5;
    private static final int EDIT_EXPENSE_ACTIVITY_REQUEST_CODE = 1;
    private static final int ADD_EXPENSE_ACTIVITY_REQUEST_CODE = 2;
    private static final int DELETE_EXPENSE_ACTIVITY_REQUEST_CODE = 21;
    String[] categories = {"Sale", "Muzyka", "Fotografia", "Dekoracje", "Transport", "Moda", "Inne"};
    int[] categioriesImageId = {R.drawable.icon_weddinghalls, R.drawable.icon_music, R.drawable.icon_camera, R.drawable.icon_decoration, R.drawable.icon_transport, R.drawable.icon_fashion, R.drawable.icon_another};
    String[] categoriesColors = {"#d9ba97","#a186be","#7da7d9","#b7b7b7","#f5989d","#f0dd83","#c4df9b"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aktywnosc","onCreate");
        setContentView(R.layout.budget_main_activity);
        recyclerView = findViewById(R.id.recyclerViewOfExpense);
        if (ActivityCompat.shouldShowRequestPermissionRationale(BudgetActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            ActivityCompat.requestPermissions(BudgetActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }
        fileManager = new FileManager<>("expenses") ;
        expenses = fileManager.getFromFile();

//        expenses=setData();
        expenseAdapterWithSwipe = new ExpenseAdapterWithSwipe(this, expenses, categories,categioriesImageId, getResources().getString(R.string.currency));
        recyclerView.setAdapter(expenseAdapterWithSwipe);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setCardInfo();
        addExpense();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        fileManager.saveToFile(expenses);
    }


    private List<Expense> setData(){
        Date date = new Date(2019, 3, 10);
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("sala weselna", "Sale", 2000.00, date));
        expenses.add(new Expense("zespół weselny", "Muzyka", 1500.00, date));
        expenses.add(new Expense("suknia ślubna", "Moda", 800.00, date));
        expenses.add(new Expense("fotograf", "Fotografia", 850.50, date));
        expenses.add(new Expense("dekoracje weselne", "Dekoracje", 300.00, date));
        expenses.add(new Expense("samochód", "Transport", 300.00, date));
        expenses.add(new Expense("buty", "Moda", 10.32, date));
        expenses.add(new Expense("welon", "Moda", 10.32, date));
        expenses.add(new Expense("zaproszenia ślubne", "Inne", 850.23, date));
        return expenses;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("aktywnosc","onActivityResult");
        if (resultCode == EDIT_EXPENSE_ACTIVITY_REQUEST_CODE) {

            if (data.getSerializableExtra("action").equals("back")) {
                Log.d("aktywnosc","powrot");
            } else {
                int position = (int) data.getSerializableExtra("newPosition");
                expenses.get(position).setDate((Date) data.getSerializableExtra("newDate"));
                expenses.get(position).setName((String) data.getSerializableExtra("newName"));
                expenses.get(position).setCategory((String) data.getSerializableExtra("newCategory"));
                expenses.get(position).setPrice((Double) data.getSerializableExtra("newPrice") * 1);
                Log.d("aktywnosc", expenses.get(position).getName());
                expenseAdapterWithSwipe.notifyDataSetChanged();
                setCardInfo();
            }
        }
        if (requestCode == ADD_EXPENSE_ACTIVITY_REQUEST_CODE) {

            if (data.getSerializableExtra("action").equals("back")) {
                Log.d("aktywnosc","powrot");
            } else
            if(data.getSerializableExtra("action").equals("expense")){
                Expense expense = (Expense) data.getSerializableExtra("expense");
                expenses.add(0, expense);
                expenseAdapterWithSwipe.notifyItemInserted(0);
                Log.d("aktywnosc", "dodano expense");
                setCardInfo();
            }
        }
        if (requestCode == DELETE_EXPENSE_ACTIVITY_REQUEST_CODE) {
            setCardInfo();
        }
    }

    public void setCardInfo() {
        setPieChart();
        TextView expensesTextView = (TextView) findViewById(R.id.ExpensesTextView);
        Double[] results = getBudget(expenses);
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#0.00", otherSymbols);
        expensesTextView.setText(df.format(results[1]) + getResources().getString(R.string.currency));
    }

    public Double[] getBudget(List<Expense> expenses) {
        double expense = 0;
        double incomes = 0;
        double balance;
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getPrice() < 0) {
                expense = expense + expenses.get(i).getPrice();
            } else {
                incomes = incomes + expenses.get(i).getPrice();
            }
        }
        balance = expense + incomes;
        Double[] results = new Double[3];
        results[0] = expense;
        results[1] = incomes;
        results[2] = balance;
        return results;
    }

    private void setPieChart() {
        String xValue[] = categories;
        double yValue[] = new double[categories.length];
        for (int i = 0; i < yValue.length; i++) {
            yValue[i] = 0;
        }
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < expenses.size(); i++) {
            for (int j = 0; j < yValue.length; j++) {
                if (expenses.get(i).getCategory().equals(xValue[j])) {
                    yValue[j] = yValue[j] + expenses.get(i).getPrice();
                }
            }
        }
        for (int i = 0; i < xValue.length; i++) {
            pieEntries.add(new PieEntry((float) yValue[i] * 1, xValue[i]));
        }

        int[]colors= new int[categoriesColors.length];
        for (int i=0;i<categoriesColors.length;i++)
        {
            colors[i] = Color.parseColor(categoriesColors[i]);
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        // dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        PieData data = new PieData(dataSet);
        PieChart chart = findViewById(R.id.pieChart);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawHoleEnabled(true);
        chart.setEntryLabelTextSize(0);
        chart.setEntryLabelColor(Color.DKGRAY);
        // chart.setHoleColorTransparent(true);
        //chart.setDrawHoleEnabled(false
        // );
        data.setValueFormatter(new ValueFormatter());
        //  data.setValueTextColor(Color.DKGRAY);
        data.setValueTextColor(Color.WHITE);
        //Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        //data.setValueTypeface(typeface);
        data.setValueTextSize(13);
        chart.setHoleRadius(45);
        chart.animateY(1000);
        chart.setData(data);
        chart.invalidate();
    }

    private class ValueFormatter implements IValueFormatter {
        private DecimalFormat mFormat;
        public ValueFormatter() {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
            otherSymbols.setDecimalSeparator('.');
            mFormat = new DecimalFormat("#####0.00", otherSymbols);
        }
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            if (value==0)
            {
                return "";
            }
            return mFormat.format(value);
        }
    }

    public void addExpense() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(BudgetActivity.this, AddExpenseActivity.class);
                intent.putExtra("categories", categories);
                startActivityForResult(intent, ADD_EXPENSE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("aktywnosc","onBackPressed");
        fileManager.saveToFile(expenses);
    }
}
