package com.example.johny.boomapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.johny.boomapp.R;
import com.example.johny.boomapp.utils.Utilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends Activity implements View.OnClickListener {

    private Utilities utils;
    private Button buttonSignUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null ){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));

        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        setTypeSignika(buttonSignUp, true, false);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        setTypeSignika(editTextEmail, false, false);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        setTypeSignika(editTextPassword, false, false);

        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        setTypeSignika(textViewSignIn, false, true);


        buttonSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT ).show();
            //stopping the execution
            return;

        }
        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT ).show();
            //stopping the execution
            return;
        }
        //if validations are ok
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword( email, password )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            //user is successfully registered and logged in
                            if (firebaseAuth.getCurrentUser() != null ){
                                //profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), Profile.class));

                            }
                        } else {
                            Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignUp) {
            registerUser();
        }
        if (view == textViewSignIn ) {
            //will open login activity
            finish();
            startActivity( new Intent(this, Login.class ));
        }
    }



    protected void setTypeSignika (TextView textView, boolean bold, boolean light ) {

        Typeface tf;

        if ( bold ) tf = Typeface.createFromAsset(getAssets(), "fonts/Signika-SemiBold.ttf");
        else if ( light ) tf = Typeface.createFromAsset(getAssets(), "fonts/Signika-Light.ttf");
        else tf = Typeface.createFromAsset(getAssets(), "fonts/Signika-Regular.ttf");

        textView.setTypeface(tf);
    }
}

