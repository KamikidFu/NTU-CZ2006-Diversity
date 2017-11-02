package com.example.deversity.wevo.Entity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Entity class for VWO
 *@author Fu, Yunhao
 */

public class VWO {
    private String email;
    private String password;
    private List<Event> eventList;
    private FirebaseDatabase firebaseDatabase;

    public String getEmail() {
        return email;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
