package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.Entity.Event;
import com.example.deversity.wevo.Entity.ServerInterface;
import com.example.deversity.wevo.Entity.Volunteer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Control class for Volunteer Client
 * @author Fu, Yunhao
 */

public class VolunteerClientMgr {
    private FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private static List<MarkerOptions> VWOMarkerList = new ArrayList<>();

    private Volunteer user;
    private ShowVWOMgr VWOMgr;
    private String pushId;
    private ServerInterface DBInterface = ServerInterface.getINSTANCE();



    private final ShowVWOMgr ShowVWOMgr = new ShowVWOMgr();

    public void addVWOMarker(double Latitude, double Longitude,String title){

        if(VWOMarkerList!=null){
            VWOMarkerList.add(new MarkerOptions().position(new LatLng(Latitude,Longitude)).title(title));
        }
    }

    public void createEvent(String EventName){
        Map<String, Object> EventData = new HashMap<>();
        EventData.put(EventName, EventName);
        pushId = mRootRef.child("Vol").child("id").child(USER.getUid()).child("Events").push().getKey();
        mRootRef.child("Vol").child("id").child(USER.getUid()).child("Events").child(pushId).setValue(EventName);
    }

    public void deleteEvent(String EventName){
        mRootRef.child("Vol").child("id").child(USER.getUid()).child("Events").child(pushId).setValue(null);
    }

    public static List<MarkerOptions> getVWOMarkerList() {
        return VWOMarkerList;
    }

    public void SetDescriptionUSER(String newDescription){
        DBInterface.setVolunteerDetails(USER.getUid(), newDescription, "description");
    }

    public void SetNameUSER(String newName) {
        DBInterface.setVolunteerDetails(USER.getUid(), newName, "name");
    }

    public ArrayList<String> getVolunteerDetails(){
        return DBInterface.getVWODetails(USER.getUid());
    }

    public ArrayList<String> getVolEvents(){
        return DBInterface.getVolunteerEvent(USER.getUid());
    }

    public void LogOut(){
        DBInterface.logOut();
    }

    public ShowVWOMgr getShowVWOMgr() {
        return ShowVWOMgr;
    }
}