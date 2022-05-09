package com.example.timekeep;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskEventAdapter extends ArrayAdapter<TaskEvent> {
    boolean[] checked;

    public TaskEventAdapter(@NonNull Context context, @NonNull List<TaskEvent> objects) {
        super(context, 0, objects);
        checked = new boolean[objects.size()];
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        TaskEvent event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.taskevent_cell, parent, false);

        TextView eventname = convertView.findViewById(R.id.TaskEventName);
        CheckBox checkBox = convertView.findViewById(R.id.CrossOutEvent);
        eventname.setText(event.getTaskName() + " " + event.getTaskTime().getHour() +":"
                + event.getTaskTime().getMinute() + " " + event.getTaskDate().getMonthValue()
                + "/" + event.getTaskDate().getDayOfMonth());

        checked[position] = event.isChecked();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked[position] = !checked[position];
                event.setChecked(checked[position]);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!eventname.getPaint().isStrikeThruText()) {
                    eventname.setPaintFlags(eventname.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    eventname.setPaintFlags(eventname.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

        checkBox.setChecked(checked[position]);
        if (checked[position]){
            eventname.setPaintFlags(eventname.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            eventname.setPaintFlags(eventname.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }

}
