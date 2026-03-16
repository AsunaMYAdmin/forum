package me.asunamyadmin.forumloregard.global.view;

import java.time.LocalDateTime;

public record TopicViewDTO(
        Integer id,
        String title,
        Integer categoryID,
        Integer authorID,
        Boolean isPinned,
        Boolean isClosed,
        String authorName,
        String categoryName,
        Integer postCount,
        LocalDateTime createdAt
) {
}
