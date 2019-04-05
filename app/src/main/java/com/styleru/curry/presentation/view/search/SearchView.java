package com.styleru.curry.presentation.view.search;

import com.styleru.curry.data.models.recipe.RecipeResponse;

public interface SearchView {

    void stopShimmerAnimation();

    void showError();

    void searchMode(String dietFilter, String cuisineFilter);

    void filterMode();

    boolean onBackPressed();
}
