package com.example.deversity.wevo.dataObj;

import java.util.ArrayList;

/**
 * Created by kidfu on 2017/10/28.
 */

public class Volunteer {
    private String name;
    private String email;
    private String password;
    private ArrayList<Job> appliedJobs;

    public Volunteer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        appliedJobs = new ArrayList<>();
    }

    public void addJob(Job job){
        appliedJobs.add(job);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Job> getAppliedJobs() {
        return appliedJobs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAppliedJobs(ArrayList<Job> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }
}
