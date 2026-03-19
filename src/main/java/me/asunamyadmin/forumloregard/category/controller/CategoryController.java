package me.asunamyadmin.forumloregard.category.controller;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.category.domain.CategoryDTO;
import me.asunamyadmin.forumloregard.category.domain.CategoryService;
import me.asunamyadmin.forumloregard.security.ForumRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public String createCategory(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String icon,
            @RequestParam(required = false) Integer sortOrder,
            @RequestParam(required = false) String minRole,
            RedirectAttributes redirectAttributes) {
        categoryService.createCategory(new CategoryDTO(name, description, icon, sortOrder, ForumRoles.valueOf(minRole)));
        redirectAttributes.addFlashAttribute("successMessage", "Категория успешно создана");
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin";
    }

    @PostMapping("/rename")
    public String renameCategory(@RequestParam Integer id, @RequestParam String newName) {
        categoryService.renameCategory(id, newName);
        return "redirect:/admin";
    }
}
