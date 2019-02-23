package com.example.poductlistapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Employee {
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("mail")
    @Expose
    private String mail;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
