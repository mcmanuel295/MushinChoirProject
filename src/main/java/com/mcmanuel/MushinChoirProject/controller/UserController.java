package com.mcmanuel.MushinChoirProject.controller;


import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.model.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }



    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<UserDto> getUser(@RequestBody Integer userId) {
        if(service.getUser(userId) != null){
            return new ResponseEntity<>(service.getUser(userId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/{grade}")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<List<UserDto>> getAllUserByGrade(@RequestParam String grade) {
        return new ResponseEntity<>(service.getAllUsersByGrade(grade), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        if (service.updateUser(userId,updatedUser) != null){
            return new ResponseEntity<>(service.updateUser(userId,updatedUser), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/")
    public ResponseEntity<String> deleteUser(@RequestBody Integer userId) {
        service.deleteUser(userId);
        return new ResponseEntity<>("User "+userId+" deleted",HttpStatus.OK );
    }



    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        if (service.login(loginRequest)){
            return "successful";
        }
        else
            return "failed";
    }
}
