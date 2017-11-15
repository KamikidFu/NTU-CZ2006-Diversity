package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deversity.wevo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_volunteer_vwo_view extends AppCompatActivity {

    private ListView eventListView;
    private ArrayList<String> EventsArrayList;
    private String VWOID;
    private TextView VWOName;
    private String[] tempEvent = new String[1];
    DatabaseReference mRootView = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_vwo_view);
        eventListView = findViewById(R.id.volVWOEventList);
        EventsArrayList = new ArrayList<>();
        VWOName=findViewById(R.id.volVWONameLabel);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("VWOName");
        Toast.makeText(getApplicationContext(),"Welcome to "+name,Toast.LENGTH_SHORT).show();
        VWOName.setText(name);
        mRootView.child("VWO").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot VWOSnapshot: dataSnapshot.getChildren()){
                    if (VWOSnapshot.child("name").getValue(String.class).equals(name)){
                        VWOID = VWOSnapshot.getKey();
                    }
                }
                for (DataSnapshot EventSnapshot : dataSnapshot.child(VWOID).child("Events").getChildren()){
                    if (EventSnapshot.getValue() != null){
                        tempEvent[0] = EventSnapshot.getKey();
                        EventsArrayList.add( tempEvent[0] );
                    }
                }
                ListAdapter vwoAdapter = new ArrayAdapter<String>(activity_volunteer_vwo_view.this, android.R.layout.simple_list_item_1, EventsArrayList);
                eventListView.setAdapter(vwoAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String eventName = adapterView.getItemAtPosition(i).toString();
                if(eventName!=null){
                    Intent intentToEvent = new Intent(getApplicationContext(),volunteer_vwo_event.class);
                    intentToEvent.putExtra("EventName",eventName);
                    intentToEvent.putExtra("VWOID",VWOID);
                    startActivity(intentToEvent);
                }
            }
        });
    }
}