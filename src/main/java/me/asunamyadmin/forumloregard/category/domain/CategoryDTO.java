package me.asunamyadmin.forumloregard.category.domain;

import me.asunamyadmin.forumloregard.security.Roles;

public record CategoryDTO(
        String name,
        String description,
        String icon,
        Integer sortOrder,
        Roles minRole
) {
}
