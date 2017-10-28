package com.example.johny.boomapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johny.boomapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null ) {
                    startActivity( new Intent( Login.this, Profile.class));
                }
            }
        };

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        setTypeSignika(buttonLogin, true, false);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        setTypeSignika(editTextEmail, false, false);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        setTypeSignika(editTextPassword, false, false);

        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        setTypeSignika(textViewSignUp, false, true);


        buttonLogin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    private void userLogin() {
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

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            //user is successfully registered and logged in
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        } else {
                            Toast.makeText(Login.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            userLogin();
        }
        if (view == textViewSignUp ) {
            //will open login activity
            finish();
            startActivity( new Intent(this, SignUp.class ));
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
