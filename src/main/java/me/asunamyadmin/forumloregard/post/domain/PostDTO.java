package me.asunamyadmin.forumloregard.post.domain;

public record PostDTO(
        Integer topicID,
        String authorName,
        String content
) {
}
