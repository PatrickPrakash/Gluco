package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 21-Feb-18.
 */

public class Gluco_value {
    private String concentration;
    private String date;
    private String time;
    private String period;
    private String notes;

    public Gluco_value() {
        super();
    }

    public Gluco_value(String concentration, String date, String time, String period, String notes) {
        this.concentration = concentration;
        this.date = date;
        this.time = time;
        this.period = period;
        this.notes = notes;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Gluco_value{" +
                "concentration='" + concentration + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", period='" + period + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}


