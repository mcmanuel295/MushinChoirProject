package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.MushinChoirProject.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<Grade> getAllGrades();

    Grade getGradeById(String gradeId);

    Grade getGradeByLevel(int gradeLevel);

    Grade addGrade(Grade grade);

}
