package com.mcmanuel.MushinChoirProject.service.impl;


import com.mcmanuel.MushinChoirProject.exception.UserNotFoundException;
import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.model.User;
import com.mcmanuel.MushinChoirProject.repository.UserRepository;
import com.mcmanuel.MushinChoirProject.service.JwtService;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder ;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

//    This method adds a new user to the database
    @Override
    public User addUser(User user) {
        user.setPassword(
                passwordEncoder.encode( user.getPassword())
        );

        return userRepository.save(user);
    }

//    This method get a particular user with the user ID
    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User "+userId +" Not Found"));
    }

//    This method gets all the users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteUser(Integer userId) {
        User user = getUser(userId);
        if (user !=null){
            userRepository.deleteById(userId);
        }
        else {
            throw new UserNotFoundException("User "+userId +" Not Found");
        }
    }

//   This method updates the user
    @Override
    public User updateUser(Integer userId, User updatedUser) {
        User user =getUser(userId);
        if (user != null) {
            return userRepository.save(updatedUser);
        }
        else{
            throw new UserNotFoundException("User "+userId +" Not Found");
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
