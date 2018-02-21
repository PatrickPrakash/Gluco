package com.projectx.gluco.DataModels;

/**
 * Created by Patrick Prakash on 19-Feb-18.
 */

public class User_info {
    private String name, age, gender;

    //Default constructor
    public User_info() {
        super();
    }

    //User-Defined Constructor
    public User_info(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    //To string function
    @Override
    public String toString() {
        return "User_info{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
