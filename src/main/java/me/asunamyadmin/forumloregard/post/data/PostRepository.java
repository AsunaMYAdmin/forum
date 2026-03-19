package me.asunamyadmin.forumloregard.post.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findAllByTopicId(Integer topicId);

    int countByTopicId(Integer topicId);
}
