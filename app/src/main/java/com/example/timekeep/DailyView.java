package com.example.timekeep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyView extends Fragment {
    private TextView DayView;
    private ListView HourView;
    private LocalDate selectedDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_view, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DayView = getView().findViewById(R.id.MonthDay);
        HourView = getView().findViewById(R.id.HourList);
        selectedDate = ((HomeActivity)getActivity()).getSelectedDate();
        SetDayView();
        MakeNewTask();

        Button nextDay = getView().findViewById(R.id.NextDayButton);
        Button prevDay = getView().findViewById(R.id.PreviousDayButton);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.plusDays(1);
                SetDayView();
            }
        });
        prevDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.minusDays(1);
                SetDayView();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SetDayView() {
        DayView.setText(monthDayFromDate(selectedDate));
        HourAdapter hourAdapter = new HourAdapter(getActivity().getApplicationContext(), getHourList());

        HourView.setAdapter(hourAdapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<HourEvent> getHourList() {
        ArrayList<HourEvent> list = new ArrayList<HourEvent>();
        ArrayList<TaskEvent> listOfEvents = new ArrayList<TaskEvent>();
        listOfEvents = ((HomeActivity)getActivity()).getListOfEvents();

        for (int i=0; i<24; i++){
            LocalTime T = LocalTime.of(i,0);
            HourEvent hourEvent = new HourEvent(T, TaskEvent.eventsPerHourByDay(listOfEvents, T, selectedDate));
            list.add(hourEvent);
        }
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthDayFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd");
        return date.format(formatter);
    }

    public void MakeNewTask(){
        Button NewTask = getView().findViewById(R.id.MakeNewTask);
        NewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).SwitchFragment(new CreateEvent());
            }
        });
    }

}