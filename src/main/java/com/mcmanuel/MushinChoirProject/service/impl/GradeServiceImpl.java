package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.repository.GradeRepository;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> getGradeById(String gradeId) {
        return gradeRepository.findById(gradeId);
    }

}
