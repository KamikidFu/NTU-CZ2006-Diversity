package com.example.deversity.wevo.Entity;

import java.util.ArrayList;

/**
 * Entity class for Volunteer
 * @author Fu, Yunhao
 */

public class Volunteer {
    private String name;
    private String email;
    private String password;
    private String description;
    private ArrayList<String> eventList;

    public Volunteer(String name, String email, String password, String description, ArrayList<String> eventList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.eventList = eventList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public ArrayList<String> getEventList() {
        return this.eventList;
    }

    public void setEventList(ArrayList<String> newEventList) {
        this.eventList = newEventList;
    }
}
