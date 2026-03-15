package me.asunamyadmin.forumloregard.topic.domain;

public record TopicDTO(
        String title,
        Integer categoryID,
        Integer authorID
) {
}
