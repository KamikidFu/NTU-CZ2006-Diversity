package com.example.deversity.wevo.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.deversity.wevo.Login.Login;
import com.example.deversity.wevo.R;
import com.example.deversity.wevo.Entity.Volunteer;
import com.example.deversity.wevo.mgr.VolunteerClientMgr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.deversity.wevo.R;

/**
 * UserTab is a boundary class for showing volunteer information
 * @author John;
 */
public class userTab extends Fragment {
    FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUserRef = mRootRef.child("Vol").child(USER.getUid());

    EditText mEditTextName;
    EditText mEditTextBrief;
    Button mButtonNameModify;
    Button mButtonBriefModify;
    Volunteer User;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_usertab, container, false);
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User = dataSnapshot.child("name").getValue(Volunteer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mEditTextName.setText(User.getName());
        mEditTextBrief.setText(User.getDescription());
        return rootView;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
    public void onStart(){
        super.onStart();

        final VolunteerClientMgr userTabMgr= new VolunteerClientMgr();
        mButtonNameModify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userTabMgr.SetNameUSER(mEditTextName.getText().toString());
            }
        });

        mButtonBriefModify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userTabMgr.SetDescriptionUSER(mEditTextBrief.getText().toString());
            }
        });
    }
}
