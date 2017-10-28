package com.example.johny.boomapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johny.boomapp.DataMgt.UserProfile;
import com.example.johny.boomapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private Button buttonLogout;
    private Button buttonSave;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null ) {
            finish();
            startActivity( new Intent(this, Login.class ));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (Button) findViewById( R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        buttonSave = (Button) findViewById( R.id.buttonSave);
        buttonSave.setOnClickListener(this);

        editTextName = (EditText) findViewById( R.id.editTextName );

        editTextEmail = (EditText) findViewById( R.id.editTextEmail);
        //editTextEmail.setHint( user.getEmail() );

        editTextPassword = (EditText) findViewById( R.id.editTextPassword);
        editTextPassword.setText( "FakePassword" );
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        UserProfile userProfile = new UserProfile( name, email );

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userProfile);

        Toast.makeText( this, "INFO SAVED...", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View view) {

        if (view == buttonLogout ) {
            //will open SignUp
            firebaseAuth.signOut();
            finish();
            startActivity( new Intent(this, SignUp.class ));
        }

        if ( view == buttonSave ){
            saveUserInformation();
        }
    }
}
