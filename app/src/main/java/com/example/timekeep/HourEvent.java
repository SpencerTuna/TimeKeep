package com.example.timekeep;

import java.time.LocalTime;

public class HourEvent {
    LocalTime time;

    public HourEvent(LocalTime time){
        this.time=time;
    }
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


}
