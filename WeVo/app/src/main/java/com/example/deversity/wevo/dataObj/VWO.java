package com.example.deversity.wevo.dataObj;

import java.util.ArrayList;

/**
 * Created by kidfu on 2017/10/28.
 */

public class VWO {
    private String name;
    private String description;
    private String email;
    private String password;
    private ArrayList<Event> events;

    public VWO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        description = "";
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
