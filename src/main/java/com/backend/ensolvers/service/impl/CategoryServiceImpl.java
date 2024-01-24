package com.backend.ensolvers.service.impl;

import com.backend.ensolvers.error.CategoryNotFoundException;
import com.backend.ensolvers.error.NoteNotFoundException;
import com.backend.ensolvers.model.Category;
import com.backend.ensolvers.model.Note;
import com.backend.ensolvers.repository.CategoryRepository;
import com.backend.ensolvers.repository.NoteRepository;
import com.backend.ensolvers.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * implementation of the Category interface and provides the business logic for CRUD operations on categories.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final NoteRepository noteRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, NoteRepository noteRepository) {
        this.categoryRepository = categoryRepository;
        this.noteRepository = noteRepository;
    }

    // Create a new category and associate it with an existing note.
    @Override
    public Category createCategory(Long noteId, Category newCategory) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException(Constants.NOTE_WITH_ID + noteId + Constants.NOT_FOUND));

        newCategory.getNotes().add(note);
        note.getCategories().add(newCategory);

        return categoryRepository.save(newCategory);
    }


    @Override
    public Optional<Category> findById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_WITH_ID + id + Constants.NOT_FOUND));
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(Constants.CATEGORY_WITH_ID + id + Constants.NOT_FOUND));

        categoryRepository.delete(category);
    }

}
