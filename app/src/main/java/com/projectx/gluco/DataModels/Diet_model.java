package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 18-Mar-18.
 */

public class Diet_model {
    String diet;
    String heading;

    public Diet_model() {
        super();
    }

    public Diet_model(String diet, String heading) {
        this.diet = diet;
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}

