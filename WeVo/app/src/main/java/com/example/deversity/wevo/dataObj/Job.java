package com.example.deversity.wevo.dataObj;

import java.util.ArrayList;

/**
 * Created by kidfu on 2017/10/28.
 */

public class Job {
    private String name;
    private String description;
    private Event relatedEvent;
    private ArrayList<Volunteer> volunteers;
    private int amountNeed;

    public Job(String name, Event relatedEvent) {
        this.name = name;
        this.relatedEvent = relatedEvent;
        this.description="";
        this.amountNeed=0;
    }

    public Job(String name, String description, Event relatedEvent, int amountNeed) {
        this.name = name;
        this.description = description;
        this.relatedEvent = relatedEvent;
        this.amountNeed = amountNeed;
        volunteers = new ArrayList<>();
    }

    public void addVolunteer(Volunteer volunteer){
        volunteers.add(volunteer);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmountNeed(int amountNeed) {
        this.amountNeed = amountNeed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Event getRelatedEvent() {
        return relatedEvent;
    }

    public ArrayList<Volunteer> getVolunteers() {
        return volunteers;
    }

    public int getAmountNeed() {
        return amountNeed;
    }

    public int getVacancy() {
        return amountNeed - volunteers.size();
    }
}
