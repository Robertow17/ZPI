package com.zpi.budget.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zpi.R;
import com.zpi.budget.model.Expense;
import com.zpi.budget.utils.ExpenseAdapterWithSwipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BudgetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Expense> expenses;
    ExpenseAdapterWithSwipe expenseAdapterWithSwipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);
        recyclerView = findViewById(R.id.recyclerViewOfExpense);
        expenses=setData();
        expenseAdapterWithSwipe = new ExpenseAdapterWithSwipe(this, expenses, null,null, getResources().getString(R.string.currency));
        recyclerView.setAdapter(expenseAdapterWithSwipe);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private List<Expense> setData(){
        Date date = new Date(2019, 2, 10);
        Expense expense1_1 = new Expense("suknia ślubna", "Moda", 21.32, date);
        Expense expense1_2 = new Expense("kwiaty", "Dekoracje", 10.32, date);
        Expense expense1_3 = new Expense("fryzjer", "Moda", 102.32, date);
        Expense expense1_4 = new Expense("fotograf", "Fotografia", 101.32, date);
        Expense expense1_5 = new Expense("kosmetyczka", "Moda", 103.32, date);
        Expense expense1_6 = new Expense("samochód", "Transport", 10.32, date);
        Expense expense1_7 = new Expense("buty", "Moda", 10.32, date);
        Expense expense1_8 = new Expense("welon", "Moda", 10.32, date);
        Expense expense1_9 = new Expense("buty", "Moda", 13123.23, date);

        List<Expense> expenses = new ArrayList<>();
        expenses.add(expense1_1);
        expenses.add(expense1_2);
        expenses.add(expense1_3);
        expenses.add(expense1_4);
        expenses.add(expense1_5);
        expenses.add(expense1_6);
        expenses.add(expense1_7);
        expenses.add(expense1_8);
        expenses.add(expense1_9);
        return expenses;
    }
}
