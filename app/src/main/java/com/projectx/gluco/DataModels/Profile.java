package com.projectx.gluco.DataModels;

/**
 * Created by Rahul on 08-03-2018.
 */

public class Profile {
    private String age,gender,medcon,name;

      public Profile() {
            super();
                        }
    public Profile(String name, String medcon, String gender, String age) {
        this.age = age;
        this.gender = gender;
        this.medcon = medcon;
        this.name = name;

   }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedcon() {
        return medcon;
    }

    public void setMedcon(String medcon) {
        this.medcon = medcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

   }

    @Override
    public String toString() {
        return "Profile{" +
                "age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", medcon='" + medcon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

