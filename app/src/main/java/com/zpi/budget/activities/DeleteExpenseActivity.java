package com.zpi.budget.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpi.R;

public class DeleteExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_delete_expense_activity);
        Intent intent = new Intent();
        setResult(21,intent);
        finish();
    }
}
