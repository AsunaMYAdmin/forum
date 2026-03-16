package me.asunamyadmin.forumloregard.global.view;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.category.data.CategoryEntity;
import me.asunamyadmin.forumloregard.category.data.CategoryRepository;
import me.asunamyadmin.forumloregard.category.exception.CategoryNotFoundException;
import me.asunamyadmin.forumloregard.forum_profile.data.ProfileEntity;
import me.asunamyadmin.forumloregard.forum_profile.data.ProfileRepository;
import me.asunamyadmin.forumloregard.forum_profile.exception.ProfileNofFoundException;
import me.asunamyadmin.forumloregard.post.data.PostEntity;
import me.asunamyadmin.forumloregard.post.data.PostRepository;
import me.asunamyadmin.forumloregard.topic.data.TopicEntity;
import me.asunamyadmin.forumloregard.topic.data.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumViewService {
    private final CategoryRepository  categoryRepository;
    private final ProfileRepository profileRepository;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;

    public List<CategoryViewDTO> getAllCategoriesWithTopics() {
        return categoryRepository.findAll()
                .stream()
                .map(this::getCategoryViewDTO)
                .toList();
    }

    public List<TopicViewDTO> getAllTopicsWithPosts() {
        return topicRepository.findAll()
                .stream()
                .map(this::getTopicViewDTO)
                .toList();
    }

    public List<PostViewDTO> getPostsByTopicId(int topicId, int page, int pageSize) {
        List<PostEntity> posts = postRepository.findAllByTopicId(topicId);
        int beginPageIndex = page * pageSize;
        if (posts.size() <= beginPageIndex && page > 0) {
            throw new IllegalArgumentException("Page more than " + beginPageIndex + " is required");
        }
        return posts.stream()
                .skip(beginPageIndex)
                .limit(pageSize)
                .map(this::getPostViewDTO)
                .toList();
    }

    private CategoryViewDTO getCategoryViewDTO(CategoryEntity categoryEntity) {
        List<TopicViewDTO> topics = topicRepository.findAllByCategoryId(categoryEntity.getId())
                .stream()
                .map(this::getTopicViewDTO)
                .toList();
        return new CategoryViewDTO(
                categoryEntity.getId(),
                categoryEntity.getCategoryName(),
                categoryEntity.getDescription(),
                categoryEntity.getIcon(),
                categoryEntity.getSortOrder(),
                categoryEntity.getRole(),
                topics.size(),
                topics
        );
    }

    private TopicViewDTO getTopicViewDTO(TopicEntity topicEntity) {
        ProfileEntity profile = profileRepository.findByUserID(topicEntity.getAuthorId())
                .orElseThrow(ProfileNofFoundException::new);
        CategoryEntity category = categoryRepository.findById(topicEntity.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);
        List<PostEntity> posts = postRepository.findAllByTopicId(topicEntity.getId());
        return new TopicViewDTO(
                topicEntity.getId(),
                topicEntity.getTitle(),
                topicEntity.getCategoryId(),
                topicEntity.getAuthorId(),
                topicEntity.getIsPinned(),
                topicEntity.getIsClosed(),
                profile.getUsername(),
                category.getCategoryName(),
                posts.size(),
                topicEntity.getCreatedAt()
        );
    }


    //TODO game information
    private PostViewDTO getPostViewDTO(PostEntity postEntity) {
        ProfileEntity profile = profileRepository.findByUserID(postEntity.getAuthorId())
                .orElseThrow(ProfileNofFoundException::new);
        return new PostViewDTO(
                postEntity.getId(),
                postEntity.getTopicId(),
                postEntity.getAuthorId(),
                postEntity.getContent(),
                profile.getUsername(),
                "",
                "",
                "",
                postEntity.getCreatedAt()
        );
    }
}
