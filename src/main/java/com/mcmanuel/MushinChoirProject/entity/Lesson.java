package com.mcmanuel.MushinChoirProject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "grade")
    private Grade grade;


}





