package com.backend.ensolvers.service;

import com.backend.ensolvers.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    Note createNote(Note note);
    Optional<Note> findById(Long id);
    Note updateNote (Long id, Note noteDetails);
    void deleteNote(Long id);
    Note archiveOrUnarchiveNote(Long id, boolean archived);
    List<Note> findAllNotes();
    List<?> listActiveNotes();
    List<?> listArchivedNotes();
    List<Note> findByCategory(Long categoryId);
}
