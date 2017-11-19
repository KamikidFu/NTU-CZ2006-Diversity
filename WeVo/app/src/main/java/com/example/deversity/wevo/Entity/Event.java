package com.example.deversity.wevo.Entity;

import java.util.ArrayList;

/**
 * Entity class for event
 * @author Fu, Yunhao
 */
public class Event {
    private String date;
    private ArrayList<Job> JobList;
    private String description;
    private String location;

    /**
     * Event constructor
     * @param date The date of event
     * @param description The description of event
     * @param location The location of event
     * @param JobList For future version ONLY Job list for that event
     */
    public Event(String date, String description, String location, ArrayList<Job> JobList) {
        this.date=date;
        this.description=description;
        this.location=location;
        this.JobList=JobList;
        this.location=location;
    }

    /**
     * Get description of event
     * @return Description of event
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Set description of event
     * @param newDescription Description of event
     */
    public void setDescription(String newDescription){
        this.description = newDescription;
    }


}
