package com.mcmanuel.MushinChoirProject.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {


    private Integer userId;

    private String lastName;
    private String firstName;

    @Column(name = "fullname")
    private String fullName = fullName();

    private int grade;
    private String part;
    private String district;
    private String group;

    private String ImageString;

    @Email
    private String email;

    private String password;
    private boolean enabled;


    public Integer getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        fullName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        fullName();
    }

    public String getFullName() {
        return fullName;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getImageString() {
        return ImageString;
    }

    public void setImageString(String imageString) {
        ImageString = imageString;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String fullName(){
        return getLastName()+" "+ getFirstName();
    }

}
