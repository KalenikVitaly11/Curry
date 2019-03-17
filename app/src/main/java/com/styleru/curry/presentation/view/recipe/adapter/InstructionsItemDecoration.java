package com.styleru.curry.presentation.view.recipe.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class InstructionsItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public InstructionsItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = space;
        outRect.left = space;
        outRect.bottom = space;

        // Добавляем большой отступ сверху первому элементу
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        }
    }
}