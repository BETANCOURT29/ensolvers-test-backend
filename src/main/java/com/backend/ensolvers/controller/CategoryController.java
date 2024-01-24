package com.backend.ensolvers.controller;

import com.backend.ensolvers.error.CategoryNotFoundException;
import com.backend.ensolvers.model.Category;
import com.backend.ensolvers.service.CategoryService;
import com.backend.ensolvers.service.impl.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/{noteId}")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category newCategory, @PathVariable Long noteId) {
        return new ResponseEntity<>(categoryService.createCategory(noteId, newCategory), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_WITH_ID + id + Constants.NOT_FOUND));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
