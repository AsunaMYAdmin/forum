package me.asunamyadmin.forumloregard.post.domain;

import me.asunamyadmin.forumloregard.post.data.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostDTO entityToPostDTO(PostEntity entity) {
        return new PostDTO(
                entity.getTopicId(),
                entity.getAuthorName(),
                entity.getContent()
        );
    }
}
