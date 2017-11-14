package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView eventListView;
    private TextView TextViewVWOName;
    private EditText EditTextDescription;
    private Button ButtonEditDescription;
    FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mRootView = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mVWORef = FirebaseDatabase.getInstance().getReference().child("VWO").child("id");
    boolean VWOLogin = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Its okay", "onCreate() is working");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vwoview);
        TextViewVWOName = findViewById(R.id.vwoNameLabel);
        EditTextDescription = findViewById(R.id.descripEdit);
        ButtonEditDescription = findViewById(R.id.ButtonEditDescription);
        addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener((View.OnClickListener) this);
        eventListView=(ListView)findViewById(R.id.eventList);

        Intent intent = getIntent();
        if(!intent.getStringExtra("VWOName").isEmpty()){
            final String vwoName = intent.getStringExtra("VWOName");
            VWOLogin = false;
            mVWORef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot VWOSnapshot : dataSnapshot.getChildren()){
                        if (VWOSnapshot.child("name").getValue(String.class).equals(vwoName)){
                            TextViewVWOName.setText(dataSnapshot.child("name").getValue(String.class));
                            EditTextDescription.setText(dataSnapshot.child("description").getValue(String.class));
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            addEventButton.setVisibility(View.INVISIBLE);
            ButtonEditDescription.setVisibility(View.INVISIBLE);
            EditTextDescription.setEnabled(false);


        }else {
            VWOLogin=true;
            mRootView.child("VWO").child("id").child(USER.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    TextViewVWOName.setText(dataSnapshot.child("name").getValue(String.class));
                    EditTextDescription.setText(dataSnapshot.child("description").getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        ListAdapter vwoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeholderEvents);
        eventListView.setAdapter(vwoAdapter);
    }

    @Override
    public void onStart(){
        super.onStart();
        final VWOClientMgr VWOmgr = new VWOClientMgr();
        ButtonEditDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VWOmgr.editDescription(EditTextDescription.getText().toString());
            }
        });
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String eventName = eventListView.getAdapter().getItem(i).toString();
                    Intent intent = new Intent(getApplicationContext(),EventCreator.class);
                    intent.putExtra("EventName", eventName);
                    if(VWOLogin)
                        intent.putExtra("UserMode","VWO");
                    else
                        intent.putExtra("UserMode","Volunteer");
                    startActivity(intent);
            }
        });
    }



    @Override
    public void onClick(View view) {
        if(view == addEventButton){
            startActivity(new Intent(this, EventCreator.class));
            Log.d("Its okay", "onClickView() is working");
        }
    }
}