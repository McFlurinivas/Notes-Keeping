package com.example.mcfluri;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;

import com.google.gson.Gson;

public class SharedPreferencesNotesRepository implements NotesRepository {
    public SharedPreferencesNotesRepository(@NonNull Context context) {
        this.sharedPreferences = getSharedPreferencesFromContext(context);
    }

    @Override
    public Note getNote(@NonNull String id) {
        final String storedData = sharedPreferences.getString(id, null);
        if(storedData == null)
            return null;

        return gson.fromJson(storedData, Note.class);
    }

    @Override
    public List<Note> getAllNotes() {
        final List<Note> r = new ArrayList<Note>();

        final Map<String, ?> storedData = sharedPreferences.getAll();
        for(Map.Entry<String, ?> entry: storedData.entrySet()) {
            final String value = (String) entry.getValue();

            r.add(gson.fromJson(value, Note.class));
        }

        return r;
    }

    @Override
    public void updateNote(@NonNull Note note) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(note.id, gson.toJson(note));

        editor.apply();
    }

    private static SharedPreferences getSharedPreferencesFromContext(@NonNull Context context) {
        final String name = "com.example.mcfluri_notes";
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @NonNull private final Gson gson = new Gson();
    @NonNull private SharedPreferences sharedPreferences;
}