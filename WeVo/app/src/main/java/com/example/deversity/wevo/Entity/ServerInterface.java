package com.example.deversity.wevo.Entity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**Entity class for Server Interface to connect Control Class with database
 * Created by Gehan on 11/15/2017.
 */

public final class ServerInterface {
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mUserRef = mRootRef.child("Vol").child("id");
    private DatabaseReference mVWORef = mRootRef.child("VWO").child("id");
    private boolean isVWO;
    private static ServerInterface INSTANCE = new ServerInterface();

    private ServerInterface(){}

    public static ServerInterface getINSTANCE() {
        return INSTANCE;
    }

    public boolean checkIsVWO(final String UserID){
        this.isVWO = false;
        mVWORef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot VWOIDSnapshot: dataSnapshot.getChildren()){
                    if (UserID.equals(VWOIDSnapshot.getKey()))
                        isVWO = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return isVWO;
    }

    public ArrayList<String> getVolunteerDetails(String UserID){
        final ArrayList<String> volunteerData = new ArrayList<>();
        mUserRef.child(UserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                volunteerData.add(dataSnapshot.child("name").getValue(String.class));
                volunteerData.add(dataSnapshot.child("description").getValue(String.class));
                volunteerData.add(dataSnapshot.child("email").getValue(String.class));
                volunteerData.add(dataSnapshot.child("password").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return volunteerData;
    }

    public ArrayList<String> getVWODetails(String UserID){
        final ArrayList<String> VWOData = new ArrayList<>();
        mVWORef.child(UserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                VWOData.add(dataSnapshot.child("name").getValue(String.class));
                VWOData.add(dataSnapshot.child("description").getValue(String.class));
                VWOData.add(dataSnapshot.child("location").getValue(String.class));
                VWOData.add(dataSnapshot.child("email").getValue(String.class));
                VWOData.add(dataSnapshot.child("password").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return VWOData;
    }

    public ArrayList<String> getVolunteerEvent(String UserID){
        final ArrayList<String> EventsApplied = new ArrayList<>();
        mUserRef.child(UserID).child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot EventSnapshot: dataSnapshot.getChildren()){
                    EventsApplied.add(EventSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return EventsApplied;
    }

    public ArrayList<String> getVWOEvent(String UserID){
        final ArrayList<String> EventsMade = new ArrayList<>();
        mVWORef.child(UserID).child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot EventSnapshot: dataSnapshot.getChildren()){
                    EventsMade.add(EventSnapshot.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return EventsMade;
    }

    public ArrayList<String> getVWONames(String SortMethod){
        final ArrayList<String> VWOLists = new ArrayList<>();
        mVWORef.orderByChild(SortMethod).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot VWOIDSnapshot: dataSnapshot.getChildren()){
                    VWOLists.add(VWOIDSnapshot.child("name").getValue(String.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  VWOLists;
    }

    public void setVolunteerDetails(String UserID, String DataChanged, String DataKey){
        mUserRef.child(UserID).child(DataKey).setValue(DataChanged);
    }

    public void setVWODetails(String UserID, String DataChanged, String DataKey){
        mVWORef.child(UserID).child(DataKey).setValue(DataChanged);
    }

    public void VWOCreateEvents(String UserID, String EventName,Event event){
        Map<String, Object> EventData = new HashMap<>();
        EventData.put(EventName, event);
        mVWORef.child(UserID).child("Events").updateChildren(EventData);
    }

    public void VWODeleteEvents(String UserID, String EventName){
        mVWORef.child(UserID).child("Events").child(EventName).removeValue();
    }

    public void VolAttendEvents(String UserID, String EventName, String VWOName){
        String EventVWOName = VWOName + ";" + EventName;
        mUserRef.child(UserID).child("Events").child(EventVWOName).setValue(EventVWOName);
    }

    public void VolCancelEvents(String UserID, final String EventName, String VWOName){
        mUserRef.child(UserID).child("Events").orderByValue().equalTo(EventName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void logOut(){
        FirebaseAuth.getInstance().signOut();
    }

    public void SignUpAddToDb(String UserID, String UserType, Object UserObject){
        Map<String, Object> UserData = new HashMap<>();
        UserData.put(UserID, UserObject);
        mRootRef.child(UserType).child("id").updateChildren(UserData);
    }

}
