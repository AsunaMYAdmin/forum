package me.asunamyadmin.forumloregard.topic.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {
    List<TopicEntity> findAllByCategoryId(Integer categoryId);
}
