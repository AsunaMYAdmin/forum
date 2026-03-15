package me.asunamyadmin.forumloregard.post.domain;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.post.data.PostEntity;
import me.asunamyadmin.forumloregard.post.data.PostRepository;
import me.asunamyadmin.forumloregard.post.exception.PostNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;

    public PostDTO getPostById(int id) {
        PostEntity entity = repository.findById(id).orElseThrow(PostNotFoundException::new);
        return mapper.entityToPostDTO(entity);
    }

    @Transactional
    public void publishPost(PostDTO post) {
        PostEntity entity = new PostEntity();
        entity.setAuthorId(post.authorID());
        entity.setTopicId(post.topicID());
        entity.setContent(post.content());
        repository.save(entity);
    }

    @Transactional
    public void deletePostById(int id) {
        PostEntity entity = repository.findById(id).orElseThrow(PostNotFoundException::new);
        repository.delete(entity);
    }

    @Transactional
    public void editPost(int id, String content) {
        PostEntity entity = repository.findById(id).orElseThrow(PostNotFoundException::new);
        entity.setContent(content);
        repository.save(entity);
    }
}
