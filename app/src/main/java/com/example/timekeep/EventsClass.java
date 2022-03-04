package com.example.timekeep;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventsClass {
    private static ArrayList<EventsClass> eventsClassList = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;

    public ArrayList<EventsClass> viewEventsForDay(LocalDate date){
        ArrayList<EventsClass> eventsClasses = new ArrayList<>();

        for (EventsClass eventsClass : eventsClassList){
            if (eventsClass.date == date){
                eventsClasses.add(eventsClass);
            }
        }
        return eventsClasses;
    }
    public ArrayList<EventsClass> viewEventsForDayAndTime(LocalDate date, LocalTime time){
        ArrayList<EventsClass> eventsClasses = new ArrayList<>();

        for (EventsClass eventsClass : eventsClassList){
            if (eventsClass.date == date && eventsClass.time == time){
                eventsClasses.add(eventsClass);
            }
        }
        return eventsClasses;
    }

    public void SetNewEvent(String name, LocalDate date, LocalTime time){
        EventsClass e = new EventsClass();
        e.setName(name);
        e.setDate(date);
        e.setTime(time);
        eventsClassList.add(e);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }





}
