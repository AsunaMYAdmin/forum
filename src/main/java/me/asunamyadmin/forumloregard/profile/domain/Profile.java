package me.asunamyadmin.forumloregard.profile.domain;

import jakarta.validation.constraints.NotNull;
import me.asunamyadmin.forumloregard.security.UserRole;

import java.time.LocalDateTime;

public record Profile(
        @NotNull
        String username,
        @NotNull
        String password,
        Integer accountNumber,
        UserRole role,
        LocalDateTime createdAt
) {
}
