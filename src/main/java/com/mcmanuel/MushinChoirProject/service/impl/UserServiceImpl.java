package com.mcmanuel.MushinChoirProject.service.impl;

import com.mcmanuel.ChoirProject.LoginRequest;
import com.mcmanuel.ChoirProject.UserRepository;
import com.mcmanuel.ChoirProject.entity.User;
import com.mcmanuel.ChoirProject.service.JwtService;
import com.mcmanuel.ChoirProject.service.intf.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Integer userId) {
        return userRepository.findById();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(Integer userId, User updatedUser) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

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
