package com.styleru.curry.presentation.view.search.pagedAdapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.styleru.curry.data.models.recipe.ShortRecipe;

public class SearchDiffCallback extends DiffUtil.ItemCallback<ShortRecipe> {
    @Override
    public boolean areItemsTheSame(@NonNull ShortRecipe shortRecipe, @NonNull ShortRecipe t1) {
        return shortRecipe.equals(t1);
    }

    @Override
    public boolean areContentsTheSame(@NonNull ShortRecipe shortRecipe, @NonNull ShortRecipe t1) {
        return shortRecipe.equals(t1);
    }
}
