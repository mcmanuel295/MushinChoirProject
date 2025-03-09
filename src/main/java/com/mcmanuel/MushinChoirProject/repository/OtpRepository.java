package com.mcmanuel.MushinChoirProject.repository;

import com.mcmanuel.MushinChoirProject.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,Integer> {

    Optional<Otp> findByEmail(String email);

    Optional<Otp> findByOtp(String otp);

}
