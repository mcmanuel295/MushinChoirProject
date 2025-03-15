package com.mcmanuel.MushinChoirProject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginRequest {

    @Email
    @NotEmpty
    private String email;

    private String password;


    public String getEmail() {
        return this.email;
    }


    public String getPassword() {
        return this.password;
    }


    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
