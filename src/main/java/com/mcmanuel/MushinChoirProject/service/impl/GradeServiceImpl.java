package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.exception.GradeNotFoundException;
import com.mcmanuel.MushinChoirProject.repository.GradeRepository;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }


    @PostConstruct
    private void init(){
        addGrade(new Grade("PRELIM"));
        addGrade(new Grade("GRADE 1"));
        addGrade(new Grade("GRADE 2"));
        addGrade(new Grade("GRADE 3"));
        addGrade(new Grade("GRADE 4"));
        addGrade(new Grade("GRADE 5"));
    }

    @Override
    public Grade addGrade(Grade grade) {
        grade.setGradeId(grade.getGradeId());
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade getGradeById(String gradeId) {
        return gradeRepository.findByGradeId(gradeId).orElseThrow(()-> new GradeNotFoundException("Grade not found"));
    }

    @Override
    public Grade getGradeByLevel(int gradeLevel) {
        return gradeRepository.findByGradeLevel(gradeLevel).orElseThrow(()-> new GradeNotFoundException("Grade not found"));
    }


}
