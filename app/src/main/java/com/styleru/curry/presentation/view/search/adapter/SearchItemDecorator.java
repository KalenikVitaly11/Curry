package com.styleru.curry.presentation.view.search.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SearchItemDecorator extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int space;

    public SearchItemDecorator(int spanCount, int space) {
        this.spanCount = spanCount;
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        outRect.left = space - column * space / spanCount; // space - column * ((1f / spanCount) * space)
        outRect.right = (column + 1) * space / spanCount; // (column + 1) * ((1f / spanCount) * space)

        if (position < spanCount) { // top edge
            outRect.top = space;
        }
        outRect.bottom = space; // item bottom
        if (parent.getChildLayoutPosition(view) == parent.getChildCount()) {
            outRect.bottom = 100; // item bottom
        }
    }
}
