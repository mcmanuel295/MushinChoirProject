package com.mcmanuel.MushinChoirProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;

@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "lastname", nullable = false)
    private String firstName;

    @Column(name = "fullname", nullable = false)
    private String fullName = fullName();

    private Grade grade;
    private String part;
    private String district;
    private String group;

    private String ImageString;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isEnabled;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Assignment answer;

    public User() {
    }

    public String fullName() {
        return getLastName() + " " + getFirstName();

    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Grade getGrade() {

        return this.grade;
    }

    public String getPart() {
        return this.part;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getGroup() {
        return this.group;
    }

    public String getImageString() {
        return this.ImageString;
    }

    public Role getRole() {
        return this.role;
    }

    public @Email String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setImageString(String ImageString) {
        this.ImageString = ImageString;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
