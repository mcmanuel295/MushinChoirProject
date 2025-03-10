package com.mcmanuel.MushinChoirProject.service;

import com.mcmanuel.MushinChoirProject.entity.Otp;
import com.mcmanuel.MushinChoirProject.repository.OtpRepository;
import com.mcmanuel.MushinChoirProject.service.impl.OtpService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;


@Service
@Async
public class EmailService {
    private final JavaMailSender mailSender;
    private final OtpService otpService;

    public EmailService(OtpRepository otpRepository, JavaMailSender mailSender, OtpService otpService) {
        this.mailSender = mailSender;
        this.otpService = otpService;
    }

    public void sendValidationEmail(String email) throws MessagingException {
        var generatedOtp =generateAndSaveActivationOtp(email);

        sendEmail(email,
                "url",
                generatedOtp.getGeneratedOtp());
    }


    private void sendEmail(
            String to,
            String confirmationUrl,
            String activationCode
    ) throws MessagingException {



        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        helper.setFrom("contact@gmail.com");
        helper.setTo(to);
        helper.setSubject("OTP CONFIRMATION");

        String text = "Please confirm your OTP:"+activationCode+" confirmation url"+confirmationUrl;
        helper.setText(text,true);

        mailSender.send(mimeMessage);
    }

    private Otp generateAndSaveActivationOtp(String email){
        Otp generatedOtp = new Otp();

        generatedOtp.setGeneratedOtp(generateActivationCode());
        generatedOtp.setCreatedAt(LocalDateTime.now());
        generatedOtp.setExpiresAt(LocalDateTime.now().plusHours(1));
        generatedOtp.setEmail(email);
        generatedOtp.setVerified(false);
        otpService.addOtp(generatedOtp);

        return generatedOtp;
    }


    private String generateActivationCode(){
        final String characters = "1234567890";
        StringBuilder builder = new StringBuilder();
        SecureRandom secureRandom= new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int index = secureRandom.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }



}
