package com.example.timekeep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TasksView extends Fragment {

    ArrayList<TaskEvent> list = new ArrayList<TaskEvent>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks_view, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewTask();
        list = ((HomeActivity)getActivity()).getListOfEvents();
        DisplayEvents();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void DisplayEvents() {
        ListView view = getView().findViewById(R.id.EventList);
        TaskEventAdapter Adapter = new TaskEventAdapter(getActivity().getApplicationContext(), list);
        view.setAdapter(Adapter);
    }

    public void NewTask(){
        Button NewTask = getView().findViewById(R.id.NewTask);
        NewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).SwitchFragment(new CreateEvent());
            }
        });
    }
}