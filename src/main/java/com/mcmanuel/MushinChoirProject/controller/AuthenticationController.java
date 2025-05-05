package com.mcmanuel.MushinChoirProject.controller;

import com.mcmanuel.MushinChoirProject.model.ChangePassword;
import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.model.Register;
import com.mcmanuel.MushinChoirProject.model.UserDto;
import com.mcmanuel.MushinChoirProject.service.intf.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService service;

    public AuthenticationController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid Register user) throws MessagingException {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        if (service.login(loginRequest)){
            return new ResponseEntity<>("successful",HttpStatus.ACCEPTED);
        }
        else
           return new ResponseEntity<>("failed",HttpStatus.NOT_ACCEPTABLE);
    }

//    for forget password
    @PostMapping("/sendingOtp")
    public ResponseEntity<String> sendOtp(@RequestBody @Valid String email) throws MessagingException {
        service.sendUserOtp(email);
        return new ResponseEntity<>("Otp sent to user email",HttpStatus.OK);
    }


    @PostMapping("/activate-user")
    public ResponseEntity<String> getOtp(@RequestBody @Valid String otp) {
        var savedOtp = service.getUserOtp(otp);
        if(savedOtp){
            service.activateAccount(otp);
            return new ResponseEntity<>("verified",HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @PutMapping("/updatePassword")
    public ResponseEntity<String> forgetPasswordOtp(@RequestBody @Valid String email, ChangePassword changePassword) throws MessagingException {
        service.changePassword(email,changePassword);
        return new ResponseEntity<>("Password updated", HttpStatus.ACCEPTED);
    }


}
