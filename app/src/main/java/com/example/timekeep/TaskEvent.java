package com.example.timekeep;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskEvent implements Parcelable {
    private LocalDate TaskDate;
    private LocalTime TaskTime;
    private String TaskName;

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
}
