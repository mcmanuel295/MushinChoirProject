package com.mcmanuel.MushinChoirProject.service.impl;


import com.mcmanuel.MushinChoirProject.exception.GradeNotFoundException;
import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.model.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;
import com.mcmanuel.MushinChoirProject.model.UtilsService;
import com.mcmanuel.MushinChoirProject.repository.UserRepository;
import com.mcmanuel.MushinChoirProject.service.JwtService;
import com.mcmanuel.MushinChoirProject.service.intf.GradeService;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder ;
    private final GradeService gradeService;


    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService, BCryptPasswordEncoder passwordEncoder, GradeService gradeService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.gradeService = gradeService;
    }

//    This method adds a new user to the database
    @Override
    public UserDto addUser(User user) {
        user.setPassword(
                passwordEncoder.encode( user.getPassword())
        );

        return UtilsService.toUserDto(userRepository.save(user));
    }

//    This method get a particular user with the user ID
    @Override
    public UserDto getUser(Integer userId) {
        return UtilsService.toUserDto(
                userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User "+userId +" Not Found"))
        );
    }


//    This method gets all the users
    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UtilsService::toUserDto)
                .toList();
    }


//    This method gets all the users By the grade
    @Override
    public List<UserDto> getAllUsersByGrade(String grade) {
        if (gradeService.getAllGrades().contains(grade)){
            return userRepository
                    .findAllByGrade(grade)
                    .stream()
                    .map(UtilsService::toUserDto)
                    .toList();
        }
        else {
            throw new GradeNotFoundException("Grade Not found");
        }
    }


    @Override
    public void deleteUser(Integer userId) {
        UserDto user = getUser(userId);
        if (user !=null){
            userRepository.deleteById(userId);
        }
        else {
            throw new UsernameNotFoundException("User "+userId +" Not Found");
        }
    }

//   This method updates the user
    @Override
    public UserDto updateUser(Integer userId, User updatedUser) {
        UserDto user =getUser(userId);
        if (user != null) {
            return UtilsService.toUserDto(userRepository.save(updatedUser));
        }
        else{
            throw new UsernameNotFoundException("User "+userId +" Not Found");
        }
    }




    @Override
    public boolean login(LoginRequest loginRequest) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );

        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(loginRequest.getEmail());
            System.out.println(token);
            return true;
        }
        return false;
    }


}
