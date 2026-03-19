package me.asunamyadmin.forumloregard.topic.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "forum_topics")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    @Column(name = "category_id")
    Integer categoryId;
    @Column(name = "author_name")
    String authorName;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "is_pinned")
    Boolean isPinned;
    @Column(name = "is_closed")
    Boolean isClosed;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isPinned = false;
        this.isClosed = false;
    }
}
