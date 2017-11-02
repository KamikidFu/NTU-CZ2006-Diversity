package com.example.deversity.wevo.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deversity.wevo.ui.VolunteerView;
import com.example.deversity.wevo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Combined boundary and control class for sign up
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private ProgressBar progressBar;
    private Switch switchType;
    private String typeChoice = "volunteer";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null ){
            //profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), VolunteerView.class));

        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        switchType.setChecked(true);
        buttonSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser() {

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
                                startActivity(new Intent(getApplicationContext(), VolunteerView.class));
                            }
                        } else {
                            FirebaseException e = (FirebaseException)task.getException();
                            Log.e("LoginActivity", "Failed Registration", e);
                            //Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
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
}
