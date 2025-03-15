package com.mcmanuel.MushinChoirProject.service.impl;
import com.mcmanuel.MushinChoirProject.entity.Grade;
import com.mcmanuel.MushinChoirProject.exception.GradeNotFoundException;
import com.mcmanuel.MushinChoirProject.exception.PasswordAndRepeatPasswordNotTheSame;
import com.mcmanuel.MushinChoirProject.exception.OtpNotFoundException;
import com.mcmanuel.MushinChoirProject.exception.UserEmailNotVerified;
import com.mcmanuel.MushinChoirProject.model.*;
import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.service.EmailService;
import com.mcmanuel.MushinChoirProject.service.UtilsService;
import com.mcmanuel.MushinChoirProject.repository.UserRepository;
import com.mcmanuel.MushinChoirProject.service.JwtService;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder ;
    private final GradeService gradeService;
    private final EmailService emailService;
    private final OtpService otpService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService, BCryptPasswordEncoder passwordEncoder, GradeService gradeService, EmailService emailService, OtpService otpService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.gradeService = gradeService;
        this.emailService = emailService;
        this.otpService = otpService;
    }



    @Override
    public UserDto addUser(Register registeringUser) throws MessagingException {
        User user = new User();

        user.setLastName(registeringUser.getLastName());
        user.setFirstName(registeringUser.getFirstName());
        user.setPart(registeringUser.getPart());
        user.setDistrict(registeringUser.getDistrict());
        user.setGroup(registeringUser.getGroupOfDistrict());
        user.setEmail(registeringUser.getEmail());

        user.setPassword(
                passwordEncoder.encode( registeringUser.getPassword())
        );

        user.setGrade(null);
        user.setActivated(false);
        user.setRole(Role.USER);
        user.setDateCreated(LocalDateTime.now());
        emailService.sendValidationEmail(user.getEmail());

        return UtilsService.toUserDto(userRepository.save(user));
    }



//    GET USER BY ATTRIBUTE
//    This method get a particular user with the user ID
    @Override
    public UserDto getUserById(UUID userId) {
        return UtilsService.toUserDto(
                userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User "+userId +" Not Found"))
        );
    }

//    This method get a particular user with the user email
    @Override
    public UserDto getUserByEmail(String email) {
        return UtilsService.toUserDto(
                userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with"+email+" not found"))
        );
    }



//    GET ALL USERS

//    This method gets all the users only by admin
    @Override
    public List<UserDto> getAllUsers(int pageNum,int size) {

        Pageable pageable = PageRequest.of(pageNum,size);

        return userRepository.findAll(pageable).getContent()
                .stream()
                .map(UtilsService::toUserDto)
                .toList();
    }


//    This method gets all the users By the grade only by admin
    @Override
    public List<UserDto> getAllUsersByGrade(String gradeId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        Grade grade = gradeService.getGradeById(gradeId);

        return userRepository
                .findAll(pageable)
                .getContent()
                .stream()
                .filter(user -> user.getGrade().getGradeId().equals(grade.getGradeId()))
                .map(UtilsService::toUserDto)
                .toList();
    }


    @Override
    public void deleteUser(UUID userId) {
        UserDto user = getUserById(userId);
        if (user !=null){
            userRepository.deleteById(userId);
        }
        else {
            throw new UsernameNotFoundException("User "+userId +" Not Found");
        }
    }

//   This method updates the user
    @Override
    public UserDto updateUser(UUID userId, User updatedUser) {
        UserDto userDto =getUserById(userId);

        if (userDto != null) {
            updatedUser.setPassword(passwordEncoder.encode( updatedUser.getPassword()));
            return UtilsService.toUserDto(userRepository.save(updatedUser));
        }
        else{
            throw new UsernameNotFoundException("User "+userId +" Not Found");
        }
    }



    @Override
    public boolean login(LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail()+" "+loginRequest.getPassword());
//        check if user email is verified
        var user = userRepository.findByEmail( loginRequest.getEmail())
                .orElseThrow(()->
                new UsernameNotFoundException("user with email"+loginRequest.getEmail()+" not found"));

        System.out.println("the returned user is "+user.getEmail()+" "+user.getPassword());


        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),loginRequest.getPassword())
        );

        if(authentication.isAuthenticated()){
            String otp = jwtService.generateOtp(loginRequest.getEmail());
            System.out.println(otp);
            return true;
        }
        return false;
    }



    @Override
    public void sendUserOtp(String email) throws MessagingException {
        emailService.sendValidationEmail(email);
    }


    @Override
    public boolean getUserOtp(String otp) {
        var savedOtp =otpService.getOtp(otp);

        if (LocalDateTime.now().isAfter(savedOtp.getExpiresAt())){
            throw new OtpNotFoundException("Activation otp had expired");
        }
        otpService.setVerified(savedOtp.getGeneratedOtp());
        return true;
    }

    @Override
    public void activateAccount(String otp){
        var savedOtp =otpService.getOtp(otp);

        if(savedOtp.isVerified()) {
            //      checking Opt validity
            if (LocalDateTime.now().isAfter(savedOtp.getExpiresAt())) {
                throw new OtpNotFoundException("Activation otp had expired");
            }

            var user = userRepository.findByEmail(savedOtp.getEmail()).orElseThrow(() ->
                    new UsernameNotFoundException("The use with email" + savedOtp.getEmail() + "not found"));

            Grade grade = gradeService.getGradeById("PRELIM");
            user.setActivated(true);
            user.setGrade(grade);

            otpService.deleteOtp(savedOtp.getOtp());
        }
        else {
            throw new OtpNotFoundException("verify account otp");
        }
    }


    @Override
    public void changePassword(String email,ChangePassword changePassword){
        var otp = otpService.getOtpByEmail(email);

        if (!otp.isVerified()) {
            throw new OtpNotFoundException("Invalid otp");
        }

        if(!changePassword.getPassword().equals(changePassword.getRepeatPassword())){
            throw new PasswordAndRepeatPasswordNotTheSame("The password and the repeat password are not the same");
        }

        var user = userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User with email "+email+" not found"));

        String encodedPassword = passwordEncoder.encode(changePassword.getPassword());
        userRepository.updatePassword(user.getEmail(),encodedPassword);
        otpService.deleteOtp(otp.getGeneratedOtp());
    }

    @Override
    public boolean nextGrade(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        if (user != null) {
            var grade = gradeService.getGradeByLevel( user.getGrade().getGradeLevel()+1);
            user.setGrade(grade);
            return true;
        }
        return false;
    }


}
