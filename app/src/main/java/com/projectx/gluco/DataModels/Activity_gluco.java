package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 06-Mar-18.
 */

public class Activity_gluco {
    String heading;
    String imagelink;

    public Activity_gluco() {
        super();
    }

    public Activity_gluco(String heading, String imagelink) {
        this.heading = heading;
        this.imagelink = imagelink;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    @Override
    public String toString() {
        return "Activity_gluco{" +
                "Heading='" + heading + '\'' +
                ", Imagelink='" + imagelink + '\'' +
                '}';
    }
}
