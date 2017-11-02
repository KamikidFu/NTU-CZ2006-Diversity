package com.example.deversity.wevo.Login;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deversity.wevo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Combined boundary and control class for login
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressBar progressBar;
    private Switch switchType;
    private String typeChoice = "volunteer";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private final static int PERMISSION_FINE_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null ) {
                    startActivity( new Intent( Login.this, com.example.deversity.wevo.ui.VolunteerView.class));
                }
            }
        };


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        switchType = (Switch) findViewById(R.id.switchType);

        switchType.setChecked(true);
        buttonLogin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if( switchType.isChecked()  ) typeChoice = "volunteer";
        else typeChoice = "vwo";
        email = email + typeChoice;
        password = password + typeChoice;

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
                            startActivity(new Intent(getApplicationContext(), com.example.deversity.wevo.ui.VolunteerView.class));
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


}
