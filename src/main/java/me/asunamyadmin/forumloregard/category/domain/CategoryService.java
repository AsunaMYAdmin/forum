package me.asunamyadmin.forumloregard.category.domain;

import lombok.RequiredArgsConstructor;
import me.asunamyadmin.forumloregard.category.data.CategoryEntity;
import me.asunamyadmin.forumloregard.category.data.CategoryRepository;
import me.asunamyadmin.forumloregard.category.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryDTO> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Transactional
    public void createCategory(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryName(categoryDTO.name());
        entity.setDescription(categoryDTO.description());
        entity.setIcon(categoryDTO.icon());
        entity.setSortOrder(categoryDTO.sortOrder());
        entity.setRole(categoryDTO.minRole());
        repository.save(entity);
    }

    @Transactional
    public void deleteCategory(Integer id) {
        CategoryEntity entity = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        repository.delete(entity);
    }

    @Transactional
    public void renameCategory(Integer id, String newName) {
        CategoryEntity entity = repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        entity.setCategoryName(newName);
        repository.save(entity);
    }
}
