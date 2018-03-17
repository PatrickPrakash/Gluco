package com.projectx.gluco.DataModels;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by Patrick Prakash on 14-Mar-18.
 */

public class RecycleData {
    private static String key;
    private static DatabaseReference reference;
    private int position;

    public RecycleData() {
        super();
    }

    public RecycleData(String keyvalue, DatabaseReference referencevalue, int position) {
        RecycleData.key = keyvalue;
        RecycleData.reference = referencevalue;
        this.position = position;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        RecycleData.key = key;
    }

    public static DatabaseReference getReference() {
        return reference;
    }

    public static void setReference(DatabaseReference reference) {
        RecycleData.reference = reference;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
