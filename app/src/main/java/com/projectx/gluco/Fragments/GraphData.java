package com.projectx.gluco.Fragments;

import java.text.ParseException;

/**
 * Created by Patrick Prakash on 10-Mar-18.
 */

public class GraphData {
    private String dateString;
    private String concentration;
    private DateBuilder dateBuilder;

    /**
     * String dateString should be in yy-MM-dd format.
     *
     * @param dateString
     * @param concentration
     */
    public GraphData(String dateString, String concentration) {
        this.dateString = dateString;
        this.concentration = concentration;
        dateBuilder = new DateBuilder(dateString);
    }

    public String getDateString() {
        return dateString;
    }

    public String getConcentration() {
        return concentration;
    }

    public float getDateX() {
        float dateX = -1;
        try {
            dateX = dateBuilder.toFloat();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateX;
    }

    public float getConcentrationY() {
        return Float.parseFloat(concentration);
    }


}
