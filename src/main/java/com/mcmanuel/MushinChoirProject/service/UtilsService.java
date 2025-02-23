package com.mcmanuel.MushinChoirProject.service;

import com.mcmanuel.MushinChoirProject.entity.User;
import com.mcmanuel.MushinChoirProject.model.UserDto;

public class UtilsService {

    public static User ToUser(UserDto userDto){
        return User.builder()
                .userId(userDto.userId())
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .grade(userDto.grade())
                .part(userDto.part())
                .district(userDto.district())
                .group(userDto.group())
                .role(userDto.role())
                .email(userDto.email())
                .isEnabled(userDto.isEnabled())
                .build();
    }

    public static UserDto toUserDto(User user){
        return UserDto
                .builder()
                .userId(user.getUserId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .fullName(user.fullName())
                .grade(user.getGrade())
                .part(user.getPart())
                .district(user.getDistrict())
                .group(user.getGroup())
                .role(user.getRole())
                .email(user.getEmail())
                .isEnabled(user.isEnabled())
                .ImageString(user.getImageString())
                .build();
    }
}
