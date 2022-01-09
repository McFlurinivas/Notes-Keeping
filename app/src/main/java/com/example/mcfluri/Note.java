package com.example.mcfluri;

import androidx.annotation.NonNull;

public class Note {
    public Note(@NonNull String id, @NonNull String title, @NonNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @NonNull public String id;
    @NonNull public String title;
    @NonNull public String content;
}
