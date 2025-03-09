package com.mcmanuel.MushinChoirProject.repository;


import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

//    @Query("select us from user us where us.grade=?1")
    List<User> findAllByGrade(Grade grade);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password =?2 WHERE u.email =?1")
    void updatePassword(String email,String password);
}
