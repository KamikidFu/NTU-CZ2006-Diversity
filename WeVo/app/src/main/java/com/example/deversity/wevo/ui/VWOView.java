package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.deversity.wevo.R;

/**
 * VWOView is boundary class for VWO Client
 * @author Teo;
 */
public class VWOView extends AppCompatActivity implements View.OnClickListener{

    private Button addEventButton;
    private String[] placeholderEvents = {"Event1","Event2","Event3"};
    private ListView eventListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Its okay", "onCreate() is working");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vwoview);
        addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener((View.OnClickListener) this);
        eventListView=(ListView)findViewById(R.id.eventList);

        ListAdapter vwoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,placeholderEvents);
        eventListView.setAdapter(vwoAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view == addEventButton){
            startActivity(new Intent(this, EventCreator.class));
            Log.d("Its okay", "onClickView() is working");
        }
    }
}