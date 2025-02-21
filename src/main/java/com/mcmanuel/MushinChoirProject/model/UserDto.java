package com.mcmanuel.MushinChoirProject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public record UserDto(
        Integer userId,
        String lastName,
        String firstName,
        String fullName,
        Grade grade,
        String part,
        String district,
        String group,
        String ImageString,
        Role role,
        String email,
        boolean isEnabled

) {
}
