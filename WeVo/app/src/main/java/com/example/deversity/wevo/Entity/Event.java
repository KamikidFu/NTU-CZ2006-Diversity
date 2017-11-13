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

    public Event(String date, String description, ArrayList<Job> JobList) {
        this.date=date;
        this.description=description;
        this.JobList=JobList;
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
