package com.example.timekeep;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEvent> {
    public HourAdapter(@NonNull Context context, @NonNull List<HourEvent> objects) {
        super(context, 0, objects);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        HourEvent event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);


        setEvents(event.getListOfEvents(), convertView);

        setHour(convertView, event.getTime());

        return convertView;
    }

    private void setEvents(ArrayList<TaskEvent> list, View view){
        TextView event1 = view.findViewById(R.id.event1);
        TextView event2 = view.findViewById(R.id.event2);
        TextView event3 = view.findViewById(R.id.event3);
        TextView event4 = view.findViewById(R.id.moreEvents);

        event4.setVisibility(View.INVISIBLE);
        if (list.size() == 0){
            event1.setVisibility(View.INVISIBLE);
            event2.setVisibility(View.INVISIBLE);
            event3.setVisibility(View.INVISIBLE);
        }else if (list.size() == 1){
            event1.setText(list.get(0).getTaskName());
            event1.setVisibility(View.VISIBLE);
            event2.setVisibility(View.INVISIBLE);
            event3.setVisibility(View.INVISIBLE);
        }else if (list.size() == 2){
            event1.setText(list.get(0).getTaskName());
            event1.setVisibility(View.VISIBLE);
            event2.setText(list.get(1).getTaskName());
            event2.setVisibility(View.VISIBLE);
            event3.setVisibility(View.INVISIBLE);
        }else if (list.size() == 3){
            event1.setText(list.get(0).getTaskName());
            event1.setVisibility(View.VISIBLE);
            event2.setText(list.get(1).getTaskName());
            event2.setVisibility(View.VISIBLE);
            event3.setText(list.get(2).getTaskName());
            event3.setVisibility(View.VISIBLE);
        }else {
            event1.setText(list.get(0).getTaskName());
            event1.setVisibility(View.VISIBLE);
            event2.setText(list.get(1).getTaskName());
            event2.setVisibility(View.VISIBLE);
            event3.setText(list.get(2).getTaskName());
            event3.setVisibility(View.VISIBLE);
            event4.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setHour(View convertView, LocalTime time)
    {
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        String n = String.valueOf(time.getHour());
        timeTV.setText(n);
    }
}
