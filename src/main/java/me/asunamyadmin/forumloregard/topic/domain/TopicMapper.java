package me.asunamyadmin.forumloregard.topic.domain;

import me.asunamyadmin.forumloregard.topic.data.TopicEntity;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public TopicDTO entityToTopicDTO(TopicEntity entity) {
        return new TopicDTO(
                entity.getTitle(),
                entity.getCategoryId(),
                entity.getAuthorId()
        );
    }
}
