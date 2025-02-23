package com.mcmanuel.MushinChoirProject.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "grade")
    private Grade grade;

}
