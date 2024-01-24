package com.backend.ensolvers.controller;

import com.backend.ensolvers.error.NoteNotFoundException;
import com.backend.ensolvers.model.Note;
import com.backend.ensolvers.service.NoteService;
import com.backend.ensolvers.service.impl.Constants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note createdNote = noteService.createNote(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note noteDetails) {
        Note updatedNote = noteService.updateNote(id, noteDetails);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteService.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(Constants.NOTE_WITH_ID + id + Constants.NOT_FOUND));
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.findAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Note> archiveOrUnarchiveNote(@PathVariable Long id, @RequestParam boolean archived) {
        Note note = noteService.archiveOrUnarchiveNote(id, archived);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<?> listActiveNotes() {
        List<?> notes = noteService.listActiveNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<?> listArchivedNotes() {
        List<?> notes = noteService.listArchivedNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Note>> getNotesByCategory(@PathVariable Long categoryId) {
        List<Note> notes = noteService.findByCategory(categoryId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
