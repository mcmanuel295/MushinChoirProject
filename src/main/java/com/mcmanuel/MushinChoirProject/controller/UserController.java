package com.mcmanuel.MushinChoirProject.controller;

import com.mcmanuel.ChoirProject.LoginRequest;
import com.mcmanuel.ChoirProject.service.intf.UserService;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public User createUser(){

    }

    public String login(@RequestBody LoginRequest loginRequest){
        if (service.login(loginRequest)){
            return "successful";
        }
        else
            return "failed";
    }
}
