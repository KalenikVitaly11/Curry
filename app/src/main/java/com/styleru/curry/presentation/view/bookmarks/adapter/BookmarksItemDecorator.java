package com.styleru.curry.presentation.view.bookmarks.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BookmarksItemDecorator extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int space;

    public BookmarksItemDecorator(int spanCount, int space) {
        this.spanCount = spanCount;
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // Позиция элемента
        int column = position % spanCount; // Колонка элемента

        outRect.left = space - column * space / spanCount;
        outRect.right = (column + 1) * space / spanCount;

        if (position < spanCount) { // Верх всех элементов кроме первого ряда
            outRect.top = space;
        }
        outRect.bottom = space; // Низ
        if (parent.getChildLayoutPosition(view) == parent.getChildCount()) {
            outRect.bottom = 100; // Низ для последнего элемента
        }
    }
}
