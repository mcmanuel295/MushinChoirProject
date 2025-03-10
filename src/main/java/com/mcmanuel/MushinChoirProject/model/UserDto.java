package com.mcmanuel.MushinChoirProject.model;

import com.mcmanuel.MushinChoirProject.entity.Grade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public record UserDto(
        UUID userId,
        String lastName,
        String firstName,
        String fullName,
        Grade grade,
        Part part,
        String district,
        Group group,
        Role role,
        String email,
        String ImageString,
        boolean isEnabled,
        LocalDateTime dateCreated

) {
}
