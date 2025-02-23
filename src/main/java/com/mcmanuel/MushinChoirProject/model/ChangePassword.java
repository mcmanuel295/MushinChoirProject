package com.mcmanuel.MushinChoirProject.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePassword {

    private String password;
    private String repeatPassword;
}
