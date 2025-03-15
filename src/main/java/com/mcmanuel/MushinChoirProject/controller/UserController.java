package com.mcmanuel.MushinChoirProject.controller;

import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        if(service.getUserById(userId) != null){
            return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        if(service.getUserByEmail(email) != null){
            return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<List<UserDto>> getAllUser(
            @RequestParam(defaultValue = "1", required = false) int pageNo,
            @RequestParam(defaultValue = "5", required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAllUsers(pageNo,pageSize), HttpStatus.OK);
    }


    @GetMapping("/{grade}")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<List<UserDto>> getAllUserByGrade(@RequestParam String grade,
                                                           @RequestParam(defaultValue = "1",required = false) int pageNo,
                                                           @RequestParam(defaultValue = "5",required = false) int pageSize
    ) {
        return new ResponseEntity<>(service.getAllUsersByGrade(grade,pageNo,pageSize), HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody User updatedUser) {
        if (service.updateUser(userId,updatedUser) != null){
            return new ResponseEntity<>(service.updateUser(userId,updatedUser), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId")
    public ResponseEntity<String> deleteUser(@RequestBody UUID userId) {
        service.deleteUser(userId);
        return new ResponseEntity<>("User "+userId+" deleted",HttpStatus.OK );
    }

    @PutMapping("/{userId}/grade")
    public ResponseEntity<UserDto> nextGrade(@Valid @PathVariable UUID userId) {
        var next = service.nextGrade(userId);

        if (next){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
