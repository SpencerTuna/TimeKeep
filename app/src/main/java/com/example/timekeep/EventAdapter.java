package com.example.timekeep;

import android.content.Context;
import android.media.metrics.Event;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<EventsClass> {


    public EventAdapter(@NonNull Context context,  @NonNull List<EventsClass> objects) {
        super(context, 0, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        EventsClass event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView EventName = convertView.findViewById(R.id.EventName);

        EventName.setText("hello");

        return convertView;
    }

}
