package com.example.mcfluri;

import android.content.Context;

import java.util.List;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends ViewModel {
    public void initialize(final Context context) {
        final NotesRepository noteRepository = new SharedPreferencesNotesRepository(context);

        notes.addAll(noteRepository.getAllNotes());
    }


    @NonNull public final List<Note> notes = new ArrayList<Note>();
}
