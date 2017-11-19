package com.example.deversity.wevo.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for Job
 * For future version ONLY
 * @author Fu, Yunhao
 */

public class Job {
    private String name;
    private String description;
    private ArrayList<Volunteer> volunteerList;
    private int amountNeed;
    private int vacancy;

    /**
     * Job constructor
     * @param name
     * @param description
     * @param volunteerList
     * @param amountNeed
     * @param vacancy
     */
    public Job(String name, String description, ArrayList<Volunteer> volunteerList, int amountNeed, int vacancy){
        this.name = name;
        this.description = description;
        this.volunteerList = volunteerList;
        this.amountNeed = amountNeed;
        this.vacancy = vacancy;
    }

    /**
     * Set job name
     * @param name Job name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set job description
     * @param Description Job description
     */
    public void setDescription(String Description) {
        this.description=Description;
    }

    /**
     * Get job name
     * @return Job name
     */
    public String getName() {
        return name;
    }

    /**
     * Get job description
     * @return Job description
     */
    public String getDescription() {
        return description;
    }
}
