package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deversity.wevo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class volunteer_vwo_event extends AppCompatActivity {

    private String EVENTID;
    private TextView EVENTName;
    private TextView EVENTDes;
    private TextView EVENTLoc;
    private TextView EVENTDate;
    private Button JoinEvent;
    DatabaseReference mRootView = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_vwo_event);
        /*
        EVENTName = findViewById(R.id.volVWOEventName);
        EVENTDes=findViewById(R.id.volVWOEventDesLabel);
        EVENTLoc=findViewById(R.id.volVWOEventLocationLabel);
        EVENTDate=findViewById(R.id.volVWODescription);
        JoinEvent=findViewById(R.id.btnvolVWOEventJoin);

        Intent intent =getIntent();
        final String eventName = intent.getStringExtra("EventName");
        final String VWOID = intent.getStringExtra("VWOID");
        Toast.makeText(getApplicationContext(),"Loading "+eventName,Toast.LENGTH_SHORT).show();
        EVENTName.setText(eventName);
        if(mRootView.child("VWO").child("id").child(VWOID).child(eventName).getKey()!=null) {
            mRootView.child("VWO").child("id").child(VWOID).child(eventName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("location") != null) {
                        EVENTLoc.setText(dataSnapshot.child("location").getKey());
                    }
                    if (dataSnapshot.child("date") != null) {
                        EVENTDate.setText(dataSnapshot.child("date").getKey());
                    }
                    if (dataSnapshot.child("description") != null) {
                        EVENTDes.setText(dataSnapshot.child("description").getKey());
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }else{
            Toast.makeText(getApplicationContext(),"Failed to load event",Toast.LENGTH_SHORT).show();

        }

        JoinEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Thank you",Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}
