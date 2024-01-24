package com.backend.ensolvers.service;

import com.backend.ensolvers.model.Category;

import java.util.Optional;

public interface CategoryService {

    Category createCategory(Long noteId, Category newCategory);
    Optional<Category> findById(Long id);
    void deleteCategory(Long id);
}
