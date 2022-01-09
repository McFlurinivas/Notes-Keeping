package com.example.mcfluri;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;
import androidx.annotation.NonNull;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditNoteViewModel extends ViewModel {
    public void initialize(@NonNull final Context context, String id) {
        notesRepository = new SharedPreferencesNotesRepository(context);

        if(id == null)
            return;

        final Note note = notesRepository.getNote(id);
        this.id = note.id;
        title.setValue(note.title);
        content.setValue(note.content);
    }

    public void onClickedSaveButton(final Context page) {
        notesRepository.updateNote(valuesToNote());

        toHomePage(page);
    }

    private void toHomePage(final Context page) {
        Intent intent = new Intent(page, MainActivity.class);
        page.startActivity(intent);
    }

    private Note valuesToNote() {
        final String finalId = (id != null) ? id : UUID.randomUUID().toString();

        return new Note(finalId, title.getValue(), content.getValue());
    }


    private String id = null;
    @NonNull public final MutableLiveData<String> title = new MutableLiveData<String>("");
    @NonNull public final MutableLiveData<String> content = new MutableLiveData<String>("");

    private NotesRepository notesRepository;
}
