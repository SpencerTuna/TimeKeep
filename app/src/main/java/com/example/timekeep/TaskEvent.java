package com.example.timekeep;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskEvent implements Parcelable {
    private LocalDate TaskDate;
    private LocalTime TaskTime;
    private String TaskName;
    private boolean isChecked;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<TaskEvent> eventsPerHourByDay(ArrayList<TaskEvent> listOfEvents, LocalTime time, LocalDate date){
        ArrayList<TaskEvent> eventsPerHour = new ArrayList<>();
        for (int i = 0; i < listOfEvents.size(); i++){
            LocalDate d = listOfEvents.get(i).TaskDate;
            if (listOfEvents.get(i).getTaskTime().getHour() == time.getHour()
                    && listOfEvents.get(i).getTaskDate().getDayOfMonth() == date.getDayOfMonth()
                    && listOfEvents.get(i).getTaskDate().getMonthValue() == date.getMonthValue()
                    && listOfEvents.get(i).getTaskDate().getYear() == date.getYear()){
                eventsPerHour.add(listOfEvents.get(i));
            }
        }
        return  eventsPerHour;
    }

    protected TaskEvent(Parcel in) {
        TaskName = in.readString();
    }

    public static final Creator<TaskEvent> CREATOR = new Creator<TaskEvent>() {
        @Override
        public TaskEvent createFromParcel(Parcel in) {
            return new TaskEvent(in);
        }

        @Override
        public TaskEvent[] newArray(int size) {
            return new TaskEvent[size];
        }
    };

    public LocalDate getTaskDate() {
        return TaskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        TaskDate = taskDate;
    }

    public LocalTime getTaskTime() {
        return TaskTime;
    }

    public void setTaskTime(LocalTime taskTime) {
        TaskTime = taskTime;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public TaskEvent(LocalDate taskDate, LocalTime taskTime, String taskName) {
        TaskDate = taskDate;
        TaskTime = taskTime;
        TaskName = taskName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(TaskName);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
