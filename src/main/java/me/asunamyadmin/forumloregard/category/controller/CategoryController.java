package me.asunamyadmin.forumloregard.category.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.category.domain.CategoryDTO;
import me.asunamyadmin.forumloregard.category.domain.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CategoryDTO> deleteCategory(@RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/rename")
    public ResponseEntity<CategoryDTO> renameCategory(@RequestParam Integer id, @RequestParam String newName) {
        categoryService.renameCategory(id, newName);
        return ResponseEntity.ok().build();
    }
}
