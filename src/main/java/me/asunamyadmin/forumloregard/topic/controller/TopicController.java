package me.asunamyadmin.forumloregard.topic.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.topic.domain.TopicDTO;
import me.asunamyadmin.forumloregard.topic.domain.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @PostMapping("/create")
    public String createTopic(@RequestParam String title, @RequestParam Integer categoryID,
                              Principal principal,
                              RedirectAttributes redirectAttributes) {
        topicService.createTopic(new TopicDTO(title, categoryID, principal.getName()));
        redirectAttributes.addFlashAttribute("successMessage", "Тема успешно создана");
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteTopic(@PathVariable int id) {
        topicService.deleteTopic(id);
        return "redirect:/admin";
    }
    @PostMapping("/title/{id}")
    public String updateTopicTitle(@PathVariable int id, @RequestBody String newTitle) {
        topicService.changeTitle(id, newTitle);
            return "redirect:/admin";
    }

    @PostMapping("/category/{id}")
    public String updateTopicCategory(@PathVariable int id, @RequestParam Integer categoryID) {
        topicService.changeCategory(id, categoryID);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/pin")
    public String updateTopicPin(@PathVariable int id, @RequestParam boolean pin) {
        topicService.setPinCategory(id, pin);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/close")
    public String updateTopicClose(@PathVariable int id, @RequestParam boolean close) {
        topicService.setCloseCategory(id, close);
        return "redirect:/admin";
    }

}
