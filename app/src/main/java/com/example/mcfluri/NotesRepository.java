package com.example.mcfluri;

import androidx.annotation.NonNull;

import java.util.List;

public interface NotesRepository {
    Note getNote(@NonNull String id);
    List<Note> getAllNotes();
    void updateNote(@NonNull Note note);
}
