package com.example.deversity.wevo.mgr;

import android.widget.ListView;

import com.example.deversity.wevo.Entity.Volunteer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

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



    public static void addVWOMarker(double Latitude, double Longitude,String title){
        if(VWOMarkerList!=null){
            VWOMarkerList.add(new MarkerOptions().position(new LatLng(Latitude,Longitude)).title(title));
        }
    }

    public static List<MarkerOptions> getVWOMarkerList() {
        return VWOMarkerList;
    }

    public void SetDescriptionUSER(String newDescription){
        mRootRef.child("Vol").child("id").child(USER.getUid()).child("description").setValue(newDescription);
    }
    public void SetNameUSER(String newName) {
        mRootRef.child("Vol").child("id").child(USER.getUid()).child("name").setValue(newName);
    }

    public void LogOut(){
        FirebaseAuth.getInstance().signOut();
    }

    public void applyJob(){

    }
    public void showJob(ListView listView){

    }
}
