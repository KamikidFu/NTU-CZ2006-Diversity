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


    public Event(String date, String description, String location, ArrayList<Job> JobList) {
	EventModification
        this.date=date;
        this.description=description;
        this.location=location;
        this.JobList=JobList;
        this.location=location;
    }

    public String getLocation() {
        return location;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public ArrayList<Job> getJobList(){
        return JobList;
    }

    public void setJobList(ArrayList<Job> newJobList) {
        this.JobList = newJobList;
    }

}
