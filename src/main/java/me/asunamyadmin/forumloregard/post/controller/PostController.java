package me.asunamyadmin.forumloregard.post.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.post.domain.PostDTO;
import me.asunamyadmin.forumloregard.post.domain.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topic/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping
    public String publishPost(@RequestParam(required = false) Integer topicID,
                              @AuthenticationPrincipal OAuth2User authorName,
                              @RequestParam(required = false) String content) {
        postService.publishPost(new PostDTO(topicID, authorName.getAttribute("sub"), content));
        return "redirect:/forum/topic/" + topicID;
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable int id, @RequestParam int topicID,
                             @AuthenticationPrincipal OAuth2User user) {
        PostDTO post = postService.getPostById(id);
        boolean isAdmin = user.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                                || a.getAuthority().equals("ROLE_GAME_MASTER")
                                || a.getAuthority().equals("ROLE_SYSTEM")
                        );
        String username = user.getAttribute("sub");
        if (!isAdmin && !post.authorName().equals(username)) {
            return "redirect:/forum/topic/" + topicID;
        }
        postService.deletePostById(id);
        return "redirect:/forum/topic/" + topicID;
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable int id, @RequestBody String content) {
        postService.editPost(id, content);
        return "redirect:/";
    }
}
