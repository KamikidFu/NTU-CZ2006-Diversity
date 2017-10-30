package com.example.deversity.wevo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.deversity.wevo.R;

public class EventCreator extends AppCompatActivity implements View.OnClickListener{

    private Button submitButton;
    private Button discardButton;
    private Button increaseButton;
    private Button decreaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creator);
        submitButton = (Button) findViewById(R.id.submitButton);
        discardButton = (Button) findViewById(R.id.discardButton);
        increaseButton = (Button) findViewById(R.id.plusButton);
        decreaseButton = (Button) findViewById(R.id.minusButton);
    }

    @Override
    public void onClick(View view) {
        if(view == submitButton){
            Toast.makeText(this, "You click on submit button", Toast.LENGTH_LONG).show();
        }else if(view == discardButton){
            Toast.makeText(this, "You click on discard button", Toast.LENGTH_LONG).show();
        }else if(view == increaseButton){
            Toast.makeText(this, "You click on increase button", Toast.LENGTH_LONG).show();
        }else if(view == decreaseButton){
            Toast.makeText(this, "You click on decrease button", Toast.LENGTH_LONG).show();
        }
    }
}
