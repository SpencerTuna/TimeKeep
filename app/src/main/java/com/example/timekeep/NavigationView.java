package com.example.timekeep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        CalendarViewButton();
        TaskViewButton();
    }
    public void CalendarViewButton(){
        Button NavButton = findViewById(R.id.CalendarButton);
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void TaskViewButton(){
        Button NavButton = findViewById(R.id.TaskButton);
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationView.this, TasksView.class));
            }
        });
    }
}