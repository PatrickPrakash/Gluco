package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 22-Feb-18.
 */

public class Pressure_gluco {
    private String pressure_max;
    private String pressure_min;
    private String pressure_date;
    private String pressure_notes;
    private String pressure_time;
    private String pressure_period;
    private String key;

    public Pressure_gluco() {

    }

    public Pressure_gluco(String pressure_max, String pressure_min, String pressure_date, String pressure_notes, String pressure_time, String pressure_period) {
        this.pressure_max = pressure_max;
        this.pressure_min = pressure_min;
        this.pressure_date = pressure_date;
        this.pressure_notes = pressure_notes;
        this.pressure_time = pressure_time;
        this.pressure_period = pressure_period;
    }

    public String getPressure_max() {
        return pressure_max;
    }

    public void setPressure_max(String pressure_max) {
        this.pressure_max = pressure_max;
    }

    public String getPressure_min() {
        return pressure_min;
    }

    public void setPressure_min(String pressure_min) {
        this.pressure_min = pressure_min;
    }

    public String getPressure_date() {
        return pressure_date;
    }

    public void setPressure_date(String pressure_date) {
        this.pressure_date = pressure_date;
    }

    public String getPressure_notes() {
        return pressure_notes;
    }

    public void setPressure_notes(String pressure_notes) {
        this.pressure_notes = pressure_notes;
    }

    public String getPressure_time() {
        return pressure_time;
    }

    public void setPressure_time(String pressure_time) {
        this.pressure_time = pressure_time;
    }

    public String getPressure_period() {
        return pressure_period;
    }

    public void setPressure_period(String pressure_period) {
        this.pressure_period = pressure_period;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Pressure_gluco{" +
                "pressure_max='" + pressure_max + '\'' +
                ", pressure_min='" + pressure_min + '\'' +
                ", pressure_date='" + pressure_date + '\'' +
                ", pressure_notes='" + pressure_notes + '\'' +
                ", pressure_time='" + pressure_time + '\'' +
                ", pressure_period='" + pressure_period + '\'' +
                '}';
    }
}
