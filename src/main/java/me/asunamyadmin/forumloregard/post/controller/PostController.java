package me.asunamyadmin.forumloregard.post.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.post.domain.PostDTO;
import me.asunamyadmin.forumloregard.post.domain.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<Void> publishPost(@RequestBody PostDTO postDTO) {
        postService.publishPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{/id}")
    public ResponseEntity<Void> updatePost(@PathVariable int id, @RequestBody String content) {
        postService.editPost(id, content);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
