package com.example.deversity.wevo.Entity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Entity class for event
 * @author Fu, Yunhao
 */
public class Event {
    private String date;
    private List<Job> jobList;
    private VWO relatedVWO;
    private String description;
    private FirebaseDatabase firebaseDatabase;

    public String getDate() {
        return date;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public VWO getRelatedVWO() {
        return relatedVWO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
