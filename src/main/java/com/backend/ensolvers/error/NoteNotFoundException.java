package com.backend.ensolvers.error;

public class NoteNotFoundException  extends RuntimeException{

    public NoteNotFoundException(String message) {
        super(message);
    }
}
