package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUser(Integer userId);

    List<User> getAllUsers();

    User updateUser(Integer userId, User updatedUser);

    void deleteUser(Integer userId);

    boolean login(LoginRequest loginRequest);
}
