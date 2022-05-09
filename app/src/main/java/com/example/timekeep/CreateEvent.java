package com.example.timekeep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class CreateEvent extends Fragment {
    private TaskEvent taskEvent;
    private TextView date;
    private TextView time;
    int hour, minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_event, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = getView().findViewById(R.id.EventDate);
        time = getView().findViewById(R.id.EventTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        date.setText(formatter.format(LocalDate.now()));

        TextView TimePicker = getView().findViewById(R.id.EventTime);
        TextView DatePicker = getView().findViewById(R.id.EventDate);
        TimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker(getView());
            }
        });
        DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker(getView());
            }
        });

        Button finish = getView().findViewById(R.id.NewTask);
        finish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                taskEvent = new TaskEvent(ConvertDate(), ConvertTime(), ConvertName());
                System.out.println(taskEvent.getTaskDate());
                ((HomeActivity)getActivity()).AddEvent(taskEvent);
                if (((HomeActivity)getActivity()).getItemSelected() == 1) {
                    ((HomeActivity) getActivity()).SwitchFragment(new TasksView());
                }else if (((HomeActivity)getActivity()).getItemSelected() == 0){
                    ((HomeActivity) getActivity()).SwitchFragment(new CalendarFragment());
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate ConvertDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        String d = date.getText().toString();
        return LocalDate.parse(d, formatter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalTime ConvertTime(){
        String t = time.getText().toString();
        return LocalTime.parse(t);
    }
    public String ConvertName(){
        EditText editText = getView().findViewById(R.id.EventName);
        String t = editText.getText().toString();
        return t;
    }

    public void DatePicker(View view) {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String d = month + "-" + dayOfMonth + "-" + year;
                date.setText(d);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                onDateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void TimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                time.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(), style, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }
}