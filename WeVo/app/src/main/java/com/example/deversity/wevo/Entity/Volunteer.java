package com.example.deversity.wevo.Entity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Entity class for Volunteer
 * @author Fu, Yunhao
 */

public class Volunteer {
    private String name;
    private String email;
    private String password;
    private String description;
    private List<Job> jobList;
    private FirebaseDatabase firebaseDatabase;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
