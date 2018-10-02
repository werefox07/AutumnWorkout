package ru.zaharova.oxana.autumnworkout.Model;

import java.util.Date;

public class Workout {

    private String title;
    private String description;
    private String imageUrl;
    private int recordRepsCount;
    private Date recordDate;
    private int recordWeight;



    public Workout(String title) {
        this.title = title;
    }

    public Workout(String title, String description, int repsCount, Date date, int weight) {
        this.title = title;
        this.description = description;
        this.recordRepsCount = repsCount;
        this.recordDate = date;
        this.recordWeight = weight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRecordRepsCount() {
        return recordRepsCount;
    }

    public void setRecordRepsCount(int recordRepsCount) {
        this.recordRepsCount = recordRepsCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getRecordWeight() {
        return recordWeight;
    }

    public void setRecordWeight(int recordWeight) {
        this.recordWeight = recordWeight;
    }
}
