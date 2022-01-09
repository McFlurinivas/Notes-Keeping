package com.example.mcfluri;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {
    public PaddingItemDecoration(Integer padding) {
        this.padding = (padding != null) ? padding : 32;
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect, @NonNull View view,
            @NonNull RecyclerView parent, @NonNull RecyclerView.State state
    ) {
        outRect.set(padding, padding, padding, padding);
    }


    private final int padding;
}
