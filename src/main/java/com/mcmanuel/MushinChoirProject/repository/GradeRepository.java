package com.mcmanuel.MushinChoirProject.repository;

import com.mcmanuel.MushinChoirProject.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

    Optional<Grade> findByGradeId(String gradeId);

    Optional<Grade> findByGradeLevel(Integer level);

    @Query("SELECT COALESCE(MAX(u.gradeLevel), 0) FROM Grade u")
    int getLevel();
}
