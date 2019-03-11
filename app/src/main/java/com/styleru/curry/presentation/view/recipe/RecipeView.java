package com.styleru.curry.presentation.view.recipe;

import com.styleru.curry.data.models.recipe.Recipe;

public interface RecipeView {

    void passData(Recipe recipe);

    void showError();
}
