package com.example.deversity.wevo.mgr;

import com.example.deversity.wevo.Entity.Event;
import com.example.deversity.wevo.Entity.Job;
import com.example.deversity.wevo.Entity.ServerInterface;
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
    private ServerInterface DBInterface = ServerInterface.getINSTANCE();
    private FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    public void createEvent(String EventName, Event newEvent){
        DBInterface.VWOCreateEvents(USER.getUid(), EventName, newEvent);
    }

    public void editDescription(String Description){
        DBInterface.setVWODetails(USER.getUid(), Description, "description");
    }

    public void removeEvent(String EventName){
        DBInterface.VWODeleteEvents(USER.getUid(), EventName);
    }

    public void addJobToEvent(Job newJob, String EventName){
        Map<String, Object> JobData = new HashMap<>();
        JobData.put(newJob.getName(), newJob);
        mRootRef.child("VWO").child("id").child(USER.getUid()).child("Events").child(EventName).child("Jobs").updateChildren(JobData);
    }

    public void logOut(){
        DBInterface.logOut();
    }

}