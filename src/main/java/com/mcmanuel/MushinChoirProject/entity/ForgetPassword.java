package com.mcmanuel.MushinChoirProject.entity;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ForgetPassword {

    private Integer forgetPasswordId;
    private String email;
    private String Otp;
}
