package com.zpi.checklist.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zpi.R;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;

import com.zpi.FileManager.FileManager;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zpi.checklist.model.ToDo;
import com.zpi.checklist.utils.AddToDoDialog;
import com.zpi.checklist.utils.CheckListAdapterWithSwipe;


import java.util.List;


public class CheckListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ToDo> toDo;
    CheckListAdapterWithSwipe checkListAdapterWithSwipe;
    TextView toDoAmount;
    TextView doneAmount;
    FileManager<ToDo> fm;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_list_main);
        recyclerView = findViewById(R.id.checkList);

        fm = new FileManager<>("toDo");
        toDo =fm.getFromFile();
        checkListAdapterWithSwipe = new CheckListAdapterWithSwipe(this, toDo);
        recyclerView.setAdapter(checkListAdapterWithSwipe);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        toDoAmount =findViewById(R.id.toDoAmount);
        doneAmount =findViewById(R.id.doneAmount);
        toDoAmount.setText(String.valueOf(toDo.size()));

        setDoneAmount();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                AddToDoDialog agd = new AddToDoDialog();
                agd.show(fm,"addDialog");
            }
        });
    }

    public void setDoneAmount(){
        doneAmount.setText(String.valueOf(countConfirmedGuests()));
    }

    private int countConfirmedGuests(){
        int result =0;
        for (ToDo e : toDo){
            if(e.isDone())
                result++;
        }
        return result;
    }


    public void notifyChanged(){
        checkListAdapterWithSwipe.notifyDataSetChanged();
    }

    public void setToDoAmount(){
        toDoAmount.setText(String.valueOf(toDo.size()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fm.saveToFile(toDo);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        fm.saveToFile(toDo);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        fm.saveToFile(toDo);
    }

    public void addToDo(ToDo toDO){
        toDo.add(toDO);
    }

    public void modifyToDo(int position, String name){
        toDo.get(position).setName(name);

    }
}
