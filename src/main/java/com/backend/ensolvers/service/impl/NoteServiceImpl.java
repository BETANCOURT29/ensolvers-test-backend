package com.backend.ensolvers.service.impl;

import com.backend.ensolvers.error.CategoryNotFoundException;
import com.backend.ensolvers.error.NoteNotFoundException;
import com.backend.ensolvers.model.Note;
import com.backend.ensolvers.repository.NoteRepository;
import com.backend.ensolvers.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * implementation of the NoteService interface and provides the business logic for CRUD operations on notes.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNote(Note note) {
        Note savedNote = noteRepository.save(note);
        return noteRepository.save(savedNote);
    }

    @Override
    public Optional<Note> findById(Long id) {
        noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(Constants.NOTE_WITH_ID + id + Constants.NOT_FOUND));
        return noteRepository.findById(id);
    }

    @Override
    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(Constants.NOTE_WITH_ID + id + Constants.NOT_FOUND));

        if (noteDetails.getTitle() != null) {
            note.setTitle(noteDetails.getTitle());
        }
        if (noteDetails.getDescription() != null) {
            note.setDescription(noteDetails.getDescription());
        }
        if (noteDetails.isArchived() != null) {
            note.setArchived(noteDetails.isArchived());
        }

        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(Constants.NOTE_WITH_ID + id + Constants.NOT_FOUND);
        }
        noteRepository.deleteById(id);
    }

    // Archive or unarchive a note by its identification.
    @Override
    public Note archiveOrUnarchiveNote(Long id, boolean archived) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(Constants.NOT_FOUND + id + Constants.NOT_FOUND));
        note.setArchived(archived);
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No notes found");
        }
        return notes;
    }

    // Gets a list of active notes, when it is not archived.
    @Override
    public List<Note> listActiveNotes() {
        List<Note> notes = noteRepository.findByArchived(false);
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No active notes found");
        }
        return noteRepository.findByArchived(false);
    }

    // Gets a list of archived notes, when it is archived.
    @Override
    public List<Note> listArchivedNotes() {
        List<Note> notes = noteRepository.findByArchived(true);
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No archived notes found");
        }
        return noteRepository.findByArchived(true);
    }

    // Gets a list of notes associated with a category.
    @Override
    public List<Note> findByCategory(Long categoryId) {
        List<Note> notes = noteRepository.findByCategory_Id(categoryId);
        if (notes.isEmpty()) {
            throw new CategoryNotFoundException("No category found");
        }
        return noteRepository.findByCategory_Id(categoryId);
    }


}
