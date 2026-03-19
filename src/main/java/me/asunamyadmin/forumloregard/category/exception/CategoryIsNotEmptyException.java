package me.asunamyadmin.forumloregard.category.exception;

public class CategoryIsNotEmptyException extends RuntimeException {
    public CategoryIsNotEmptyException() {
        super("Для удаление категории необходимо, чтобы она была пустая!");
    }
}
