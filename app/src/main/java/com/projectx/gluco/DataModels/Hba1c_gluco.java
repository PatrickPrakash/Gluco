package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 22-Feb-18.
 */

public class Hba1c_gluco {
    private String hba1c_con;
    private String hba1c_date;
    private String hba1c_time;
    private String hba1c_notes;
    private String hba1c_period;
    private String key;

    public Hba1c_gluco() {

    }

    public Hba1c_gluco(String hba1c_con, String hba1c_date, String hba1c_time, String hba1c_notes, String hba1c_period) {
        this.hba1c_con = hba1c_con;
        this.hba1c_date = hba1c_date;
        this.hba1c_time = hba1c_time;
        this.hba1c_notes = hba1c_notes;
        this.hba1c_period = hba1c_period;
    }

    public String getHba1c_con() {
        return hba1c_con;
    }

    public void setHba1c_con(String hba1c_con) {
        this.hba1c_con = hba1c_con;
    }

    public String getHba1c_date() {
        return hba1c_date;
    }

    public void setHba1c_date(String hba1c_date) {
        this.hba1c_date = hba1c_date;
    }

    public String getHba1c_time() {
        return hba1c_time;
    }

    public void setHba1c_time(String hba1c_time) {
        this.hba1c_time = hba1c_time;
    }

    public String getHba1c_notes() {
        return hba1c_notes;
    }

    public void setHba1c_notes(String hba1c_notes) {
        this.hba1c_notes = hba1c_notes;
    }

    public String getHba1c_period() {
        return hba1c_period;
    }

    public void setHba1c_period(String hba1c_period) {
        this.hba1c_period = hba1c_period;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Hba1c_gluco{" +
                "hba1c_con='" + hba1c_con + '\'' +
                ", hba1c_date='" + hba1c_date + '\'' +
                ", hba1c_time='" + hba1c_time + '\'' +
                ", hba1c_notes='" + hba1c_notes + '\'' +
                ", hba1c_period='" + hba1c_period + '\'' +
                '}';
    }
}


