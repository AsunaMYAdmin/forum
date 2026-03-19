package me.asunamyadmin.forumloregard.global.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.global.view.ForumViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ForumController {
    private final ForumViewService forumViewService;

    @GetMapping("/")
    public String forum(Model model) {
        model.addAttribute("categories", forumViewService.getAllCategoriesWithTopics());
        return "forum";
    }

    @GetMapping("/topic/{id}")
    public  String topic(Model model, @PathVariable int id, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("topic", forumViewService.getTopicByID(id));
        model.addAttribute("posts", forumViewService.getPostsByTopicId(id, page, 20));
        model.addAttribute("totalPages", forumViewService.getTotalPages(id, 20));
        model.addAttribute("currentPage", page);
        return "topic";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("categories", forumViewService.getAllCategoriesWithTopics());
        model.addAttribute("topics", forumViewService.getAllTopicsWithPosts());

        // добавляем сообщения по умолчанию, чтобы Thymeleaf не ругался
        model.addAttribute("successMessage", null);
        model.addAttribute("errorMessage", null);

        return "admin";
    }
}
