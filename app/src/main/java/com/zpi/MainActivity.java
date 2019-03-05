package com.zpi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zpi.budget.activities.BudgetActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.budgetImageView:
                    intent = new Intent(MainActivity.this, BudgetActivity.class);
                    startActivity(intent);
                    break;
        }
    }
}
