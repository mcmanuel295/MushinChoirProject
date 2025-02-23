package com.mcmanuel.MushinChoirProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mcmanuel.MushinChoirProject.model.Group;
import com.mcmanuel.MushinChoirProject.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "lastname", nullable = false)
    private String firstName;

    @Column(name = "fullname", nullable = false)
    private String fullName = fullName();

    @OneToOne
    @JsonIgnore
    private Grade grade;


    private String part;
    private String district;

    @Enumerated(EnumType.STRING)
    private Group group;

    private String ImageString;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Email
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isEnabled;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Assignment answer;


    public String fullName() {
        return getLastName() + " " + getFirstName();

    }

}
