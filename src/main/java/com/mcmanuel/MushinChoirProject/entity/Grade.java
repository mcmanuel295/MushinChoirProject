package com.mcmanuel.MushinChoirProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.List;


@Entity
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Transactional(rollbackOn = Exception.class)
public class Grade {

    @Id
    private String gradeId;

    private static int level;

    private int gradeLevel;


    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL)
    private List<Lesson> lessonList;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Assignment> assignmentList;

    @OneToMany(mappedBy = "grade",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> user;

    public Grade(String gradeId) {
        this.gradeId = gradeId;
        System.out.println("the level is "+level);
        ++level;
        gradeLevel=level;
    }

    public Grade() {
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }


    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

}

