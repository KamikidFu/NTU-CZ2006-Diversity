package com.example.deversity.wevo.Entity;

import java.util.List;

/**
 * Entity class for Job
 * @author Fu, Yunhao
 */

public class Job {
    private String name;
    private String description;
    private Event relatedEvent;
    private List<Volunteer> volunteerList;
    private int amountNeed;
    private int vacancy;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String Description) {
        this.description=Description;
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

    public List<Volunteer> getVolunteerList() {
        return volunteerList;
    }

    public int getAmountNeed() {
        return amountNeed;
    }

    public int getVacancy() {
        return vacancy;
    }
}
