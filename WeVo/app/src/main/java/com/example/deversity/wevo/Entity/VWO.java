package com.example.deversity.wevo.Entity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for VWO
 *@author Fu, Yunhao
 */

public class VWO {
    private String email;
    private String password;
    private String location;
    private String description;
    private ArrayList<Event> eventList;

    public VWO(String email, String password, String location, String description, ArrayList<Event> eventList) {
        this.email = email;
        this.password = password;
        this.location = location;
        this.description = description;
        this.eventList = eventList;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public void setEventList(ArrayList<Event> newEventList) {
        this.eventList = newEventList;
    }

}
