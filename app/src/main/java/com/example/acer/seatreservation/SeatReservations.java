package com.example.acer.seatreservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SeatReservations extends AppCompatActivity {

    EditText from,to;//For getting places
    EditText time,number;//For Getting time and number of travellers

    Button reserve,cancel;//For saving or cancel reservation

    String list;


    Button date;//Button for getting the date from calendar
    static String dateId = "";//For holding the date
    static String dateText = " ";//For holding the text of the day



    //ArrayList for Holding all Reservations
    ArrayList<String[]> reservations = new ArrayList<>(20);
    private static String DEBUGTAG = "tag1"; //String for debugtag
    public static final String PREF_Event="MyPrefsFile";  //String for sharesPreferences to store an event


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_reservations);
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

        //find the day displayed id
        java.util.Calendar c = java.util.Calendar.getInstance();


        dateId = (c.get(java.util.Calendar.DAY_OF_MONTH))
                + "/" + c.get(java.util.Calendar.MONTH)
                + "/" + c.get(java.util.Calendar.YEAR);

        dateId = dateText;

        date = (Button) findViewById(R.id.date);
        date.setText(dateText);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeatReservations.this, Calendar.class);
                startActivity(intent);
            }
        });


        from=(EditText) findViewById(R.id.fromPlace);
        to=(EditText)findViewById(R.id.toPlace);
        time=(EditText)findViewById(R.id.time);
        number=(EditText)findViewById(R.id.numberPassegers);


        reserve = (Button) findViewById(R.id.saveReservation);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if all the text field filled out before saving
                if(from.getText().toString() == null || from.getText().toString().equals("") ||
                        to.getText().toString() == null || to.getText().toString().equals("") ||
                        time.getText().toString()== null || time.getText().equals("") ||
                        number.getText().toString()==null || number.getText().equals("")) {
                    String toastMsg = String.format("You have to fill all the informations");
                    Toast.makeText(SeatReservations.this, toastMsg, Toast.LENGTH_LONG).show();
                }
                else {

                    createReservation();//For Creating Reservation
                    Intent intent = new Intent(SeatReservations.this,ReservationsDisplayer.class);
                    intent.putExtra("Reserve",list);
                    startActivity(intent);

                }

            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatReservations.this, SeatReservations.class);
                startActivity(intent);
            }
        });

        }

    private void createReservation(){
        //An Array for creating a reservation object

        String [] reservation = {from.getText().toString(),to.getText().toString(),time.getText().toString(),number.getText().toString()};
        reservations.add(reservation);//Add reservation to the arrayList

        list ="";//Get reservation one by one

        for(int i=0;i<reservations.size();i++){
            String[] reserve =reservations.get(i);
            for(int j=0;j<reserve.length;j++)
                list +=reserve[j]+"\n";
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(DEBUGTAG, "on pause called when the system is about to start resuming a previous activity");

        //Get Shared preferences
        SharedPreferences prefs = getSharedPreferences(PREF_Event,MODE_PRIVATE);

        //Get preference editor

        SharedPreferences.Editor edit = prefs.edit();

        //Store Value

        edit.putString("Reservation",list);

        //Apply changes

        edit.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(DEBUGTAG, "on Resume called when the activity will start interacting with the user");

        //Get shared preferences

        SharedPreferences prefs = getSharedPreferences(PREF_Event,MODE_PRIVATE);

        //Retrieve values

        list=prefs.getString("Reservation","");

        //view2.setText(list);

    }

    public  void onRadioButtonClicked(View view){
        //Is the button  Checked

       boolean checked = ((RadioButton) view).isChecked();

        //Check which radion button was clicked

        switch (view.getId()){
            case R.id.onlyTrain:

                if(checked)//Only for the train
                    break;

            case R.id.TrainogBus:
                if (checked)
                    break;
        }
    }
    }



