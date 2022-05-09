package com.example.timekeep;

import java.time.LocalTime;
import java.util.ArrayList;

public class HourEvent {
    private LocalTime time;
    private ArrayList<TaskEvent> listOfEvents;

    public ArrayList<TaskEvent> getListOfEvents() {
        return listOfEvents;
    }

    public void setListOfEvents(ArrayList<TaskEvent> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }

    public HourEvent(LocalTime time, ArrayList<TaskEvent> list){
        this.time=time;
        this.listOfEvents = list;
    }
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}
