package com.example.deversity.wevo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deversity.wevo.Entity.Event;
import com.example.deversity.wevo.Entity.Job;
import com.example.deversity.wevo.R;
import com.example.deversity.wevo.mgr.VWOClientMgr;

import java.util.ArrayList;

/**
 * EventCreator is a boundary class for creating event and showing event
 * @author Teo
 */

public class EventCreator extends AppCompatActivity implements View.OnClickListener{

    private Button submitButton;
    private Button discardButton;
    private Button increaseButton;
    private Button decreaseButton;
    private EditText EditTextEventName;
    private EditText EditTextEventDescription;
    private EditText EditTextEventLocation;
    private EditText EditTextEventDate;
    private EditText EditTextEventTime;
    VWOClientMgr VWOMgr = new VWOClientMgr();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creator);
        submitButton = (Button) findViewById(R.id.submitButton);
        discardButton = (Button) findViewById(R.id.discardButton);
        increaseButton = (Button) findViewById(R.id.plusButton);
        decreaseButton = (Button) findViewById(R.id.minusButton);
        EditTextEventDate = findViewById(R.id.editTextEventDate);
        EditTextEventDescription = findViewById(R.id.editTextEventDescription);
        EditTextEventLocation = findViewById(R.id.editTextEventLocation);
        EditTextEventName = findViewById(R.id.editTextEventName);
        EditTextEventTime = findViewById(R.id.editTextEventTime);
    }

    @Override
    public void onClick(View view) {
        if(view == submitButton){
            ArrayList<Job> newJobList = new ArrayList<>();
            String EventName = EditTextEventName.getText().toString();
            String EventDate = EditTextEventDate.getText().toString();
            EventDate = EventDate +  ";" + EditTextEventTime.getText().toString();
            String EventDescription = EditTextEventDescription.getText().toString();
            Event newEvent = new Event(EventDate, EventDescription, newJobList);
            VWOMgr.createEvent(EventName, newEvent);

            //Toast.makeText(this, "You click on submit button", Toast.LENGTH_LONG).show();
        }else if(view == discardButton){
            //Toast.makeText(this, "You click on discard button", Toast.LENGTH_LONG).show();
        }else if(view == increaseButton){
            //Toast.makeText(this, "You click on increase button", Toast.LENGTH_LONG).show();
        }else if(view == decreaseButton){
            //Toast.makeText(this, "You click on decrease button", Toast.LENGTH_LONG).show();
        }
    }
}
