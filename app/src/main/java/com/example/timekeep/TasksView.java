package com.example.timekeep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TasksView extends AppCompatActivity {

    ArrayList<TaskEvent> list = new ArrayList<TaskEvent>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_view);
        NavButton();
        NewTask();
        if(getIntent().getParcelableExtra("Event") != null){
            list.add(getIntent().getParcelableExtra("Event"));
        }
        list.add(new TaskEvent(LocalDate.now(),LocalTime.now(),"Clean Laundry"));
        list.add(new TaskEvent(LocalDate.now(),LocalTime.now(),"fold Laundry"));
        DisplayEvents();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void DisplayEvents() {
        ListView view = findViewById(R.id.EventList);
        TaskEventAdapter Adapter = new TaskEventAdapter(getApplicationContext(), list);
        view.setAdapter(Adapter);
    }

    public void NewTask(){
        Button NewTask = findViewById(R.id.NewTask);
        NewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TasksView.this, CreateEvent.class);
                startActivity(i);
            }
        });
    }
    public void NavButton(){
        Button NavButton = findViewById(R.id.NavButton);
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });    }
}