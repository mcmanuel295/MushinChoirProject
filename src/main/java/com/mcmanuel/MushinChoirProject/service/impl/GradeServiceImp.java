package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.model.Grade;
import com.mcmanuel.MushinChoirProject.repository.GradeRepository;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImp implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImp(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
}
