package com.example.deversity.wevo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.deversity.wevo.R;

public class VWOView extends AppCompatActivity implements View.OnClickListener{

    private Button addEventButton;
    private String[] placeholderEvents = {"Event1","Event2","Event3"};
    private ListView eventListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_vwoview);
        addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener((View.OnClickListener) this);
        eventListView=(ListView)findViewById(R.id.eventList);

        ListAdapter vwoAdpter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,placeholderEvents);
        eventListView.setAdapter(vwoAdpter);
    }

    @Override
    public void onClick(View view) {
        if(view == addEventButton){
            startActivity(new Intent(this, EventCreator.class));
        }
    }
}
