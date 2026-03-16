package me.asunamyadmin.forumloregard.global.view;

import me.asunamyadmin.forumloregard.security.ForumRoles;

import java.util.List;

public record CategoryViewDTO(
        Integer id,
        String name,
        String description,
        String icon,
        Integer sortOrder,
        ForumRoles minRole,
        Integer topicCount,
        List<TopicViewDTO> topics
) {
}
