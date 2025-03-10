package com.mcmanuel.MushinChoirProject.service;

import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;

public class UtilsService {

    public static User ToUser(UserDto userDto){
                User user = new User();

                user.setUserId(userDto.userId());
                user.setLastName(userDto.lastName());
                user.setFirstName(userDto.firstName());
                user.setGrade(userDto.grade());
                user.setPart(userDto.part());
                user.setDistrict(userDto.district());
                user.setGroup(userDto.group());
                user.setRole(userDto.role());
                user.setEmail(userDto.email());
                user.setEnabled(userDto.isEnabled());
                user.setDateCreated(userDto.dateCreated());
        return user;
    }

    public static UserDto toUserDto(User user){
                return new UserDto(
                        user.getUserId(),
                        user.getLastName(),
                        user.getFirstName(),
                        user.getFullName(),
                        user.getGrade(),
                        user.getPart(),
                        user.getDistrict(),
                        user.getGroup(),
                        user.getRole(),
                        user.getEmail(),
                        user.getImageString(),
                        user.isEnabled(),
                        user.getDateCreated()
                        );
    }


}
