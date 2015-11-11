package com.example.acer.seatreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Configure the calendar
        //Works only from Api 16

        calendar = (CalendarView) findViewById(R.id.calendar);//Get  the calendar view ID
        calendar.setShowWeekNumber(false);//Set the calendar for not showing the week Number

        //Go back to the SeatReservation activity and change the date button text according to the date
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                java.util.Calendar c = java.util.Calendar.getInstance();
                String date ="";//Empty string for holding the date

                if(year == c.get(java.util.Calendar.YEAR) ) {
                    if (month == c.get(java.util.Calendar.MONTH)){
                        if(dayOfMonth == c.get(java.util.Calendar.DATE))

                            date = dayOfMonth + "/" + month + "/" + year;

                        else
                            date = dayOfMonth + "/" + month + "/" + year;
                    }
                    else
                        date = dayOfMonth + "/" + month + "/" + year;
                }
                else
                    date = dayOfMonth + "/" + month + "/" + year;

                SeatReservations.dateText = date;
                Intent intent = new Intent(Calendar.this, SeatReservations.class);
                startActivity(intent);
            }
        });
    }
}