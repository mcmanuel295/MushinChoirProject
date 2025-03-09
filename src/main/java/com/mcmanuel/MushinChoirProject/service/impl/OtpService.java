package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.MushinChoirProject.entity.Otp;
import com.mcmanuel.MushinChoirProject.exception.OtpNotFoundException;
import com.mcmanuel.MushinChoirProject.repository.OtpRepository;
import org.springframework.stereotype.Service;

@Service
public class OtpService{
    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }


    public void addOtp(Otp generatedOtp) {
        otpRepository.save(generatedOtp);
    }

    public Otp getOtpByEmail(String email) {
        return otpRepository.findByEmail(email).orElseThrow(()->
                new OtpNotFoundException("Invalid otp"));
    }


    public Otp getOtp(String otp) {
        return otpRepository.findByOtp(otp).orElseThrow(()->
                new OtpNotFoundException("Invalid otp"));
    }

    public void deleteOtp(String otp) {
        var savedOtp = getOtp(otp);
        otpRepository.delete(savedOtp);
    }

    public void setVerified(String otp){
        var savedOtp = getOtp(otp);
        savedOtp.setVerified(true);
    }
}
