package me.asunamyadmin.forumloregard.topic.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.topic.domain.TopicDTO;
import me.asunamyadmin.forumloregard.topic.domain.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTopic(@RequestBody TopicDTO topicDTO) {
        topicService.createTopic(topicDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable int id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/title/{id}")
    public ResponseEntity<Void> updateTopic(@PathVariable int id, @RequestBody String newTitle) {
        topicService.changeTitle(id, newTitle);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<Void> updateTopic(@PathVariable int id, @RequestParam Integer categoryID) {
        topicService.changeCategory(id, categoryID);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{/id}/pin")
    public ResponseEntity<Void> updateTopicPin(@PathVariable int id, @RequestParam boolean pin) {
        topicService.setPinCategory(id, pin);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{/id}/close")
    public ResponseEntity<Void> updateTopicClose(@PathVariable int id, @RequestParam boolean close) {
        topicService.setCloseCategory(id, close);
        return ResponseEntity.ok().build();
    }

}
