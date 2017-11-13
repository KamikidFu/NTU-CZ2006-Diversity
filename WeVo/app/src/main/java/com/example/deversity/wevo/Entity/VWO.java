package com.example.deversity.wevo.Entity;

import java.util.ArrayList;

/**
 * Entity class for VWO
 *@author Fu, Yunhao
 */

public class VWO {
    private  String name;
    private String email;
    private String password;
    private String location;
    private String description;
    private ArrayList<Event> eventList;

    public VWO(String name, String email, String password, String location, String description, ArrayList<Event> eventList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.description = description;
        this.eventList = eventList;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
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
