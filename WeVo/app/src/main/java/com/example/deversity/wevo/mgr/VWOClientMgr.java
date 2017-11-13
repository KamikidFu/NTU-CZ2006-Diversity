package com.example.deversity.wevo.mgr;

import com.example.deversity.wevo.Entity.Event;
import com.example.deversity.wevo.Entity.VWO;
import com.example.deversity.wevo.Entity.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by kidfu on 2017/10/28.
 */

public class VWOClientMgr {
    private VWO user;
    private ShowVWOMgr VWOMgr;
    private FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    public void showEvent(Event event){

    }
    public void createEvent(String EventName, Event newEvent){
        Map<String, Object> EventData = new HashMap<>();
        EventData.put(EventName, newEvent);
        mRootRef.child("VWO").child("id").child(USER.getUid()).child("Events").updateChildren(EventData);
    }
    public void editDescription(String Description){
        mRootRef.child("VWO").child("id").child(USER.getUid()).child("description").setValue(Description);
    }

}