package me.asunamyadmin.forumloregard.post.domain;

public record PostDTO(
        Integer topicID,
        Integer authorID,
        String content
) {
}
