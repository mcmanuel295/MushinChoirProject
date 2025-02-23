package com.mcmanuel.MushinChoirProject.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class ForgetPassword {

    private Integer forgetPasswordId;
    private String email;
    private String Otp;
}
