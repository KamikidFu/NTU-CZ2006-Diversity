package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.Entity.Volunteer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**
 * Control class for Volunteer Client
 * @author Fu, Yunhao
 */

public class VolunteerClientMgr {
    private FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private Volunteer user;
    private ShowVWOMgr VWOMgr;

    public void SetDescriptionUSER(String newDescription){
        mRootRef.child("Volunteer").child("id").child(USER.getUid()).child("description").setValue(newDescription);
    }
    public void SetNameUSER(String newName) {
        mRootRef.child("Volunteer").child("id").child(USER.getUid()).child("name").setValue(newName);
    }

    public void applyJob(){

    }
    public void showJob(ListView listView){

    }
}
