package com.projectx.gluco.DataModels;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Patrick Prakash on 17-Mar-18.
 */

public class Activitymodel {
    private static DatabaseReference dataref;

    public Activitymodel() {
        super();
    }

    public Activitymodel(DatabaseReference dataref) {
        Activitymodel.dataref = dataref;
    }

    public static DatabaseReference getDataref() {
        return dataref;
    }

    public static void setDataref(DatabaseReference dataref) {
        Activitymodel.dataref = dataref;
    }
}
