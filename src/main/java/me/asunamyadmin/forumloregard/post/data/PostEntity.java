package me.asunamyadmin.forumloregard.post.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "forum_posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "topic_id")
    Integer topicId;
    @Column(name = "author_id")
    Integer authorId;
    @Column(columnDefinition = "TEXT")
    String content;
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
