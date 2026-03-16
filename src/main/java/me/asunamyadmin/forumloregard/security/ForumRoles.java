package me.asunamyadmin.forumloregard.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ForumRoles {
    USER,
    ADMIN,
    GAME_MASTER,
    SYSTEM;

    public SimpleGrantedAuthority getSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}
