package com.mcmanuel.MushinChoirProject.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;

public class Register {
    private String lastName;
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Part part;

    private String district;

    @Enumerated(EnumType.STRING)
    private Group groupOfDistrict;

    @Email
    private String email;
    private String password;



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Group getGroupOfDistrict() {
        return groupOfDistrict;
    }

    public void setGroupOfDistrict(Group groupOfDistrict) {
        this.groupOfDistrict = groupOfDistrict;
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
}
