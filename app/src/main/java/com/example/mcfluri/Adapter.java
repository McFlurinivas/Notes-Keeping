package com.example.mcfluri;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public Adapter(@NonNull Context context, @NonNull List<Note> notes)   {
        this.notes = notes;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Note note = notes.get(position);

        holder.title.setText(note.title);
        holder.content.setText(note.content);

        holder.card.setOnClickListener(
            (view)-> {
                final Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra("id", note.id);
                context.startActivity(intent);
            }
        );
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }



    @NonNull private final List<Note> notes;
    @NonNull private final Context context;
    @NonNull private final LayoutInflater inflater;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }


        @NonNull private final CardView card;
        @NonNull private final TextView title;
        @NonNull private final TextView content;
    }
}
