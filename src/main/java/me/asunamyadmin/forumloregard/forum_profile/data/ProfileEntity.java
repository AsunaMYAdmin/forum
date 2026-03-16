package me.asunamyadmin.forumloregard.forum_profile.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.asunamyadmin.forumloregard.security.ForumRoles;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "forum_profiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "user_id")
    Integer userID;
    String username;
    String title;
    @Column(name = "forum_role")
    ForumRoles role;
    @Column(name = "post_count")
    Integer postCount;
    @Column(name = "joined_at")
    LocalDateTime joinedAt;

    @PrePersist
    void onCreate() {
        joinedAt = LocalDateTime.now();
        role = ForumRoles.USER;
        postCount = 0;
    }

    public void publicPostCount() {
        postCount = postCount + 1;
    }
}

