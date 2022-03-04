package com.example.timekeep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyView extends AppCompatActivity {
    private TextView DayView;
    private ListView HourView;
//    private ListView EventView;
    private LocalDate selectedDate;
//    private LocalTime selectedTime;
    EventsClass events = new EventsClass();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_view);
        int y = getIntent().getIntExtra("Year", 1);
        int m = getIntent().getIntExtra("Month",1);
        int d = getIntent().getIntExtra("Day", 1);
        initWidgets();
        selectedDate = selectedDate.of(y, m ,d);
//        selectedTime = LocalTime.now();
        SetDayView();
    }
    private void initWidgets(){

        DayView = findViewById(R.id.MonthDay);
        HourView = findViewById(R.id.HourList);
//        EventView = findViewById(R.id.EventList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SetDayView() {

        DayView.setText(monthDayFromDate(selectedDate));
        HourAdapter hourAdapter = new HourAdapter(getApplicationContext(), getHourList());
//        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), getEventList());
        HourView.setAdapter(hourAdapter);
//        EventView.setAdapter(eventAdapter);
    }

  /*  private List<EventsClass> getEventList() {
        return events.viewEventsForDayAndTime(selectedDate, selectedTime);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<HourEvent> getHourList() {
        ArrayList<HourEvent> list = new ArrayList<HourEvent>();

        for (int i=0; i<24; i++){
            LocalTime T = LocalTime.of(i,0);
            HourEvent hourEvent = new HourEvent(T);
            list.add(hourEvent);
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthDayFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void NextDayAction(View view){
        selectedDate = selectedDate.plusDays(1);
        SetDayView();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void PreviousDayAction(View view){
        selectedDate = selectedDate.minusDays(1);
        SetDayView();
    }
}