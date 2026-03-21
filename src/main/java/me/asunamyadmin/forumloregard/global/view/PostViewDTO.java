package me.asunamyadmin.forumloregard.global.view;

import java.time.LocalDateTime;

public record PostViewDTO(
        Integer id,
        Integer topicID,
        String content,
        String authorName,
        String authorRole,
        String authorClass,
        String authorGuild,
        String authorTitle,
        LocalDateTime createdAt
) {
}
