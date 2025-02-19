package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.ChoirProject.LoginRequest;
import com.mcmanuel.ChoirProject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User <User> addUser(User user);
    Optional getUser(Integer userId);
    List<User> getAllUsers();
    User updateUser(Integer userId, User updatedUser);
    void deleteUser(Integer userId);

    boolean login(LoginRequest loginRequest);
}
