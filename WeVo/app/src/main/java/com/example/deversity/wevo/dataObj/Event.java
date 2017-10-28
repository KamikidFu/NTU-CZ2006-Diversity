package com.example.deversity.wevo.dataObj;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kidfu on 2017/10/28.
 */

public class Event {
    private Date date;
    private ArrayList<Job> jobs;
    private VWO relatedVWO;
    private String description;

    public Event(Date date, VWO relatedVWO, String description) {
        this.date = date;
        this.relatedVWO = relatedVWO;
        this.description = description;
        jobs = new ArrayList<>();
    }

    public void addJob(Job job){
        jobs.add(job);
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public VWO getRelatedVWO() {
        return relatedVWO;
    }

    public String getDescription() {
        return description;
    }
}
