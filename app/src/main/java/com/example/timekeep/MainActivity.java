package com.example.timekeep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView MonthYearText;
    private RecyclerView CalendarRecyclerView;
    private LocalDate SelectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        SelectedDate = LocalDate.now();
        setMonthView();
        NavButton();
    }

    private void initWidgets() {
        CalendarRecyclerView = findViewById(R.id.CalendarRecyclerView);
        MonthYearText = findViewById(R.id.MonthNameYear);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        MonthYearText.setText(monthYearFromDate(SelectedDate));
        ArrayList<String> daysInMonth = DaysInMonth(SelectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        CalendarRecyclerView.setLayoutManager(layoutManager);
        CalendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> DaysInMonth(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = SelectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++) {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void PreviousMonthAction(View view) {
        SelectedDate = SelectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void NextMonthAction(View view) {
        SelectedDate = SelectedDate.plusMonths(1);
        setMonthView();
    }
    public void NavButton(){
        Button NavButton = findViewById(R.id.NavButton);
        NavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NavigationView.class);
                startActivity(i);
            }
        });    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        Intent i = new Intent(MainActivity.this, DailyView.class);
        SelectedDate = SelectedDate.of(SelectedDate.getYear(), SelectedDate.getMonthValue(), position-1);
        i.putExtra("Month", SelectedDate.getMonthValue());
        i.putExtra("Year", SelectedDate.getYear());
        i.putExtra("Day", SelectedDate.getDayOfMonth());
        startActivity(i);
    }
}