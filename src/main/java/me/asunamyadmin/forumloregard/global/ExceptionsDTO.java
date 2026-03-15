package me.asunamyadmin.forumloregard.global;

import java.time.LocalDateTime;

public record ExceptionsDTO(
        String title,
        LocalDateTime errorTime
) {
}
