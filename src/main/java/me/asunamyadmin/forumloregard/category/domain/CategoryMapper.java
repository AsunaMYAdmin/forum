package me.asunamyadmin.forumloregard.category.domain;

import me.asunamyadmin.forumloregard.category.data.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO entityToDTO(CategoryEntity entity) {
        return new CategoryDTO(
                entity.getCategoryName(),
                entity.getDescription(),
                entity.getIcon(),
                entity.getSortOrder(),
                entity.getRole()
        );
    }
}
