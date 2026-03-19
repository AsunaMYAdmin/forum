package me.asunamyadmin.forumloregard.topic.domain;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.topic.data.TopicEntity;
import me.asunamyadmin.forumloregard.topic.data.TopicRepository;
import me.asunamyadmin.forumloregard.topic.exception.TopicNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicDTO>  getAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::entityToTopicDTO)
                .toList();
    }

    @Transactional
    public void createTopic(TopicDTO topicDTO) {
        TopicEntity entity = new TopicEntity();
        entity.setAuthorName(topicDTO.authorName());
        entity.setCategoryId(topicDTO.categoryID());
        entity.setTitle(topicDTO.title());
        topicRepository.save(entity);
    }

    @Transactional
    public void deleteTopic(Integer topicID) {
        TopicEntity topicEntity = topicRepository.findById(topicID).orElseThrow(TopicNotFoundException::new);
        topicRepository.delete(topicEntity);
    }

    @Transactional
    public void changeTitle(Integer topicID, String newTitle) {
        TopicEntity topicEntity = topicRepository.findById(topicID).orElseThrow(TopicNotFoundException::new);
        topicEntity.setTitle(newTitle);
        topicRepository.save(topicEntity);
    }

    @Transactional
    public void changeCategory(Integer topicID, Integer newCategoryId) {
        TopicEntity topicEntity = topicRepository.findById(topicID).orElseThrow(TopicNotFoundException::new);
        topicEntity.setCategoryId(newCategoryId);
        topicRepository.save(topicEntity);
    }

    @Transactional
    public void setPinCategory(Integer topicID, Boolean pinCategory) {
        TopicEntity topicEntity = topicRepository.findById(topicID).orElseThrow(TopicNotFoundException::new);
        topicEntity.setIsPinned(pinCategory);
        topicRepository.save(topicEntity);
    }

    @Transactional
    public void setCloseCategory(Integer topicID, Boolean closeCategory) {
        TopicEntity topicEntity = topicRepository.findById(topicID).orElseThrow(TopicNotFoundException::new);
        topicEntity.setIsClosed(closeCategory);
        topicRepository.save(topicEntity);
    }
}
