package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deversity.wevo.Login.Login;
import com.example.deversity.wevo.R;
import com.example.deversity.wevo.mgr.VWOClientMgr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * VWOView is boundary class for VWO Client
 * @author Teo;
 */
public class VWOView extends AppCompatActivity implements View.OnClickListener{

    private Button addEventButton;
    private String[] placeholderEvents = {"Event1","Event2","Event3"};
    private ArrayList<String> EventsArrayList;
    private ListView eventListView;
    private TextView TextViewVWOName;
    private EditText EditTextDescription;
    private Button ButtonEditDescription;
    private Button ButtonLogOut;
    FirebaseUser USER;
    DatabaseReference mRootView;
    //private boolean VWOLog = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Its okay", "onCreate() is working");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vwoview);
        TextViewVWOName = findViewById(R.id.vwoNameLabel);
        EditTextDescription = findViewById(R.id.descripEdit);
        ButtonEditDescription = findViewById(R.id.ButtonEditDescription);
        ButtonLogOut = findViewById(R.id.buttonLogOut);
        addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener((View.OnClickListener) this);
        eventListView=(ListView)findViewById(R.id.eventList);
        EventsArrayList = new ArrayList<>();

        Intent intent = getIntent();
        if(!intent.getStringExtra("MODE").isEmpty()) {
            String mode = intent.getStringExtra("MODE");
            if(mode.matches("VOL")) {
                //VWOLog=false;
                //TO-DO Volunteer visit
                String name = intent.getStringExtra("VWOName");
                Toast.makeText(getApplicationContext(),"Welcome to "+name,Toast.LENGTH_SHORT).show();
                //TO-DO Search the database with the name and put the data into this view

                TextViewVWOName.setText(name);
                ListAdapter vwoAdapter = new ArrayAdapter<String>(VWOView.this, android.R.layout.simple_list_item_1, placeholderEvents);
                eventListView.setAdapter(vwoAdapter);

                //TO-DO Set visibility for each button that only VWO can use
                ButtonLogOut.setVisibility(View.INVISIBLE);
                ButtonEditDescription.setVisibility(View.INVISIBLE);
                addEventButton.setVisibility(View.INVISIBLE);
                EditTextDescription.setEnabled(false);
            }else {
                //VWOLog=true;
                USER = FirebaseAuth.getInstance().getCurrentUser();
                mRootView = FirebaseDatabase.getInstance().getReference();
                //TO-DO VWO user log in
                mRootView.child("VWO").child("id").child(USER.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextViewVWOName.setText(dataSnapshot.child("name").getValue(String.class));
                        EditTextDescription.setText(dataSnapshot.child("description").getValue(String.class));
                        for (DataSnapshot EventSnapshot : dataSnapshot.child("Events").getChildren()) {
                            if (EventSnapshot.getValue() != null) {
                                EventsArrayList.add(EventSnapshot.getKey());
                            }
                        }
                        ListAdapter vwoAdapter = new ArrayAdapter<String>(VWOView.this, android.R.layout.simple_list_item_1, EventsArrayList);
                        eventListView.setAdapter(vwoAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }else{
             Toast.makeText(getApplicationContext(),"Unexpected Error!",Toast.LENGTH_SHORT).show();
             startActivity(new Intent(this, Login.class));
             finish();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        final VWOClientMgr VWOmgr = new VWOClientMgr();

        //Edit button click listener
        ButtonEditDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VWOmgr.editDescription(EditTextDescription.getText().toString());
            }
        });
        //Log out click listener
        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Log you out", Toast.LENGTH_SHORT ).show();
                VWOmgr.logOut();
                startActivity(new Intent(VWOView.this, Login.class));
                try {
                    VWOView.this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        //Event list listener
    }

    @Override
    public void onClick(View view) {
        if(view == addEventButton){
            startActivity(new Intent(this, EventCreator.class));
            Log.d("Its okay", "onClickView() is working");
        }
    }
}