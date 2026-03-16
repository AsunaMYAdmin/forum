package me.asunamyadmin.forumloregard.global.view;

import java.time.LocalDateTime;

public record PostViewDTO(
        Integer id,
        Integer topicID,
        Integer authorID,
        String content,
        String authorName,
        String authorClass,
        String authorGuild,
        String authorTitle,
        LocalDateTime createdAt
) {
}
