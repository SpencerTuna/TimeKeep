package com.example.timekeep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateEvent extends AppCompatActivity{
    private TaskEvent taskEvent;
    private TextView date;
    private TextView time;
    int hour, minute;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        date = findViewById(R.id.EventDate);
        time = findViewById(R.id.EventTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        date.setText(formatter.format(LocalDate.now()));

        Button finish = findViewById(R.id.NewTask);
        finish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                taskEvent = new TaskEvent(ConvertDate(), ConvertTime(), ConvertName());
                Intent i = new Intent(CreateEvent.this, TasksView.class);
                i.putExtra("Event", taskEvent);
                startActivity(i);
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
        EditText editText = findViewById(R.id.EventName);
        String t = editText.getText().toString();
        return t;
    }

    public void DatePicker(View view) {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String d = month + "-" + dayOfMonth + "-" + year;
                date.setText(d);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
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
                this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }
}