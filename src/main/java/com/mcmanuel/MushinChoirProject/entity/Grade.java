package com.mcmanuel.MushinChoirProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Grade {

    @Id
    @GeneratedValue
    private String gradeId;

    @OneToMany(mappedBy = "grade")
    private List<Lesson> lessonList;

    @OneToMany(mappedBy = "grade")
    private List<Assignment> assignmentList;

    @OneToOne
    private User user;



}

    /*
    PRELIM("PRELIM"),
    GRADE_1("GRADE 1"),
    GRADE_2("GRADE 2"),
    GRADE_3("GRADE 3"),
    GRADE_4("GRADE 4"),
    GRADE_5("GRADE 5");
*/
