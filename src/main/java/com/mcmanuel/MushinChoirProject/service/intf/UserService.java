package com.mcmanuel.MushinChoirProject.service.intf;

import com.mcmanuel.MushinChoirProject.model.ChangePassword;
import com.mcmanuel.MushinChoirProject.model.LoginRequest;
import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.model.Register;
import com.mcmanuel.MushinChoirProject.model.UserDto;
import jakarta.mail.MessagingException;
import java.util.List;
import java.util.UUID;


public interface UserService {

    UserDto addUser(Register registeringUser) throws  MessagingException;

    UserDto getUserById(UUID userId);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers(int pageNo, int size);

    List<UserDto> getAllUsersByGrade(String grade,int pageNum, int pageSize);

    List<String> getAllUsersByFullName();

    UserDto updateUser(UUID userId, User updatedUser);

    void deleteUser(UUID userId);

    boolean login(LoginRequest loginRequest);

    void sendUserOtp(String email) throws MessagingException;

    boolean getUserOtp(String otp);

    void activateAccount(String otp);

    void changePassword(String email,ChangePassword changePassword);

    boolean nextGrade(UUID userId);

    boolean addAdmin(String fullName);

    boolean dropAdmin(String fullName);
}
