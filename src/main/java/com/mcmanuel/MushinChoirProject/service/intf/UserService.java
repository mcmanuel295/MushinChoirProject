package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(User user);

    UserDto getUser(Integer userId);

    List<UserDto> getAllUsers();

    List<UserDto> getAllUsersByGrade(String grade);

    UserDto updateUser(Integer userId, User updatedUser);

    void deleteUser(Integer userId);

    boolean login(LoginRequest loginRequest);

}
