package com.styleru.curry.presentation.view.search;

import com.styleru.curry.data.models.recipe.RecipeResponse;

public interface SearchView {

    void setData(RecipeResponse recipeResponse);

    void showError();
}
