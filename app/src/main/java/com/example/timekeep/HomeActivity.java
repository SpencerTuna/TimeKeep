package com.example.timekeep;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private LocalDate SelectedDate;
    private ArrayList<TaskEvent> listOfEvents = new ArrayList<TaskEvent>();
    private int itemSelected;

    public LocalDate getSelectedDate() {
        return SelectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        SelectedDate = selectedDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SelectedDate = LocalDate.now();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new CalendarFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_calendar);
            itemSelected = 0;
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        listOfEvents.add(new TaskEvent(LocalDate.now(), LocalTime.now(),"Clean Laundry"));
        listOfEvents.add(new TaskEvent(LocalDate.now(),LocalTime.now(),"fold Laundry"));
        listOfEvents.add(new TaskEvent(LocalDate.now(),LocalTime.now(),"Do Dishes"));


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                itemSelected = 0;
                break;
            case R.id.nav_tasks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TasksView()).commit();
                itemSelected = 1;
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void SwitchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public ArrayList<TaskEvent> getListOfEvents() {
        return listOfEvents;
    }
    public void setListOfEvents(ArrayList<TaskEvent> listOfEvents) {
        this.listOfEvents = listOfEvents;
    }
    public void AddEvent(TaskEvent event){
        this.listOfEvents.add(event);
    }

    public int getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(int itemSelected) {
        this.itemSelected = itemSelected;
    }
}