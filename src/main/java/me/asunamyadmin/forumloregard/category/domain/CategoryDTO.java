package me.asunamyadmin.forumloregard.category.domain;

import me.asunamyadmin.forumloregard.security.ForumRoles;

public record CategoryDTO(
        String name,
        String description,
        String icon,
        Integer sortOrder,
        ForumRoles minRole
) {
}
