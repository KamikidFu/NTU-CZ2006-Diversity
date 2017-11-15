package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        EditTextEventDate = findViewById(R.id.editTextEventDate);
        EditTextEventDescription = findViewById(R.id.editTextEventDescription);
        EditTextEventLocation = findViewById(R.id.editTextEventLocation);
        EditTextEventName = findViewById(R.id.editTextEventName);
        EditTextEventTime = findViewById(R.id.editTextEventTime);

        submitButton.setOnClickListener( this );
        discardButton.setOnClickListener( this );

    }

    public void onStart(){
        super.onStart();
    }

    public String createEvent()
    {
        ArrayList<Job> newJobList = new ArrayList<>();
        String EventName = EditTextEventName.getText().toString();
        String EventDate = EditTextEventDate.getText().toString();
        EventDate = EventDate +  ";" + EditTextEventTime.getText().toString();
        String EventDescription = EditTextEventDescription.getText().toString();
        Event newEvent = new Event(EventName, EventDate, EventDescription, newJobList);
        VWOMgr.createEvent(EventName, newEvent);

        return EventName;

    }
    @Override
    public void onClick(View view) {

        if (view == submitButton) {

            String EventName = createEvent();

            Intent intent = new Intent( this, VWOView.class ) ;
            intent.putExtra("MODE","VWO");
            //finish();

            startActivity( intent );
        }
        if (view == discardButton ) {
            //will open login activity
            Intent intent = new Intent( this, VWOView.class ) ;
            intent.putExtra("MODE","VWO");
            //finish();

            startActivity( intent );
        }
    }
}