package com.styleru.curry.presentation.view.cuisine.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CuisineItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public CuisineItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = space;
        outRect.bottom = 0;
        outRect.top = 0;

        // Добавляем большой отступ слева первому элементу
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = space * 4;
        } else {
            outRect.left = 0;
        }
    }
}