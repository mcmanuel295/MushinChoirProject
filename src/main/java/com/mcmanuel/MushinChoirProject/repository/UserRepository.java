package com.mcmanuel.MushinChoirProject.repository;


import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);


    @Query(" select ud from user us where us.grade=?1")
    List<User> findAllByGrade(Grade grade);
}
