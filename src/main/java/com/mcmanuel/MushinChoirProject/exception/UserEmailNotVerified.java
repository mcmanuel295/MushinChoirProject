package com.mcmanuel.MushinChoirProject.exception;

public class UserEmailNotVerified extends RuntimeException {
    public UserEmailNotVerified(String message) {
        super(message);
    }
}
